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
 * @author Vicente
 */

/*
insert into funcionario(id,email,funcao,login,nome,senha) 
values(0,'admin@gmail.com','administrador','admin','ADM do Sistema','admin')
*/
@Entity
@NamedQueries({
    @NamedQuery(name = Funcionario.POR_LOGIN_E_SENHA, query = "select u from Funcionario u where u.login = :login and u.senha = :senha"),
    @NamedQuery(name = Funcionario.TODOS_FUNCIONARIOS, query = "select u from Funcionario u")
})
public class Funcionario implements Serializable {

    public static final String POR_LOGIN_E_SENHA = "Funcionario.porLogineSenha";
    public static final String TODOS_FUNCIONARIOS = "Funcionario.todosFuncionarios";

    @Id
    @GeneratedValue
    private long id;
    private String nome;
    private String email;
    private String funcao;
    private String login;
    private String senha;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    
    

}
