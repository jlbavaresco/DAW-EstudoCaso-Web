package br.edu.ifsul.controle;

import br.edu.ifsul.dao.VendaDAO;
import br.edu.ifsul.dao.PessoaFisicaDAO;
import br.edu.ifsul.dao.ProdutoDAO;
import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.modelo.Parcela;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.modelo.Venda;
import br.edu.ifsul.modelo.VendaItens;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@ManagedBean(name = "controleVenda")
@SessionScoped
public class ControleVenda implements Serializable {

    private VendaDAO<Venda> dao;
    private Venda objeto;
    private ProdutoDAO<Produto> daoProduto;
    private PessoaFisicaDAO<PessoaFisica> daoPessoaFisica;
    private UsuarioDAO<Usuario> daoUsuario;
    private VendaItens item;
    private Boolean novoItem;

    public ControleVenda() {
        dao = new VendaDAO<>();
        daoProduto = new ProdutoDAO<>();
        daoPessoaFisica = new PessoaFisicaDAO<>();
        daoUsuario = new UsuarioDAO<>();
    }

    public String listar() {
        return "/privado/venda/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new Venda();
        return "formulario?faces-redirect=true";
    }

    public String salvar() {
        boolean persistiu;
        if (objeto.getId() == null) {
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        } else {
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
    }

    public String cancelar() {
        return "listar?faces-redirect=true";
    }

    public String editar(Integer id) {
        try {
            objeto = dao.localizar(id);
            return "formulario?faces-redirect=true";
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
            return "listar?faces-redirect=true";
        }
    }

    public void remover(Integer id) {
        objeto = dao.localizar(id);
        if (dao.remover(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public void novoItem() {
        item = new VendaItens();
        novoItem = true;
    }

    public void editarItem(int index) {
        item = objeto.getItens().get(index);
        novoItem = false;
    }

    public void salvarItem() {
        if (novoItem) {
            objeto.adicionarItem(item);
        } else {
            atualizarValorTotal();
        }
        Util.mensagemInformacao("Item adicionado com sucesso");
    }

    public void removerItem(int index) {
        objeto.removerItem(index);
        Util.mensagemInformacao("Item removido com sucesso");
    }

    public void atualizaValorUnitarioItem() {
        if (item != null) {
            if (item.getProduto() != null) {
                item.setValorUnitario(item.getProduto().getPreco());
            }
        }
    }        

    public void calculaValorTotalItem() {
        if (item.getValorUnitario() != null
                && item.getQuantidade() != null) {
            item.setValorTotal(item.getValorUnitario() * item.getQuantidade());
        }
    }
    
    private void atualizarValorTotal() {
        objeto.setValorTotal(0.0);
        Double total = 0.0;
        for (VendaItens vi : objeto.getItens()){
            total += vi.getValorTotal();
        }
        objeto.setValorTotal(total);
    }  
    
    public void gerarParcelas(){        
        Boolean temPagamento = false;
        for (Parcela p : objeto.getParcelas()) {
            if (p.getDataPagamento() != null || p.getValorPago() != null) {
                temPagamento = true;
            }
        }
        if (temPagamento) {
            Util.mensagemErro("Parcelas não podem ser geradas novamente "
                    + "por já existir pelo menos um pagamento");
        } else {
            objeto.getParcelas().clear();
            objeto.gerarParcelas();
            Util.mensagemInformacao("Parcelas Geradas com sucesso");
        }        
    }

    public void imprimeRelatorio(Integer id) {

    }

    public VendaDAO getDao() {
        return dao;
    }

    public void setDao(VendaDAO dao) {
        this.dao = dao;
    }

    public Venda getObjeto() {
        return objeto;
    }

    public void setObjeto(Venda objeto) {
        this.objeto = objeto;
    }

    public ProdutoDAO<Produto> getDaoProduto() {
        return daoProduto;
    }

    public void setDaoProduto(ProdutoDAO<Produto> daoProduto) {
        this.daoProduto = daoProduto;
    }

    public PessoaFisicaDAO<PessoaFisica> getDaoPessoaFisica() {
        return daoPessoaFisica;
    }

    public void setDaoPessoaFisica(PessoaFisicaDAO<PessoaFisica> daoPessoaFisica) {
        this.daoPessoaFisica = daoPessoaFisica;
    }

    public UsuarioDAO<Usuario> getDaoUsuario() {
        return daoUsuario;
    }

    public void setDaoUsuario(UsuarioDAO<Usuario> daoUsuario) {
        this.daoUsuario = daoUsuario;
    }

    public VendaItens getItem() {
        return item;
    }

    public void setItem(VendaItens item) {
        this.item = item;
    }

    public Boolean getNovoItem() {
        return novoItem;
    }

    public void setNovoItem(Boolean novoItem) {
        this.novoItem = novoItem;
    }

}
