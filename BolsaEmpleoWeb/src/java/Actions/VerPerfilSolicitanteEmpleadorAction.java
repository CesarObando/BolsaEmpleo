/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.EmpleadorBusiness;
import Business.OfertaBusiness;
import Business.SolicitanteBusiness;
import Business.SolicitudBusiness;
import Dominio.Empleador;
import Dominio.Oferta;
import Dominio.Solicitante;
import Dominio.Solicitud;
import Exception.DataException;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
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
public class VerPerfilSolicitanteEmpleadorAction extends ActionSupport implements SessionAware, Preparable, ModelDriven<Solicitante>, ServletRequestAware {

    private Solicitante solicitanteAVer;
    private String mensaje;
    private boolean existe;
    private HttpServletRequest request;
    private SessionMap<String, Object> sessionMap;
    private int idSolicitud;

    public VerPerfilSolicitanteEmpleadorAction() {
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
        idSolicitud = Integer.parseInt(request.getParameter("id"));
        Solicitud solicitud = new SolicitudBusiness().buscarSolicitud(idSolicitud);
        sessionMap.put("solicitud", solicitud);

        int idSolicitante = solicitud.getSolicitante().getId();
        solicitanteAVer = new SolicitanteBusiness().buscarSolicitante(idSolicitante);
    }

    @Override
    public Solicitante getModel() {
        return this.solicitanteAVer;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public Solicitante getSolicitanteAVer() {
        return solicitanteAVer;
    }

    public void setSolicitanteAVer(Solicitante solicitanteAVer) {
        this.solicitanteAVer = solicitanteAVer;
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

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

}
