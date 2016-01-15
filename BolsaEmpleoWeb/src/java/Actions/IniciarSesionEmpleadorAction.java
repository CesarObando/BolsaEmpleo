/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.EmpleadorBusiness;
import Dominio.Empleador;
import Exception.DataException;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.util.ValueStack;
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
public class IniciarSesionEmpleadorAction extends ActionSupport implements Preparable, ModelDriven<Empleador>, SessionAware, ServletRequestAware {

    private HttpServletRequest request;
    public SessionMap<String, Object> sessionMap;
    private Empleador empleador;
    private String nombreUsuario;
    private String clave;

    @Override
    public void prepare() throws Exception {
        empleador = new Empleador();
    }

    @Override
    public String execute() throws Exception {
        if (this.sessionMap.get("empleador") == null) { // Si no hay una sesion iniciada
            return INPUT;
        } else {
            this.addActionError("Ya se ha iniciado una sesion en el sistema");
            return ERROR;
        }
    }

    @Override
    public Empleador getModel() {
        return this.empleador;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

    public String iniciarSesionEmpleador() {
        sessionMap.clear();
        EmpleadorBusiness empleadorBusiness = new EmpleadorBusiness();
        try {
            empleador = empleadorBusiness.inicioSesion(nombreUsuario, clave);
            if (empleador == null || empleador.getCedula().equals("")) {
                this.addActionMessage("Usuario o contraseña incorrectas");
                return ERROR;
            }
            sessionMap.put("empleador", empleador);
        } catch (SQLException ex) {
            Logger.getLogger(IniciarSesionAction.class.getName()).log(Level.SEVERE, null, ex);
            this.addActionMessage("Ha ocurrido un error en la base de datos, por favor espere. O si el error persiste comuníquese con nosotros.\nGracias");
            return ERROR;
        } catch (DataException ex) {
            Logger.getLogger(IniciarSesionAction.class.getName()).log(Level.SEVERE, null, ex);
            this.addActionMessage("Ha ocurrido un error en la base de datos, por favor espere. O si el error persiste comuníquese con nosotros.\nGracias");
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

    public Empleador getSolicitante() {
        return empleador;
    }

    public void setSolicitante(Empleador empleador) {
        this.empleador = empleador;
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
}
