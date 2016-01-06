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
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Cesar
 */
public class EliminarSolicitanteAction extends ActionSupport implements Preparable, ModelDriven<Solicitante>, ServletRequestAware, SessionAware{
    
    private Solicitante solicitanteAEliminar;
    private String mensaje;
    private boolean existe;
    private HttpServletRequest request;
    public SessionMap<String, Object> sessionMap;

    public EliminarSolicitanteAction() {
    }
    
     @Override
    public String execute() throws Exception {
        if (existe) {
            return INPUT;
        } else {
            return ERROR;
        }
    }
    
    @Override
    public void prepare() throws Exception {
        solicitanteAEliminar = (Solicitante) sessionMap.get("solicitante");
    }

    @Override
    public Solicitante getModel() {
        return this.solicitanteAEliminar;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }
    
    @Override
    public void validate(){
        
    }
    
    public String eliminar() throws DataException{
        SolicitanteBusiness solicitanteBusiness = new SolicitanteBusiness();
        try {
            solicitanteBusiness.eliminarSolicitante(solicitanteAEliminar.getId());
            return SUCCESS;
        } catch (SQLException e) {
            return ERROR;
        }
    }

    public Solicitante getSolicitanteAEliminar() {
        return solicitanteAEliminar;
    }

    public void setSolicitanteAEliminar(Solicitante solicitanteAEliminar) {
        this.solicitanteAEliminar = solicitanteAEliminar;
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

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }
    
}
