package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ParcelaDAO;
import br.edu.ifsul.modelo.Parcela;
import br.edu.ifsul.modelo.ParcelaID;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@ManagedBean(name = "controleParcela")
@ViewScoped
public class ControleParcela implements Serializable {

    private ParcelaDAO<Parcela> dao;
    private Parcela objeto;

    public ControleParcela() {
        dao = new ParcelaDAO<>();
    }

    public String listar() {
        return "/privado/parcelas/listar?faces-redirect=true;";
    }
    
    public void editar(ParcelaID id) {
        try {
            objeto = dao.localizar(id);
        } catch (Exception e) {
            Util.mensagemErro("Erro ao localizar objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void salvarPagamento() {
        try {
            dao.merge(objeto);
            Util.mensagemInformacao("Objeto persistido com sucesso");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir: " + Util.getMensagemErro(e));
        }
    }    

    public ParcelaDAO<Parcela> getDao() {
        return dao;
    }

    public void setDao(ParcelaDAO<Parcela> dao) {
        this.dao = dao;
    }

    public Parcela getObjeto() {
        return objeto;
    }

    public void setObjeto(Parcela objeto) {
        this.objeto = objeto;
    }

}
