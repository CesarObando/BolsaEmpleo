/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.SolicitanteBusiness;
import Dominio.Solicitante;
import Exception.DataException;
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
public class IniciarSesionAction extends ActionSupport implements Preparable, ModelDriven<Solicitante>, SessionAware, ServletRequestAware {

    private HttpServletRequest request;
    private SessionMap<String, Object> sessionMap;
    private Solicitante solicitante;
    private String nombreUsuario;
    private String clave;

    @Override
    public void prepare() throws Exception {
        solicitante = new Solicitante();
    }
    
    @Override
    public String execute() throws Exception {
        if (this.sessionMap.get("solicitante") == null){ // Si no hay una sesion iniciada
            return INPUT;
        } else {
            this.addActionError("Ya se ha iniciado una sesion en el sistema");
            return ERROR;
        }
    }

    @Override
    public Solicitante getModel() {
        return this.solicitante;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }
    
    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

    public String iniciarSesionUsuario() {
        sessionMap.clear();
        SolicitanteBusiness solicitanteBusiness = new SolicitanteBusiness();
        try {
            solicitante = solicitanteBusiness.iniciarSesion(nombreUsuario, clave);
            if (solicitante == null){
                this.addActionMessage("Usuario o contraseña incorrectas");
                return ERROR;
            }
            sessionMap.put("solicitante", solicitante);
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

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
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
