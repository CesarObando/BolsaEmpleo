/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.EmpleadorBusiness;
import Business.OfertaBusiness;
import Business.SolicitudBusiness;
import Dominio.Empleador;
import Dominio.Oferta;
import Dominio.Solicitante;
import Dominio.Solicitud;
import Exception.DataException;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author JonathanA
 */
public class BuscarSolicitudesSolicitanteAction extends ActionSupport implements Preparable, ServletRequestAware, SessionAware {

    private final String BUSCAR_SOLICITUDES = "buscarSolicitudesSolicitante";
    private Solicitante solicitante;
    private LinkedList<Solicitud> solicitudes;
    private HttpServletRequest request;
    private SessionMap<String, Object> sessionMap;

    public BuscarSolicitudesSolicitanteAction() {
    }

    @Override
    public String execute() throws Exception {
        return ActionSupport.SUCCESS;
    }

    @Override
    public void prepare() throws Exception {
        solicitante = (Solicitante) sessionMap.get("solicitante");
        int idSolicitante = solicitante.getId();
        SolicitudBusiness solicitudBusiness = new SolicitudBusiness();
        OfertaBusiness ofertaBusiness = new OfertaBusiness();
        EmpleadorBusiness empleadorBusiness = new EmpleadorBusiness();
        solicitudes = solicitudBusiness.buscarSolicitudesFiltradas(idSolicitante, 0);
        for (int i = 0; i < solicitudes.size(); i++) {
            Solicitud solicitud = solicitudes.get(i);
            int idOferta = solicitud.getOferta().getId();
            Oferta oferta = ofertaBusiness.buscarOferta(idOferta);
            int idEmpleador = oferta.getEmpleador().getId();
            Empleador empleador = empleadorBusiness.buscarEmpleador(idEmpleador);
            oferta.setEmpleador(empleador);
            solicitud.setOferta(oferta);
            solicitudes.set(i, solicitud);
        }
    }

    public String buscar() throws DataException {
        solicitante = (Solicitante) sessionMap.get("solicitante");
        int idSolicitante = solicitante.getId();
        SolicitudBusiness solicitudBusiness = new SolicitudBusiness();
        OfertaBusiness ofertaBusiness = new OfertaBusiness();
        EmpleadorBusiness empleadorBusiness = new EmpleadorBusiness();
        try {
            solicitudes = solicitudBusiness.buscarSolicitudesFiltradas(idSolicitante, 0);

            for (int i = 0; i < solicitudes.size(); i++) {
                Solicitud solicitud = solicitudes.get(i);
                int idOferta = solicitud.getOferta().getId();
                Oferta oferta = ofertaBusiness.buscarOferta(idOferta);
                int idEmpleador = oferta.getEmpleador().getId();
                Empleador empleador = empleadorBusiness.buscarEmpleador(idEmpleador);
                oferta.setEmpleador(empleador);
                solicitud.setOferta(oferta);
                solicitudes.set(i, solicitud);
            }

        } catch (SQLException e) {
            Logger.getLogger(BuscarSolicitudesSolicitanteAction.class.getName()).log(Level.SEVERE, null, e);
        }
        return BUSCAR_SOLICITUDES;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
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

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }

    public LinkedList<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(LinkedList<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

}
