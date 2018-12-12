package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CidadeDAO;
import br.edu.ifsul.dao.PessoaFisicaDAO;
import br.edu.ifsul.dao.PaisDAO;
import br.edu.ifsul.dao.ProdutoDAO;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Endereco;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@ManagedBean(name = "controlePessoaFisica")
@ViewScoped
public class ControlePessoaFisica implements Serializable {

    private PessoaFisicaDAO<PessoaFisica> dao;
    private PessoaFisica objeto;
    private CidadeDAO<Cidade> daoCidade;
    private ProdutoDAO<Produto> daoProduto;
    private Endereco endereco;
    private Boolean novoEndereco;
    private Produto produto;
    
    public ControlePessoaFisica(){
        dao = new PessoaFisicaDAO<>();
        daoCidade = new CidadeDAO<>();
        daoProduto = new ProdutoDAO<>();
    }
    
    public void imprimeRelatorioPessoa(Integer id){
        objeto = dao.localizar(id);
        List<PessoaFisica> lista = new ArrayList<>();
        lista.add(objeto);
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioPessoa", parametros, lista);
    }
    
    public void imprimeRelatorioTodasPessoas(){
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioPessoa", 
                parametros, dao.getListaTodos());
    }    
    
    public String listar(){
        return "/privado/pessoafisica/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new PessoaFisica();       
    }
    
    public void salvar(){
        boolean persistiu;
        if(objeto.getId() == null){
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu){
            Util.mensagemInformacao(dao.getMensagem());            
        } else {
            Util.mensagemErro(dao.getMensagem());            
        }
    }    
    
    public void editar(Integer id){
        try {
            objeto = dao.localizar(id);            
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));            
        }
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if (dao.remover(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public void novoEndereco(){
        endereco = new Endereco();
        novoEndereco = true;
    }
    
    public void editarEndereco(int index){
        endereco = objeto.getEnderecos().get(index);
        novoEndereco = false;
    }
    
    public void salvarEndereco(){
        if (novoEndereco){
            objeto.adicionarEndereco(endereco);
        }
        Util.mensagemInformacao("Endereço atualizado com sucesso!");
    }
    
    public void removerEndereco(int index){
        objeto.removerEndereco(index);
        Util.mensagemInformacao("Endereço removido com sucesso!");
    }
    
    public void adicionarDesejo(){
        if (produto != null){
            if(!objeto.getDesejos().contains(produto)){
                objeto.getDesejos().add(produto);
                Util.mensagemInformacao("Desejo adicionado com sucesso!");
            } else {
                Util.mensagemErro("Desejo já existe na lista!");
            }
        }
    }
    
    public void removerDesejo(int index){        
        objeto.getDesejos().remove(index);
        Util.mensagemInformacao("Desejo removido com sucesso!");
    }
    
    public PessoaFisicaDAO getDao() {
        return dao;
    }

    public void setDao(PessoaFisicaDAO dao) {
        this.dao = dao;
    }

    public PessoaFisica getObjeto() {
        return objeto;
    }

    public void setObjeto(PessoaFisica objeto) {
        this.objeto = objeto;
    }

    public CidadeDAO<Cidade> getDaoCidade() {
        return daoCidade;
    }

    public void setDaoCidade(CidadeDAO<Cidade> daoCidade) {
        this.daoCidade = daoCidade;
    }

    public ProdutoDAO<Produto> getDaoProduto() {
        return daoProduto;
    }

    public void setDaoProduto(ProdutoDAO<Produto> daoProduto) {
        this.daoProduto = daoProduto;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Boolean getNovoEndereco() {
        return novoEndereco;
    }

    public void setNovoEndereco(Boolean novoEndereco) {
        this.novoEndereco = novoEndereco;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
