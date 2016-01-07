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
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.util.ValueStack;
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
        existe = true;
        empleadorAEliminar = new Empleador();
        empleadorAEliminar = (Empleador) sessionMap.get("empleador");
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
        try {
            empleadorBusiness.eliminarEmpleador(empleadorAEliminar.getId());
            return SUCCESS;
        } catch (SQLException e) {
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
