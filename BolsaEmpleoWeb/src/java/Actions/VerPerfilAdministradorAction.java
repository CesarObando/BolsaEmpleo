/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Dominio.Administrador;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Tin
 */
public class VerPerfilAdministradorAction extends ActionSupport implements Preparable, ModelDriven<Administrador>, ServletRequestAware, SessionAware {

    private Administrador administrador;
    private String mensaje;
    private HttpServletRequest request;
    public SessionMap<String, Object> sessionMap;

    public VerPerfilAdministradorAction() {
    }

    @Override
    public String execute() {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        administrador = (Administrador) sessionMap.get("administrador");
        administrador = (Administrador) request.getSession().getAttribute("administrador");
    }

    @Override
    public Administrador getModel() {
        return this.administrador;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    @Override
    public void validate() {

    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

}
