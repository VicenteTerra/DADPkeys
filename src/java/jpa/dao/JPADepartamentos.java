/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.dao;

import dao.DepartamentoDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Departamento;

/**
 *
 * @author Pedro
 */
public class JPADepartamentos implements Serializable, DepartamentoDAO {

    @Override
    public void salvar(Departamento departamento) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
       try 
            {
        
               em.getTransaction().begin();
               em.persist(departamento);
               em.getTransaction().commit();
       }finally{
                em.close();
    }
    }

    @Override
    public void atualizaDepartamento(Long id) {
         EntityManager em = JPAUtil.getEMF().createEntityManager();
        
        Departamento departamento = em.find(Departamento.class, id);
        
       try {
            em.getTransaction().begin();
            em.merge(departamento);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void remover(Long id) {
         EntityManager em = JPAUtil.getEMF().createEntityManager();
        Departamento departamento =em.find(Departamento.class, id);
        if (departamento != null) {
            em.getTransaction().begin();
            em.remove(departamento);
            em.getTransaction().commit();
        }
        em.close();
    }

    @Override
    public Departamento detalhar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Departamento> todos() {
    EntityManager em = JPAUtil.getEMF().createEntityManager();
        TypedQuery<Departamento> tq = em.createNamedQuery(Departamento.TODOS_DEPARTAMENTOS,Departamento.class);
        List<Departamento> departamentos = tq.getResultList();
        em.close();
        return departamentos;
    }
}