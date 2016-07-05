/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Chave;

/**
 *
 * @author Vicente
 */
public interface ChaveDAO {

    public void salvar(Chave chave);

    public void remover(Long id);

    public List<Chave> todas();
}
