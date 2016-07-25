/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author Tecnologia
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Emprestimo.TODOS_EMPRESTIMOS, query = "select u from Emprestimo u")
})
public class Emprestimo implements Serializable {

    public static final String TODOS_EMPRESTIMOS = "Emprestimos.TodosEmprestimos";
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Funcionario func;
    @ManyToOne
    private Equipamento equip;
   /* @OneToOne
    private Docente doc;*/
    @ManyToOne
    private Discente disc;
    private Date data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Funcionario getFunc() {
        return func;
    }

    public void setFunc(Funcionario func) {
        this.func = func;
    }

    public Equipamento getEquip() {
        return equip;
    }

    public void setEquip(Equipamento equip) {
        this.equip = equip;
    }

   /* public Docente getDoc() {
        return doc;
    }

    public void setDoc(Docente doc) {
        this.doc = doc;
    }*/

    public Discente getDisc() {
        return disc;
    }

    public void setDisc(Discente disc) {
        this.disc = disc;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}
