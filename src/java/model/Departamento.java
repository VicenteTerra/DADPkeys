/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Pedro
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Departamento.LISTA_CAMPOS, query = "select u from Departamento u where u.id = :id"),
    @NamedQuery(name = Departamento.TODOS_DEPARTAMENTOS, query = "select u from Departamento u")
})
public class Departamento implements Serializable {
    public static final String LISTA_CAMPOS = "Departamento.ListaCampos";
    public static final String TODOS_DEPARTAMENTOS = "Departamento.todosDepartamentos";
    @Id @GeneratedValue
    private Long id;
    @Lob 
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
