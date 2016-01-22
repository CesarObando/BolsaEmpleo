/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.AdministradorBusiness;
import Business.EmpleadorBusiness;
import Business.OfertaBusiness;
import Business.SolicitanteBusiness;
import Business.SolicitudBusiness;
import Dominio.Empleador;
import Dominio.Oferta;
import Dominio.Solicitante;
import Dominio.Solicitud;
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
 * @author JonathanA
 */
public class EliminarSolicitudAdministradorAction extends ActionSupport implements SessionAware, Preparable, ModelDriven<Solicitud>, ServletRequestAware {

    private Solicitud solicitudEliminar;
    private String mensaje;
    private boolean existe;
    private HttpServletRequest request;
    private SessionMap<String, Object> sessionMap;

    public EliminarSolicitudAdministradorAction() {
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
        OfertaBusiness ofertaBusiness = new OfertaBusiness();
        SolicitanteBusiness solicitanteBusiness = new SolicitanteBusiness();
        EmpleadorBusiness empleadorBusiness = new EmpleadorBusiness();
        int idSolicitud = Integer.parseInt(request.getParameter("id"));
        try {
            solicitudEliminar = new SolicitudBusiness().buscarSolicitud(idSolicitud);
            int idOferta = solicitudEliminar.getOferta().getId();
            int idSolicitante = solicitudEliminar.getSolicitante().getId();
            Oferta oferta = ofertaBusiness.buscarOferta(idOferta);
            int idEmpleador = oferta.getEmpleador().getId();
            Empleador empleador = empleadorBusiness.buscarEmpleador(idEmpleador);
            oferta.setEmpleador(empleador);
            Solicitante solicitante = solicitanteBusiness.buscarSolicitante(idSolicitante);
            solicitudEliminar.setOferta(oferta);
            solicitudEliminar.setSolicitante(solicitante);
            
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

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

}
