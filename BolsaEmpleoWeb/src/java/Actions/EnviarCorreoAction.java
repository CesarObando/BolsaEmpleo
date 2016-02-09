/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import Utilitarios.EnviarCorreos;

/**
 *
 * @author JonathanA
 */
public class EnviarCorreoAction extends ActionSupport implements Preparable, ServletRequestAware {

    private HttpServletRequest request;
    private String correo;
    private String asunto;
    private String cuerpo;
    
    public EnviarCorreoAction() {
    }

    public String execute() throws Exception {
        return ActionSupport.INPUT;
    }

    @Override
    public void prepare() throws Exception {
        
    }
    
    public String enviarCorreo() {
        EnviarCorreos enviarCorreos = new EnviarCorreos();
        correo = request.getParameter("correo");
        asunto = request.getParameter("asunto");
        cuerpo = request.getParameter("cuerpo");
        return enviarCorreos.EnviarCorreo(correo, asunto, cuerpo);
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }
    
    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
