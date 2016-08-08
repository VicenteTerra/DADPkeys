/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DiscenteDAO;
import dao.DocenteDAO;
import dao.EmprestimoDAO;
import dao.EquipamentoDAO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.RollbackException;
import javax.servlet.http.HttpSession;
import jpa.dao.JPADiscentes;
import jpa.dao.JPADocentes;
import jpa.dao.JPAEmprestimos;
import jpa.dao.JPAEquipamentos;
import model.Discente;
import model.Docente;
import model.Emprestimo;
import model.Equipamento;
import model.Funcionario;
import utill.Mensagens;

/**
 *
 * @author Tecnologia
 */
@ManagedBean(name = "empControl")
@SessionScoped
public class ControladorDeEmprestimos {

    private final EmprestimoDAO emprestimoDAO = new JPAEmprestimos();
    private final DocenteDAO docenteDAO = new JPADocentes();
    private final DiscenteDAO discenteDAO = new JPADiscentes();
    private final EquipamentoDAO equipamentoDAO = new JPAEquipamentos();
    private Emprestimo emprestimo = new Emprestimo();
    private List<Emprestimo> listaEmprestimos = new ArrayList();
    private String buscaMat = new String();
    private String buscaMatResp = new String();
    private String buscaTipo = new String();
    private Discente discente = new Discente();

    @PostConstruct
    public void loadEmprestimos() {
        listaEmprestimos = emprestimoDAO.todas();

    }

    public String cadastrar(Equipamento equip, Funcionario func) throws RollbackException {

        if (discente != null) {
            if (equip.getStatus() != 1) {
                if (buscaTipo.equals("disc")) {
                    Docente docente = docenteDAO.buscaPorMatricula(buscaMatResp);
                    for (Discente a : docente.getListaDiscentes()) {
                        if (a.getMatricula().equals(buscaMat)) {

                            emprestimo.setEquip(equip);
                            emprestimo.setDisc(a);
                            emprestimo.setFunc(func);
                            emprestimoDAO.salvar(emprestimo);
                            equip.setStatus(1);
                            equipamentoDAO.atualizaEquipamento(equip);

                            emprestimo = new Emprestimo();

                            Mensagens.adicionarMensagem(
                                    FacesMessage.SEVERITY_INFO,
                                    "Epréstimo bem sucedido!",
                                    null);

                            return "indexEquipamentos.xhtml?faces-redirect=true";
                        }else{
                            Mensagens.adicionarMensagem(
                                    FacesMessage.SEVERITY_FATAL,
                                    "Discente não autorizado pelo Docente especificado!",
                                    null);
                            
                            return "emprestaEquipamento.xhtml?faces-redirect=true";
                        }
                    }
                }
                if (buscaTipo.equals("doc")) {
                
                    Docente docente = docenteDAO.buscaPorMatricula(buscaMat);
                    emprestimo.setEquip(equip);
                    emprestimo.setFunc(func);
                    emprestimo.setDoc(docente);
                    emprestimoDAO.salvar(emprestimo);
                    equip.setStatus(1);
                    equipamentoDAO.atualizaEquipamento(equip);

                    // listaEmprestimos = emprestimoDAO.todas();
                    emprestimo = new Emprestimo();

                    Mensagens.adicionarMensagem(
                            FacesMessage.SEVERITY_INFO,
                            "Epréstimo bem sucedido!",
                            null);

                    return "indexEquipamentos.xhtml?faces-redirect=true";
                }
            } else {
                Mensagens.adicionarMensagem(
                        FacesMessage.SEVERITY_ERROR,
                        "Equipamento já está emprestado!",
                        null);
                return "indexEquipamentos.xhtml?faces-redirect=true";
            }
        } else {
            Mensagens.adicionarMensagem(
                    FacesMessage.SEVERITY_ERROR,
                    "Não Existe discente com a matrícula informada!",
                    null);
            return "indexEquipamentos.xhtml?faces-redirect=true";
        }
        return "";
    }

    public String devolver(Equipamento equip) {
        if (equip.getStatus() == 1) {
            equip.setStatus(0);
            equipamentoDAO.atualizaEquipamento(equip);

            Mensagens.adicionarMensagem(
                    FacesMessage.SEVERITY_INFO,
                    "Devolvido com Sucesso!",
                    null);
            return "indexEquipamentos.xhtml?faces-redirect=true";
        } else {

            Mensagens.adicionarMensagem(
                    FacesMessage.SEVERITY_ERROR,
                    "Equipamente não está emprestado",
                    null);
            return "indexEquipamentos.xhtml?faces-redirect=true";
        }
    }

    public String remover(Emprestimo func) {
        emprestimoDAO.remover(func.getId());
        loadEmprestimos();

        Mensagens.adicionarMensagem(
                FacesMessage.SEVERITY_ERROR,
                "Usuário Removido!",
                null);

        return "indexEmprestimos.xhtml?faces-redirect=true";

    }

    public void editar(Emprestimo func) {
        emprestimo = emprestimoDAO.busca(func.getId());
        // return "editaEmprestimo.xhtml?faces-redirect=true";
    }

    public String atualizar() {

        emprestimoDAO.atualizaEmprestimo(emprestimo);
        listaEmprestimos = emprestimoDAO.todas();
        emprestimo = new Emprestimo();

        Mensagens.adicionarMensagem(
                FacesMessage.SEVERITY_INFO,
                "Usuário Editato!",
                null);

        return "editaEmprestimo.xhtml?faces-redirect=true";
    }

    public String getBuscaMatResp() {
        return buscaMatResp;
    }

    public void setBuscaMatResp(String buscaMatResp) {
        this.buscaMatResp = buscaMatResp;
    }

    public Discente getDiscente() {
        return discente;
    }

    public void setDiscente(Discente discente) {
        this.discente = discente;
    }

    public String getBuscaMat() {
        return buscaMat;
    }

    public void setBuscaMat(String buscaMat) {
        this.buscaMat = buscaMat;
    }

    public String getBuscaTipo() {
        return buscaTipo;
    }

    public void setBuscaTipo(String buscaTipo) {
        this.buscaTipo = buscaTipo;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public List<Emprestimo> getListaEmprestimos() {
        return listaEmprestimos;
    }

    public void setListaEmprestimos(List<Emprestimo> listaEmprestimos) {
        this.listaEmprestimos = listaEmprestimos;
    }

}
