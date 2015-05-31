/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Actions;

import Dominio.Empleador;
import Dominio.Oferente;
import Dominio.Usuario;
import static com.opensymphony.xwork2.Action.INPUT;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ricardo
 */
public class PrincipalEmpleadorAction extends ActionSupport implements Preparable, SessionAware, ServletRequestAware{
    
    private HttpServletRequest request;
    private SessionMap<String, Object> sessionMap;
    private Usuario usuario;
    private Empleador empleador;
    
    @Override
    public void prepare() throws Exception {
        this.empleador = (Empleador) this.sessionMap.get("empleador");
        this.usuario = (Usuario) this.sessionMap.get("usuario");
    }
    
    @Override
    public String execute() throws Exception {
        if (this.sessionMap.get("empleador") != null) { // Hay una sesion de empleado abierta
            return INPUT;
        } else {
            if(this.usuario.isAdministrador())
                return "administrador";
            if(this.usuario.isOferente())
                return "oferente";
            return LOGIN;
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empleador getEmpleador() {
        return empleador;
    }

    public void setEmpleador(Empleador empleador) {
        this.empleador = empleador;
    }
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }
    
    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }
}
