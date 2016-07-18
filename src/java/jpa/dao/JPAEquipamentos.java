/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.dao;

import dao.EquipamentoDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Equipamento;

/**
 *
 * @author Tecnologia
 */
public class JPAEquipamentos implements EquipamentoDAO , Serializable{

    public void salvar(Equipamento equipamento) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(equipamento);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void remover(Long equipamentoId) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        Equipamento equipamento = em.find(Equipamento.class, equipamentoId);
        if (equipamento != null) {
            em.getTransaction().begin();
            em.remove(equipamento);
            em.getTransaction().commit();
        }
        em.close();
    }

    public Equipamento busca(Long id) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        Equipamento equipamento = em.find(Equipamento.class, id);
        em.close();
        return equipamento;
    }

    public Equipamento buscaPorTipo(String tipo) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        TypedQuery<Equipamento> tq = em.createNamedQuery(Equipamento.POR_TIPO,
                Equipamento.class);
        tq.setParameter("tipo", tipo);
        Equipamento equipamento = tq.getSingleResult();
        em.close();
        return equipamento;
    }

    public List<Equipamento> todas() {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        TypedQuery<Equipamento> tq = em.createNamedQuery(Equipamento.TODOS_EQUIPAMENTOS,
                Equipamento.class);
        //  tq.setParameter("id_equipamento", equipamentoId);
        List<Equipamento> funcionarios = tq.getResultList();
        em.close();
        return funcionarios;
    }

    public void atualizaEquipamento(Equipamento equipamento) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
       
        try {
            em.getTransaction().begin();
            em.merge(equipamento);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
