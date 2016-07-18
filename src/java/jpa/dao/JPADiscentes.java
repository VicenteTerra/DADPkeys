/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.dao;

import dao.DiscenteDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Discente;

/**
 *
 * @author Tecnologia
 */
public class JPADiscentes implements DiscenteDAO , Serializable {
    
    public void salvar(Discente discente) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(discente);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void remover(Long discenteId) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        Discente discente = em.find(Discente.class, discenteId);
        if (discente != null) {
            em.getTransaction().begin();
            em.remove(discente);
            em.getTransaction().commit();
        }
        em.close();
    }

    public Discente busca(Long id) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        Discente discente = em.find(Discente.class, id);
        em.close();
        return discente;
    }

    public Discente buscaPorMatricula(String mat) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        TypedQuery<Discente> tq = em.createNamedQuery(Discente.POR_MATRICULA,
                Discente.class);
        tq.setParameter("matricula", mat);
        Discente discente = tq.getSingleResult();
        em.close();
        return discente;
    }

    public List<Discente> todas() {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        TypedQuery<Discente> tq = em.createNamedQuery(Discente.TODOS_DISCENTES,
                Discente.class);
        //  tq.setParameter("id_discente", discenteId);
        List<Discente> funcionarios = tq.getResultList();
        em.close();
        return funcionarios;
    }

    public void atualizaDiscente(Discente discente) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        System.out.println("Cheguei");
        try {
            em.getTransaction().begin();
            em.merge(discente);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    
}
