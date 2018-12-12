package br.edu.ifsul.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
public class Util {
    
    public static String getMensagemErro(Exception e) {
        // aqui eu vou percorrer a exceção até achar a 
        // exceção de mais baixo nivel
        while (e.getCause() != null){
            e = (Exception) e.getCause();
        }
        // aqui capturo a mensagem de erro para tratar
        String retorno = e.getMessage();
        if (retorno.contains("viola restrição de chave estrangeira")){
            retorno = "Registro não pode ser excluído por possuir referência em "
                    + "outros objetos!";
        }
        return retorno;
    }
    
    public static void mensagemInformacao(String msg){
        FacesContext contexto = FacesContext.getCurrentInstance();
        contexto.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, msg,"");
        contexto.addMessage(null, mensagem);               
    }

    public static void mensagemErro(String msg){
        FacesContext contexto = FacesContext.getCurrentInstance();
        contexto.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg,"");
        contexto.addMessage(null, mensagem);               
    }    
    
}
