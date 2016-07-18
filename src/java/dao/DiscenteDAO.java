/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Discente;
import model.Funcionario;

/**
 *
 * @author Tecnologia
 */
public interface DiscenteDAO {

    public void salvar(Discente discente);

    public void remover(Long iduser);

    public List<Discente> todas();

    public Discente busca(Long id);

    public Discente buscaPorMatricula(String mat);

    public void atualizaDiscente(Discente discente);

}
