/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Cesar
 */
public class CerrarSesionAction extends ActionSupport implements SessionAware {

    private SessionMap<String, Object> sessionMap;

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

    public String cerrarSesion() {
        sessionMap.remove("solicitante");
        sessionMap.remove("empleador");
        sessionMap.remove("administrador");
        sessionMap.clear();

        return SUCCESS;
    }

}
