/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import control.ControladorDeFuncionarios;
import javax.faces.context.FacesContext;
import model.Funcionario;

/**
 *
 * @author Vicente
 */
public class Sessao {
    public static Funcionario obterFuncionarioSessao() {
        FacesContext context = FacesContext.getCurrentInstance();
        ControladorDeFuncionarios funcionarioBean = (ControladorDeFuncionarios) context.getELContext().getELResolver().getValue(context.getELContext(), null, "funcControl");
        if (funcionarioBean != null) {
            return funcionarioBean.getFuncionarioSessao();
        }
        return null;
    }
    
}
