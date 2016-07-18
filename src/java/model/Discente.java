/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Tecnologia
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Discente.POR_MATRICULA, query = "select u from Discente u where u.matricula  = :mat "),
    @NamedQuery(name = Discente.TODOS_DISCENTES, query = "select u from Discente u")
})
public class Discente implements Serializable{

    public static final String POR_MATRICULA = "Discente.porMatricula";
    public static final String TODOS_DISCENTES = "Discente.todosDiscentes";
    @Id
    @GeneratedValue
    private long id;
    private String nome;
    private String email;
    private String telefone;
    private String matricula;
    private List<Docente> docenteResp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Docente> getDocenteResp() {
        return docenteResp;
    }

    public void setDocenteResp(List<Docente> docenteResp) {
        this.docenteResp = docenteResp;
    }

}
