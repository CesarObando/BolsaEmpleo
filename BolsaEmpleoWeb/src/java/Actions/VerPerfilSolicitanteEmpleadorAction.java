/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.SolicitanteBusiness;
import Business.SolicitudBusiness;
import Dominio.Solicitante;
import Dominio.Solicitud;
import static com.opensymphony.xwork2.Action.ERROR;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.sql.Connection;
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
public class VerPerfilSolicitanteEmpleadorAction extends ActionSupport implements Preparable, ModelDriven<Solicitante>, ServletRequestAware, SessionAware {

    private Solicitante solicitanteAVer;
    private String mensaje;
    private boolean existe;
    private HttpServletRequest request;
    private Connection conexion;
    private Map parametros;
    private SessionMap<String, Object> sessionMap;

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

        int idSolicitud = Integer.parseInt(request.getParameter("id"));
        Solicitud solicitud = new SolicitudBusiness().buscarSolicitud(idSolicitud);
        int idSolicitante = solicitud.getSolicitante().getId();

        try {
            solicitanteAVer = new SolicitanteBusiness().buscarSolicitante(idSolicitante);
            sessionMap.put("solicitante", solicitanteAVer);
        } catch (SQLException e) {
            existe = false;
        }

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
        this.sessionMap = (SessionMap<String, Object>) map; //To change body of generated methods, choose Tools | Templates.
    }

}
