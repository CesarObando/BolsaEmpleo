/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.AdministradorBusiness;
import Dominio.Administrador;
import Exception.DataException;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Cesar
 */
public class IniciarSesionAdministradorAction extends ActionSupport implements Preparable, ModelDriven<Administrador>, SessionAware, ServletRequestAware {

    private HttpServletRequest request;
    public SessionMap<String, Object> sessionMap;
    private Administrador administrador;
    private String nombreUsuario;
    private String clave;
    private String mensaje;

    @Override
    public void prepare() throws Exception {
        administrador = new Administrador();
    }

    @Override
    public String execute() throws Exception {
        if (this.sessionMap.get("administrador") == null) { // Si no hay una sesion iniciada
            return INPUT;
        } else {
            this.addActionError("Ya se ha iniciado una sesion en el sistema");
            return ERROR;
        }
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

    public String iniciarSesionAdministrador() {
        sessionMap.clear();
        AdministradorBusiness administradorBusiness = new AdministradorBusiness();
        try {
            administrador = administradorBusiness.iniciarSesion(nombreUsuario, clave);
            if (administrador.getId() == 0) {
                this.addFieldError("nombreUsuario", "Usuario o contraseña incorrectas");
                return ERROR;
            }
            mensaje = "Ha iniciado sesión correctamente.";
            sessionMap.put("mensaje", mensaje);
            this.addActionMessage(mensaje);
            sessionMap.put("administrador", administrador);
        } catch (SQLException ex) {
            Logger.getLogger(IniciarSesionAction.class.getName()).log(Level.SEVERE, null, ex);
            mensaje = "Ha ocurrido un error en la base de datos, por favor espere. O si el error persiste comuníquese con nosotros.\nGracias";
            sessionMap.put("mensaje", mensaje);
            this.addActionError(mensaje);
            return ERROR;
        } catch (DataException ex) {
            Logger.getLogger(IniciarSesionAction.class.getName()).log(Level.SEVERE, null, ex);
            mensaje = "\"Ha ocurrido un error en la base de datos, por favor espere. O si el error persiste comuníquese con nosotros.\\nGracias\"";
            sessionMap.put("mensaje", mensaje);
            this.addActionError(mensaje);
            return ERROR;
        }
        return SUCCESS;
    }

    @Override
    public void validate() {
        if (this.request.getParameter("nombreUsuario").trim().equals("")) {
            addFieldError("nombreUsuario", "Ingrese un nombre de usuario o correo");
        }
        if (this.request.getParameter("clave").trim().equals("")) {
            addFieldError("clave", "Ingrese una contraseña");
        }
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public Administrador getModel() {
        return this.administrador;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
