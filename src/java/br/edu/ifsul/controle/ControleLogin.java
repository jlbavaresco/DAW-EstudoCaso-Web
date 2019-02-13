package br.edu.ifsul.controle;

import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@ManagedBean(name = "controleLogin")
@SessionScoped
public class ControleLogin implements Serializable {

    private UsuarioDAO<Usuario> dao;
    private Usuario usuarioLogado;
    private String usuario;
    private String senha;

    public ControleLogin() {
        dao = new UsuarioDAO<>();
    }

    public String paginaLogin() {
        return "/login?faces-redirect=true";
    }

    public String efetuarLogin() {
        if (dao.login(usuario, senha)) {
            usuarioLogado = dao.localizaPorNomeUsuario(usuario);
            Util.mensagemInformacao("Login efetuado com sucesso");
            return "/index";
        } else {
            Util.mensagemErro("Usuário ou senha inválidos");
            return "/login";
        }
    }

    public String efetuarLogout() {
        usuarioLogado = null;
        Util.mensagemInformacao("Logout efetuado com sucesso");
        return "/index";
    }

    public UsuarioDAO<Usuario> getDao() {
        return dao;
    }

    public void setDao(UsuarioDAO<Usuario> dao) {
        this.dao = dao;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
