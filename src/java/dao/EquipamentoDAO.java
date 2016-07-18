/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Equipamento;

/**
 *
 * @author Tecnologia
 */
public interface EquipamentoDAO {

    public void salvar(Equipamento equipamento);

    public void remover(Long iduser);

    public List<Equipamento> todas();

    public Equipamento busca(Long id);

    public Equipamento buscaPorTipo(String tipo);

    public void atualizaEquipamento(Equipamento equipamento);

}
