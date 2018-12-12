package br.edu.ifsul.controle;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@ManagedBean(name = "controleAjax")
@ViewScoped
public class ControleAjax implements Serializable {
    
    private String entrada;
    private String saida;
    
    public ControleAjax(){
        
    }
    
    public void processar(){
        saida = "Você digitou: " +  entrada +  " usando ajax do JSF";
        entrada = "";
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }

}
