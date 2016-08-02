/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DepartamentoDAO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import jpa.dao.JPADepartamentos;
import model.Departamento;

/**
 *
 * @author Pedro
 */
@ManagedBean (name="depControl")
public class ControladorDeDepartamentos {
     private  final DepartamentoDAO departamentoDAO = new JPADepartamentos();
    private final Departamento departamentosessao = null;
    private final  Departamento departamento = new Departamento();
    private List<Departamento> listaDepartamentos = new ArrayList();

   
    
    @PostConstruct
    public void loadDepartamentos(){
        setListaDepartamentos(getDepartamentoDAO().todos());
                
        
    }
    
    public void cadastrar(Departamento departamento){
        getDepartamentoDAO().salvar(departamento);
    }
    public void alterarDepartamento(Long id){
        getDepartamentoDAO().atualizaDepartamento(id);
  }
    public void excluirDepartamento(Long id){
        getDepartamentoDAO().remover(id);
    }
    public Departamento detalharDepartamento(Long id){
        Departamento dep = getDepartamentoDAO().detalhar(id);
        return dep;
        
    }

    
    
    
    
    public List getListaDepartamentos() {
        return listaDepartamentos;
    }

    public DepartamentoDAO getDepartamentoDAO() {
        return departamentoDAO;
    }

    public Departamento getDepartamentosessao() {
        return departamentosessao;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setListaDepartamentos(List listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    

    
}
