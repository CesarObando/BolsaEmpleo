/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.SolicitudBusiness;
import Dominio.Solicitante;
import Dominio.Solicitud;
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
public class VerPerfilSolicitanteAction extends ActionSupport implements Preparable, ModelDriven<Solicitante>, ServletRequestAware, SessionAware {

    private Solicitante solicitante;
    private String mensaje;
    private int numFavoritos;
    private int numSolicitudes;
    private HttpServletRequest request;
    public SessionMap<String, Object> sessionMap;

    public VerPerfilSolicitanteAction() {
    }

    @Override
    public String execute() {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        solicitante = (Solicitante) sessionMap.get("solicitante");
        solicitante = (Solicitante) request.getSession().getAttribute("solicitante");
        getFavoritos();
    }

    public void getFavoritos() throws SQLException {
        numFavoritos = 0;
        LinkedList<Solicitud> solicitudes = new SolicitudBusiness().buscarSolicitudesFiltradas(solicitante.getId(), 0);
        numSolicitudes = solicitudes.size();
        for (Solicitud solicitud : solicitudes) {
            if(solicitud.isFavorito()){
                numFavoritos++;
            }
        }
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    @Override
    public void validate() {

    }

    @Override
    public Solicitante getModel() {
        return this.solicitante;
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

    public int getNumFavoritos() {
        return numFavoritos;
    }

    public void setNumFavoritos(int numFavoritos) {
        this.numFavoritos = numFavoritos;
    }

    public int getNumSolicitudes() {
        return numSolicitudes;
    }

    public void setNumSolicitudes(int numSolicitudes) {
        this.numSolicitudes = numSolicitudes;
    }

}
