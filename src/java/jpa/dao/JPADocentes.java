/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.dao;

import dao.DocenteDAO;
import java.io.Serializable;
import java.util.List;
import model.Docente;
import javax.persistence.*;
import model.Discente;

/**
 *
 * @author Pedro
 */
public class JPADocentes implements DocenteDAO, Serializable {

    @Override
    public void salvar(Docente docente) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {

            em.getTransaction().begin();
            em.persist(docente);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void remover(Long id) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        Docente docente = em.find(Docente.class, id);
        if (docente != null) {
            em.getTransaction().begin();
            em.remove(docente);
            em.getTransaction().commit();
        }
        em.close();
    }
   
    /**
     *
     * @return
     */
    @Override
    public List<Docente> todos() {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        TypedQuery<Docente> tq = em.createNamedQuery(Docente.TODOS_DOCENTES, Docente.class);
        List<Docente> docentes = tq.getResultList();
        em.close();
        return docentes;
    }

    @Override
    public void atualizaDocente(Docente docente) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        // System.out.println(" " + docente.getNome());
        try {
            em.getTransaction().begin();
            em.merge(docente);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Docente detalhar(Long id) {

        EntityManager em = JPAUtil.getEMF().createEntityManager();
        TypedQuery<Docente> tq = em.createNamedQuery(Docente.LISTA_CAMPOS, Docente.class);
        Docente docente = tq.getSingleResult();
        em.close();
        return docente;

    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Docente busca(Long id) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        Docente docente = em.find(Docente.class, id);
        em.close();
        return docente;
    }

    @Override
    public Docente buscaPorMatricula(String mat) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            TypedQuery<Docente> tq = em.createNamedQuery(Docente.POR_MATRICULA,
                    Docente.class);
            tq.setParameter("mat", mat);
            List<Docente> docente= tq.getResultList();
            if (docente == null || docente.isEmpty()) {
                return null;
            }
            return docente.get(0);
        } finally {
            em.close();
        }

    }

}
