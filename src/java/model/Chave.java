/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Vicente
 */
@Entity@NamedQuery(name = Chave.TODAS_CHAVES, query = "select u from Chave u")
public class Chave implements Serializable {

    public static final String TODAS_CHAVES = "Chave.TodasChaves";

    @Id
    @GeneratedValue
    private long id;
    private int sala_id;
   
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;   
    }

    public int getSala_id() {
        return sala_id;
    }

    public void setSala_id(int sala_id) {
        this.sala_id = sala_id;
    }

  

}
