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
import static com.opensymphony.xwork2.Action.ERROR;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Tin
 */
public class VerOfertaSolicitudesFavoritasAction extends ActionSupport implements SessionAware, Preparable, ModelDriven<Oferta>, ServletRequestAware {

    private Oferta ofertaAVer;
    private LinkedList<Solicitud> solicitudes;
    private String mensaje;
    private boolean existe;
    private HttpServletRequest request;
    private SessionMap<String, Object> sessionMap;

    public VerOfertaSolicitudesFavoritasAction() {
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
        int idOferta = Integer.parseInt(request.getParameter("id"));
        try {
            solicitudes = new SolicitudBusiness().buscarSolicitudesFavoritas(idOferta);
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

}
