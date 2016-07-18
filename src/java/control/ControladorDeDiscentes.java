/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DiscenteDAO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.RollbackException;
import jpa.dao.JPADiscentes;
import model.Discente;
import utill.Mensagens;

/**
 *
 * @author Tecnologia
 */
@ManagedBean(name = "discControl")
@SessionScoped
public class ControladorDeDiscentes {

    private final DiscenteDAO discenteDAO = new JPADiscentes();
    private Discente discente = new Discente();
    private List<Discente> listaDiscentes = new ArrayList();

    @PostConstruct
    public void loadDiscentes() {
        listaDiscentes = discenteDAO.todas();

    }

    public String cadastrar() throws RollbackException {

        discenteDAO.salvar(discente);
        listaDiscentes = discenteDAO.todas();
        discente = new Discente();

        Mensagens.adicionarMensagem(
                FacesMessage.SEVERITY_INFO,
                "Inserção bem sucedida!",
                null);

        return "cadastroDiscente.xhtml?faces-redirect=true";

    }

    public String remover(Discente func) {
        discenteDAO.remover(func.getId());
        loadDiscentes();

        Mensagens.adicionarMensagem(
                FacesMessage.SEVERITY_ERROR,
                "Usuário Removido!",
                null);

        return "indexDiscentes.xhtml?faces-redirect=true";

    }

    public void editar(Discente disc) {
           
        System.out.println("ID no ante edit" + discente.getId());   
        discente = discenteDAO.busca(disc.getId());
        System.out.println("ID no edit" + discente.getId());
        // return "editaDiscente.xhtml?faces-redirect=true";
    }

    public String atualizar() {
        System.out.println("id = " + discente.getId());
        discenteDAO.atualizaDiscente(discente);
        listaDiscentes = discenteDAO.todas();
        discente = new Discente();

        Mensagens.adicionarMensagem(
                FacesMessage.SEVERITY_INFO,
                "Usuário Editato!",
                null);

        return "editaDiscente.xhtml?faces-redirect=true";
    }

    public Discente getDiscente() {
        return discente;
    }

    public void setDiscente(Discente discente) {
        this.discente = discente;
    }

    public List<Discente> getListaDiscentes() {
        return listaDiscentes;
    }

    public void setListaDiscentes(List<Discente> listaDiscentes) {
        this.listaDiscentes = listaDiscentes;
    }

}
