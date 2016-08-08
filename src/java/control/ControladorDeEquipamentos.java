/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.EquipamentoDAO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.RollbackException;
import javax.servlet.http.HttpSession;
import jpa.dao.JPAEquipamentos;
import model.Emprestimo;
import model.Equipamento;
import utill.Mensagens;

/**
 *
 * @author Tecnologia
 */
@ManagedBean(name = "equipControl")
@SessionScoped
public class ControladorDeEquipamentos {

    private final EquipamentoDAO equipamentoDAO = new JPAEquipamentos();
    private Equipamento equipamento = new Equipamento();
    private List<Equipamento> listaEquipamentos = new ArrayList();

    @PostConstruct
    public void loadEquipamentos() {
        listaEquipamentos = equipamentoDAO.todas();
        equipamento = new Equipamento();
    }

    public String cadastrar() throws RollbackException {

        equipamentoDAO.salvar(equipamento);
        listaEquipamentos = equipamentoDAO.todas();
        equipamento = new Equipamento();

        Mensagens.adicionarMensagem(
                FacesMessage.SEVERITY_INFO,
                "Inserção bem sucedida!",
                null);

        return "cadastroEquipamentos.xhtml?faces-redirect=true";

    }

    public String remover(Equipamento func) {
        equipamentoDAO.remover(func.getId());
        loadEquipamentos();

        Mensagens.adicionarMensagem(
                FacesMessage.SEVERITY_ERROR,
                "Usuário Removido!",
                null);

        return "indexEquipamentos.xhtml?faces-redirect=true";

    }

    public void editar(Equipamento equip) {
        equipamento = equipamentoDAO.busca(equip.getId());
        // return "editaEquipamento.xhtml?faces-redirect=true";
    }

    public String atualizar() {

        equipamentoDAO.atualizaEquipamento(equipamento);
        listaEquipamentos = equipamentoDAO.todas();
        equipamento = new Equipamento();

        Mensagens.adicionarMensagem(
                FacesMessage.SEVERITY_INFO,
                "Usuário Editato!",
                null);

        return "editaEquipamento.xhtml?faces-redirect=true";
    }
    
   

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public List<Equipamento> getListaEquipamentos() {
        return listaEquipamentos;
    }

    public void setListaEquipamentos(List<Equipamento> listaEquipamentos) {
        this.listaEquipamentos = listaEquipamentos;
    }

   
}
