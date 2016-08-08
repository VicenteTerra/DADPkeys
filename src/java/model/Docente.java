/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.io.Serializable;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;



/**
 *
 * @author Pedro
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Docente.LISTA_CAMPOS, query = "select u from Docente u where u.id = :id"),
    @NamedQuery(name = Docente.TODOS_DOCENTES, query = "select u from Docente u"),
    @NamedQuery(name = Docente.POR_MATRICULA, query = "select u from Docente u where u.matricula  = :mat ")
})
public class Docente implements Serializable{
   public static final String LISTA_CAMPOS = "Docente.ListaCampos";
   public static final String TODOS_DOCENTES = "Docente.todosDocentes";
     public static final String POR_MATRICULA = "Docente.porMatricula";
    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String matricula;
    private String nome;
    private String assinatura;
    private String telefone;        
    @ManyToOne
    private Departamento dept;
    @ManyToMany
    private List<Discente> listaDiscentes = new ArrayList();

    public long getId() {
        return id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(String assinatura) {
        this.assinatura = assinatura;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getDepartamento(){
        return getDept().getDescricao();
    }

    public Departamento getDept() {
        return dept;
    }

    public void setDept(Departamento dept) {
        this.dept = dept;
    }

    public List<Discente> getListaDiscentes() {
        return listaDiscentes;
    }

    public void setListaDiscentes(List<Discente> listaDiscentes) {
        this.listaDiscentes = listaDiscentes;
    }

   
    
}
