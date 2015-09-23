/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.EmpleadorBusiness;
import Business.SolicitanteBusiness;
import Dominio.Empleador;
import Dominio.Solicitante;
import Exception.DataException;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Tin
 */
public class EliminarEmpleadorAction extends ActionSupport implements Preparable, ModelDriven<Empleador>, ServletRequestAware {

    private Empleador empleadorAEliminar;
    private String mensaje;
    private HttpServletRequest request;

    public EliminarEmpleadorAction() {
    }

    @Override
    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        empleadorAEliminar = new Empleador();
        mensaje = "";
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

}
