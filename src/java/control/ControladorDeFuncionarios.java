/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import Util.Mensagens;
import dao.FuncionarioDAO;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.RollbackException;
import javax.servlet.http.HttpSession;
import jpa.dao.JPAFuncionarios;
import model.Funcionario;

/**
 *
 * @author Vicente
 */
@ManagedBean(name = "funcControl")
@SessionScoped
public class ControladorDeFuncionarios {

    private final FuncionarioDAO funcionarioDAO = new JPAFuncionarios();
    private Funcionario funcionarioSessao = null;
    private Funcionario funcionario = new Funcionario();
    private String checkpassword;

    public String autentica() {
        funcionarioSessao = funcionarioDAO.autenticaMatriculaeSenha(funcionario.getLogin(),
                funcionario.getSenha());
        
       
        if (funcionarioSessao == null) {
            Mensagens.adicionarMensagem(
                    FacesMessage.SEVERITY_ERROR,
                    "Login ou senha inválidos!",
                    null);
            return "index.xhtml?faces-redirect=true";
        }
        return "indexUser.xhtml?faces-redirect=true";

    }

    public String cadastro() throws RollbackException {

        if (funcionario.getSenha().equals(checkpassword)) {

            funcionarioDAO.salvar(funcionario);
            funcionario = new Funcionario();

            Mensagens.adicionarMensagem(
                    FacesMessage.SEVERITY_INFO,
                    "Inserção bem sucedida!",
                    null);

        } else {
           
            Mensagens.adicionarMensagem(
                    FacesMessage.SEVERITY_ERROR,
                    "As senhas informadas não conferem!",
                    null);
        }

        return "cadastro.xhtml?faces-redirect=true";

    }

    public String logoff() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        session.invalidate();
        funcionario = new Funcionario();
        return "index.xhtml?faces-redirect=true";
    }

    public String atualizaDados() {
        System.out.println(" novo nome " + funcionarioSessao.getNome());
        funcionarioDAO.atualizaFuncionario(funcionarioSessao);
        Mensagens.adicionarMensagem(FacesMessage.SEVERITY_INFO,
                "Funcionario Alterado!", null);
        return "indexUser.xhtml?faces-redirect=true";

    }

    public Funcionario getFuncionarioSessao() {
        return funcionarioSessao;
    }

    public void setFuncionarioSessao(Funcionario funcionarioSessao) {
        this.funcionarioSessao = funcionarioSessao;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getCheckpassword() {
        return checkpassword;
    }

    public void setCheckpassword(String checkpassword) {
        this.checkpassword = checkpassword;
    }

}
