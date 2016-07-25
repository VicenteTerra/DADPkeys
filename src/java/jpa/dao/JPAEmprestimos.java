/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.dao;

import dao.EmprestimoDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Emprestimo;

/**
 *
 * @author Tecnologia
 */
public class JPAEmprestimos implements EmprestimoDAO , Serializable {
    
    public void salvar(Emprestimo emprestimo) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(emprestimo);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void remover(Long emprestimoId) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        Emprestimo emprestimo = em.find(Emprestimo.class, emprestimoId);
        if (emprestimo != null) {
            em.getTransaction().begin();
            em.remove(emprestimo);
            em.getTransaction().commit();
        }
        em.close();
    }

    public Emprestimo busca(Long id) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        Emprestimo emprestimo = em.find(Emprestimo.class, id);
        em.close();
        return emprestimo;
    }

    public List<Emprestimo> todas() {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        TypedQuery<Emprestimo> tq = em.createNamedQuery(Emprestimo.TODOS_EMPRESTIMOS,
                Emprestimo.class);
        //  tq.setParameter("id_emprestimo", emprestimoId);
        List<Emprestimo> funcionarios = tq.getResultList();
        em.close();
        return funcionarios;
    }

    public void atualizaEmprestimo(Emprestimo funcionario) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
      
        try {
            em.getTransaction().begin();
            em.merge(funcionario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
