/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.dao;

import dao.FuncionarioDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Funcionario;

/**
 *
 * @author Vicente
 */
public class JPAFuncionarios implements FuncionarioDAO,Serializable{
   
    public void salvar(Funcionario usuario) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Funcionario autenticaMatriculaeSenha(String login, String senha) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            TypedQuery<Funcionario> tq = em.createNamedQuery(Funcionario.POR_LOGIN_E_SENHA,
                    Funcionario.class);
            tq.setParameter("login", login);
            tq.setParameter("senha", senha);
            List<Funcionario> lu = tq.getResultList();
            if (lu == null || lu.isEmpty()) {
                return null;
            }
            return lu.get(0);
        } finally {
            em.close();
        }
    }

    public void remover(Long userId) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        Funcionario user = em.find(Funcionario.class, userId);
        if (user != null) {
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        }
        em.close();
    }
    public List<Funcionario> todas() {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        TypedQuery<Funcionario> tq = em.createNamedQuery(Funcionario.TODOS_FUNCIONARIOS,
                Funcionario.class);
      //  tq.setParameter("id_user", usuarioId);
        List<Funcionario> funcionarios = tq.getResultList();
        em.close();
        return funcionarios;
    }
   
    
    public void atualizaFuncionario(Funcionario funcionario){
         EntityManager em = JPAUtil.getEMF().createEntityManager();
         System.out.println(" " + funcionario.getNome());
       try {
            em.getTransaction().begin();
            em.merge(funcionario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
}
