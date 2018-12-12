package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Parcela;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
public class ParcelaDAO<TIPO> extends DAOGenerico<Parcela> implements Serializable {
    
    private Boolean filtroVencimento = false;
    private Calendar dataInicial;
    private Calendar dataFinal;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");    
    
    public ParcelaDAO(){
        super();
        classePersistente = Parcela.class;
        // inicializar a ordem padrão
        ordem = "parcelaID.venda.id";
    }
    
    
    @Override
    public List<Parcela> getListaObjetos() {
        String jpql = "from " + classePersistente.getSimpleName();
        String where = "";
        // filtrar a entrada para proteger de injeção de sql
        filtro = filtro.replaceAll("[';-]", "");
        if (filtro.length() > 0) {
            if (ordem.equals("id") || ordem.equals("parcelaID.venda.id")) {
                try {
                    Integer.parseInt(filtro);
                    where += " where " + ordem + " = '" + filtro + "' ";
                } catch (Exception e) {

                }
            } else {
                where += " where upper(" + ordem + ") like '" + filtro.toUpperCase() + "%' ";
            }
        }
        if (filtroVencimento){
            if (!(where.length() > 0)){
                where = " where ";
            } else {
                where += " and ";
            }
            where += " vencimento >= '"+sdf.format(dataInicial.getTime())+ "' and vencimento <= '"+
                    sdf.format(dataFinal.getTime())+ "' ";
        }        
        jpql += where;
        jpql += " order by " + ordem;
        totalObjetos = em.createQuery(jpql).getResultList().size();
        return em.createQuery(jpql).
                setFirstResult(posicaoAtual).
                setMaxResults(maximoObjetos).getResultList();
    }    

    public Boolean getFiltroVencimento() {
        return filtroVencimento;
    }

    public void setFiltroVencimento(Boolean filtroVencimento) {
        this.filtroVencimento = filtroVencimento;
    }

    public Calendar getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Calendar dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Calendar getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Calendar dataFinal) {
        this.dataFinal = dataFinal;
    }
   
}
