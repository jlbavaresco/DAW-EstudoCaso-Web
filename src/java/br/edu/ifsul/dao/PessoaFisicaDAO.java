package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.PessoaFisica;
import java.io.Serializable;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - CamPpus Passo Fundo
 */
public class PessoaFisicaDAO<TIPO> extends DAOGenerico<PessoaFisica> implements Serializable {
    
    public PessoaFisicaDAO(){
        super();
        classePersistente = PessoaFisica.class;
        // inicializar a ordem padrão
        ordem = "nome";
    }
   
}
