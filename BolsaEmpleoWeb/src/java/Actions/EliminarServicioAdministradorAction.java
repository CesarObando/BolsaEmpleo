/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.ServicioBusiness;
import Dominio.Servicio;
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
public class EliminarServicioAdministradorAction extends ActionSupport implements SessionAware, Preparable, ModelDriven<Servicio>, ServletRequestAware {

    private Servicio servicioAEliminar;
    private String mensaje;
    private boolean existe;
    private HttpServletRequest request;
    private SessionMap<String, Object> sessionMap;

    public EliminarServicioAdministradorAction() {
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
        int idServicio = Integer.parseInt(request.getParameter("id"));
        servicioAEliminar = new ServicioBusiness().buscarServicio(idServicio);
    }

    @Override
    public Servicio getModel() {
        return this.servicioAEliminar;
    }

    public String eliminar() throws SQLException, DataException {
        ServicioBusiness servicioBusiness = new ServicioBusiness();
        boolean eliminado = true;
        try {
            servicioBusiness.eliminarServicio(servicioAEliminar.getId());
        } catch (SQLException e) {
            eliminado = !eliminado;
        }
        if (eliminado) {
            mensaje = "El servicio fue eliminado correctamente.";
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

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public Servicio getServicioAEliminar() {
        return servicioAEliminar;
    }

    public void setServicioAEliminar(Servicio servicioAEliminar) {
        this.servicioAEliminar = servicioAEliminar;
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
