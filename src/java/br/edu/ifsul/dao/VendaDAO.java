package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Venda;
import java.io.Serializable;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
public class VendaDAO<TIPO> extends DAOGenerico<Venda> implements Serializable {
    
    public VendaDAO(){
        super();
        classePersistente = Venda.class;
        // inicializar a ordem padrão
        ordem = "id";
    }
   
}
