package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Produto;
import java.io.Serializable;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
public class ProdutoDAO<TIPO> extends DAOGenerico<Produto> implements Serializable {
    
    public ProdutoDAO(){
        super();
        classePersistente = Produto.class;
        // inicializar a ordem padrão
        ordem = "nome";
    }
   
}
