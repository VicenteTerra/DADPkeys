/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.dao;

import dao.ChaveDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Chave;

/**
 *
 * @author Vicente
 */
public class JPAChaves implements Serializable, ChaveDAO {

    public void salvar(Chave chave) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(chave);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
   
    public void remover(Long chaveId) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        Chave chave = em.find(Chave.class, chaveId);
        if (chave != null) {
            em.getTransaction().begin();
            em.remove(chave);
            em.getTransaction().commit();
        }
        em.close();
    }

    public List<Chave> todas() {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        TypedQuery<Chave> tq = em.createNamedQuery(Chave.TODAS_CHAVES,
                Chave.class);
        //  tq.setParameter("id_user", usuarioId);
        List<Chave> chaves = tq.getResultList();
        em.close();
        return chaves;
    }

    public void atualizaChave(Chave chave) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(chave);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
