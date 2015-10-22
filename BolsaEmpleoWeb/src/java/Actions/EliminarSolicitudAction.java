/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.AdministradorBusiness;
import Business.SolicitudBusiness;
import Dominio.Solicitud;
import Exception.DataException;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author JonathanA
 */
public class EliminarSolicitudAction extends ActionSupport implements Preparable, ModelDriven<Solicitud>, ServletRequestAware {

    private Solicitud solicitudEliminar;
    private String mensaje;
    private boolean existe;
    private HttpServletRequest request;

    public EliminarSolicitudAction() {
    }

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
        int idSolicitud = Integer.parseInt(request.getParameter("id"));
        try {
            solicitudEliminar = new SolicitudBusiness().buscarSolicitud(idSolicitud);
        } catch (SQLException e) {
            existe = false;
        }
    }

    @Override
    public Solicitud getModel() {
        return this.solicitudEliminar;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public String eliminar() throws DataException {
        SolicitudBusiness solicitudBusiness = new SolicitudBusiness();
        boolean eliminado = true;
        try {
            solicitudBusiness.eliminarSolicitud(solicitudEliminar.getId());
        } catch (SQLException e) {
            eliminado = !eliminado;
        }
        if (eliminado) {
            mensaje = "La solicitud fue eliminada correctamente.";
            return SUCCESS;
        } else {
            mensaje = "Ocurrió un problema al eliminar.";
            return ERROR;
        }
    }

    public Solicitud getSolicitudEliminar() {
        return solicitudEliminar;
    }

    public void setSolicitudEliminar(Solicitud solicitudEliminar) {
        this.solicitudEliminar = solicitudEliminar;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

}
