/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Departamento;

/**
 *
 * @author Pedro
 */
public interface DepartamentoDAO {
    public void salvar(Departamento departamento);
    public void atualizaDepartamento (Long id);
    public void remover(Long id);
    public Departamento detalhar(Long id);
    public  List<Departamento> todos();
    
}
