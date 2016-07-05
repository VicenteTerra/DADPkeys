/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import Util.Mensagens;
import dao.ChaveDAO;
import dao.FuncionarioDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import jpa.dao.JPAChaves;
import jpa.dao.JPAFuncionarios;
import model.Chave;
import model.Funcionario;

/**
 *
 * @author Vicente
 */

@ManagedBean(name = "chaveControl")
public class ControladorDeChaves implements Serializable {

    private final ChaveDAO chaveDAO = new JPAChaves();
    //private Funcionario funcionarioSessao = null;
    private Chave chave = new Chave();
    //private String checkpassword;
    private List<Chave> listaChaves = new ArrayList();

    public String salvar() {

        chaveDAO.salvar(chave);

        /// listaGeneroLivro = livroDAO.generosPorId(novoLivro.getId());
        Mensagens.adicionarMensagem(FacesMessage.SEVERITY_INFO,
                "Chave Cadastrado com sucesso!", null);
        chave = new Chave();

        return "indexUser.xhtml?faces-redirect=true";
    }

    public String remover(Chave chave) {
        chaveDAO.remover(chave.getId());
        carregarChaves();
        Mensagens.adicionarMensagem(FacesMessage.SEVERITY_INFO,
                "Chave excluida com sucesso!", null);
      
        return "indexUser.xhtml?faces-redirect=true";
    }

    /*  public List<Genero> carregarGeneros(long id) {
        listaGeneroLivro = livroDAO.generosPorId(novoLivro.getId());
        return listaGeneroLivro;
    }*/
    @PostConstruct
    public void carregarChaves() {
        listaChaves = chaveDAO.todas();

    }

    
    public Chave getChave() {
        return chave;
    }

    public void setChave(Chave chave) {
        this.chave = chave;
    }

    public List<Chave> getListaChaves() {
        return listaChaves;
    }

    public void setListaChaves(List<Chave> listaChaves) {
        this.listaChaves = listaChaves;
    }
    
    
}
