/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Chave;
import model.Funcionario;

/**
 *
 * @author Vicente
 */
public interface FuncionarioDAO {
     
    public void salvar(Funcionario funcionario);

    public Funcionario autenticaMatriculaeSenha(String login, String senha);

    public void remover(Long iduser);

    public List<Funcionario> todas();

    public void atualizaFuncionario(Funcionario funcionario);
    
}
