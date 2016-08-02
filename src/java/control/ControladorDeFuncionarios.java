/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import utill.Mensagens;
import dao.FuncionarioDAO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
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
    private List<Funcionario> listaFuncionarios = new ArrayList();
    private String checkpassword;

    @PostConstruct
    public void loadFuncionarios() {
        listaFuncionarios = funcionarioDAO.todas();

    }

    public String autentica() {

        funcionarioSessao = funcionarioDAO.autenticaMatriculaeSenha(funcionario.getLogin(),
                funcionario.getSenha());
        funcionario = new Funcionario();
      
            if (funcionarioSessao == null) {
                Mensagens.adicionarMensagem(
                        FacesMessage.SEVERITY_ERROR,
                        "Login ou senha inválidos!",
                        null);
                return "index.xhtml?faces-redirect=true";
            }
            return "indexFuncionarios.xhtml?faces-redirect=true";
        

    }

    public String cadastrar() throws RollbackException {

        if (funcionario.getSenha().equals(checkpassword)) {

            funcionarioDAO.salvar(funcionario);
            listaFuncionarios = funcionarioDAO.todas();
            funcionario = new Funcionario();
            checkpassword = null;
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

        return "cadastroFuncionarios.xhtml?faces-redirect=true";

    }

    public String remover(Funcionario func) {
        funcionarioDAO.remover(func.getId());
        loadFuncionarios();

        Mensagens.adicionarMensagem(
                FacesMessage.SEVERITY_ERROR,
                "Usuário Removido!",
                null);

        return "indexFuncionarios.xhtml?faces-redirect=true";

    }

    public void editar(Funcionario func) {
        funcionario = funcionarioDAO.busca(func.getId());
        // return "editaFuncionario.xhtml?faces-redirect=true";
    }

    public String atualizar() {
        if (funcionario.getSenha().equals(checkpassword)) {
            funcionarioDAO.atualizaFuncionario(funcionario);
            listaFuncionarios = funcionarioDAO.todas();
            funcionario = new Funcionario();
            checkpassword = null;
            Mensagens.adicionarMensagem(
                    FacesMessage.SEVERITY_INFO,
                    "Usuário Editato!",
                    null);

        } else {
            Mensagens.adicionarMensagem(
                    FacesMessage.SEVERITY_ERROR,
                    "As senhas não conferem!",
                    null);

        }
        return "editaFuncionario.xhtml?faces-redirect=true";
    }

    public String logoff() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        session.invalidate();
        funcionario = new Funcionario();
        return "index.xhtml?faces-redirect=true";
    }

    /*public String atualizaDados() {
        System.out.println(" novo nome " + funcionarioSessao.getNome());
        funcionarioDAO.atualizaFuncionario(funcionarioSessao);
        Mensagens.adicionarMensagem(FacesMessage.SEVERITY_INFO,
                "Funcionario Alterado!", null);
        return "indexUser.xhtml?faces-redirect=true";

    }*/
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

    public List<Funcionario> getListaFuncionarios() {
        return listaFuncionarios;
    }

    public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
        this.listaFuncionarios = listaFuncionarios;
    }

}
