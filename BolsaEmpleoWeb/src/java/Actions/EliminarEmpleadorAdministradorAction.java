/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.EmpleadorBusiness;
import Dominio.Empleador;
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
public class EliminarEmpleadorAdministradorAction extends ActionSupport implements SessionAware, Preparable, ModelDriven<Empleador>, ServletRequestAware {

    private Empleador empleadorAEmpleador;
    private String mensaje;
    private boolean existe;
    private HttpServletRequest request;
    private SessionMap<String, Object> sessionMap;

    public EliminarEmpleadorAdministradorAction() {
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
        existe = true;
        int idEmpleador = Integer.parseInt(request.getParameter("id"));
        try {
            empleadorAEmpleador = new EmpleadorBusiness().buscarEmpleador(idEmpleador);
        } catch (SQLException e) {
            existe = false;
        }
    }

    @Override
    public Empleador getModel() {
        return this.empleadorAEmpleador;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public String eliminar() throws DataException, SQLException {
        EmpleadorBusiness empleadorBusiness = new EmpleadorBusiness();
        int idEmpleador = Integer.parseInt(request.getParameter("id"));
        empleadorAEmpleador = new EmpleadorBusiness().buscarEmpleador(idEmpleador);
        boolean eliminado = true;
        try {
            empleadorBusiness.eliminarEmpleador(empleadorAEmpleador.getId());
        } catch (SQLException e) {
            eliminado = !eliminado;
        }
        if (eliminado) {
            mensaje = "El empleador fue eliminada correctamente.";
            sessionMap.put("mensaje", mensaje);
            addActionMessage(mensaje);
            return SUCCESS;
        } else {
            mensaje = "Ocurrió un problema al eliminar.";
            sessionMap.put("mensaje", mensaje);
            addActionError(mensaje);
            return ERROR;
        }
    }

    public Empleador getEmpleadorAEmpleador() {
        return empleadorAEmpleador;
    }

    public void setEmpleadorAEmpleador(Empleador empleadorAEmpleador) {
        this.empleadorAEmpleador = empleadorAEmpleador;
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
