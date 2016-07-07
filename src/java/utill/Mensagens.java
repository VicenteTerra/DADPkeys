/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utill;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Vicente
 */
public class Mensagens {
    public static void adicionarMensagem(FacesMessage.Severity sev, String msg, String componente) {
        FacesMessage fm = new FacesMessage(sev, msg, msg);
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(componente, fm);
    }
}
