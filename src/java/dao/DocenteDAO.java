/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Discente;
import model.Docente;

/**
 *
 * @author Pedro
 */
public interface DocenteDAO {
    public void salvar(Docente docente);
    public void atualizaDocente (Docente docente);
    public void remover(Long id);
    public Docente detalhar(Long id);
    public  List<Docente> todos();
    public Docente busca(Long id);
    public Docente buscaPorMatricula(String mat);            
    
}
