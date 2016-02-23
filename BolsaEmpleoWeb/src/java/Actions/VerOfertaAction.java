/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.OfertaBusiness;
import Business.SolicitanteBusiness;
import Business.SolicitudBusiness;
import Dominio.Oferta;
import Dominio.Solicitante;
import Dominio.Solicitud;
import Exception.DataException;
import static com.opensymphony.xwork2.Action.ERROR;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
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
 * @author Tin
 */
public class VerOfertaAction extends ActionSupport implements SessionAware, Preparable, ModelDriven<Oferta>, ServletRequestAware {

    private Oferta ofertaAVer;
    private LinkedList<Solicitud> solicitudes;
    private String mensaje;
    private boolean existe;
    private HttpServletRequest request;
    private SessionMap<String, Object> sessionMap;
    private int idOferta;

    public VerOfertaAction() {
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
        idOferta = Integer.parseInt(request.getParameter("id"));
        try {
            solicitudes = new SolicitudBusiness().buscarSolicitudesFiltradas(0, idOferta);
            for (int i = 0; i < solicitudes.size(); i++) {
                Solicitud solicitud = new Solicitud();
                solicitud = solicitudes.get(i);
                Solicitante solicitante = new Solicitante();
                solicitante = new SolicitanteBusiness().buscarSolicitante(solicitud.getSolicitante().getId());
                solicitud.setSolicitante(solicitante);
                solicitudes.set(i, solicitud);
            }
            ofertaAVer = new OfertaBusiness().buscarOferta(idOferta);
            sessionMap.put("oferta", ofertaAVer);
        } catch (SQLException e) {
            existe = false;
        }
    }

    public String buscarSolicitudesFavoritas() throws SQLException {
        try {
            solicitudes = new SolicitudBusiness().buscarSolicitudesFavoritas(Integer.parseInt(request.getParameter("id")));
            for (int i = 0; i < solicitudes.size(); i++) {
                Solicitud solicitud = new Solicitud();
                solicitud = solicitudes.get(i);
                Solicitante solicitante = new Solicitante();
                solicitante = new SolicitanteBusiness().buscarSolicitante(solicitud.getSolicitante().getId());
                solicitud.setSolicitante(solicitante);
                solicitudes.set(i, solicitud);
            }
            return SUCCESS;
        } catch (DataException ex) {
            Logger.getLogger(VerOfertaAction.class.getName()).log(Level.SEVERE, null, ex);
            return ERROR;
        }
    }

    @Override
    public Oferta getModel() {
        return this.ofertaAVer;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public Oferta getOfertaAEliminar() {
        return ofertaAVer;
    }

    public void setOfertaAEliminar(Oferta ofertaAEliminar) {
        this.ofertaAVer = ofertaAEliminar;
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

    public LinkedList<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(LinkedList<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

}
