package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Usuario;
import java.io.Serializable;
import javax.persistence.Query;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - CamPpus Passo Fundo
 */
public class UsuarioDAO<TIPO> extends DAOGenerico<Usuario> implements Serializable {
    
    public UsuarioDAO(){
        super();
        classePersistente = Usuario.class;
        // inicializar a ordem padrão
        ordem = "nome";
    }
    
      public boolean login(String usuario, String senha) {
		  Query query = super.getEm()
			  .createQuery(
				  "from Usuario where upper(nomeUsuario) = :usuario and "
				  + "upper(senha) = :senha and ativo = true");
		  query.setParameter("usuario", usuario.toUpperCase());
		  query.setParameter("senha", senha.toUpperCase());
		  if (!query.getResultList().isEmpty()) {
			  return true;
		  } else {
			  return false;
		  }
      }

      public Usuario localizaPorNomeUsuario(String usuario) {
	  Usuario obj = (Usuario) super.getEm().createQuery("from Usuario where upper(nomeUsuario) = :usuario").
		  setParameter("usuario", usuario.toUpperCase()).getSingleResult();
	  return obj;
      }    
   
}
