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
import static com.opensymphony.xwork2.Action.SUCCESS;
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
 * @author Tin
 */
public class EliminarEmpleadorAction extends ActionSupport implements Preparable, ModelDriven<Empleador>, ServletRequestAware, SessionAware {

    private Empleador empleadorAEliminar;
    private String mensaje;
    private boolean existe;
    private HttpServletRequest request;
    public SessionMap<String, Object> sessionMap;

    public EliminarEmpleadorAction() {
    }

    @Override
    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        empleadorAEliminar = new Empleador();
        empleadorAEliminar = (Empleador) sessionMap.get("empleador");
        existe = true;
        if (request.getParameter("id") != null) {
            int idSolicitante = Integer.parseInt(request.getParameter("id"));
            empleadorAEliminar.setId(idSolicitante);
        }
    }

    @Override
    public void validate() {

    }

    @Override
    public Empleador getModel() {
        return this.empleadorAEliminar;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public String eliminar() throws DataException {
        EmpleadorBusiness empleadorBusiness = new EmpleadorBusiness();
        boolean eliminado = true;
        try {
            empleadorBusiness.eliminarEmpleador(empleadorAEliminar.getId());
        } catch (SQLException e) {
            eliminado = !eliminado;
        }
        if (eliminado) {
            mensaje = "La categoría fue eliminada correctamente.";
            return SUCCESS;
        } else {
            mensaje = "Ocurrió un problema al eliminar.";
            return ERROR;
        }
    }

    public Empleador getEmpleadorAEliminar() {
        return empleadorAEliminar;
    }

    public void setEmpleadorAEliminar(Empleador empleadorAEliminar) {
        this.empleadorAEliminar = empleadorAEliminar;
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

}
