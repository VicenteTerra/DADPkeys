/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;


import dao.DocenteDAO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import jpa.dao.JPADocentes;
import model.Docente;
import utill.Mensagens;



/**
 *
 * @author Pedro
 */
@ManagedBean(name = "docControl")
@SessionScoped
public class ControladorDeDocentes {
    private  final DocenteDAO docenteDAO = new JPADocentes();
    private final Docente docentesessao = null;
    private  Docente docente;
    private List listaDocentes = new ArrayList();

    public ControladorDeDocentes() {
        this.docente = new Docente();
    }
    
    @PostConstruct
    public void loadDocentes(){
        listaDocentes = docenteDAO.todos();
                
        
    }
    
    public void cadastrar(){
        docenteDAO.salvar(docente);
        listaDocentes = docenteDAO.todos();
        docente = new Docente();
    }
    public void alterarDocente(Docente doc){
        docente = docenteDAO.busca(doc.getId());
  }
    public String remover(Docente doc){
      docenteDAO.remover(doc.getId());
        loadDocentes();

        Mensagens.adicionarMensagem(
                FacesMessage.SEVERITY_ERROR,
                "Docente Removido!",
                null);

        return "indexDocentes.xhtml?faces-redirect=true";

    }
    public Docente detalharDocente(Long id){
        Docente doc = docenteDAO.detalhar(id);
        return doc;
        
    }
    public void atualizar(){
        docenteDAO.atualizaDocente(docente);
        docente = new Docente();
        
    }
    
    
    
    
    
    public List getListaDocentes() {
        return listaDocentes;
    }

    public void setListaDocentes(List listaDocentes) {
        this.listaDocentes = listaDocentes;
    }

    public DocenteDAO getDocenteDAO() {
        return docenteDAO;
    }

    public Docente getDocentesessao() {
        return docentesessao;
    }

    public Docente getDocente() {
        return docente;
    }

}
