/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Tecnologia
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Equipamento.POR_TIPO, query = "select u from Equipamento u where u.tipo = :tipo "),
    @NamedQuery(name = Equipamento.TODOS_EQUIPAMENTOS, query = "select u from Equipamento u")
})
public class Equipamento implements Serializable {

    public static final String POR_TIPO = "Equipamento.porLogineSenha";
    public static final String TODOS_EQUIPAMENTOS = "Equipamento.todosFuncionario";

    @Id
    @GeneratedValue
    private long id;
    private String local;
    private String tipo;
    private String descricao;
    private int status = 0;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
