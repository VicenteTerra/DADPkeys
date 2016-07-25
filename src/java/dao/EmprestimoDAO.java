/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Emprestimo;

/**
 *
 * @author Tecnologia
 */
public interface EmprestimoDAO {
    
    public void salvar(Emprestimo emprestimo  );

    public void remover(Long id);

    public List<Emprestimo> todas();
    
    public Emprestimo busca(Long id);

    public void atualizaEmprestimo(Emprestimo emprestimo);
}
