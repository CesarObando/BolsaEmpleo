package Actions;

import Business.SolicitanteBusiness;
import Business.SolicitudBusiness;
import Dominio.Solicitante;
import Dominio.Solicitud;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

public class VerPerfilSolicitanteEmpleadorAction extends ActionSupport implements Preparable, ModelDriven<Solicitante>, ServletRequestAware, SessionAware {

    //Variables globales
    private Solicitante solicitanteAVer;
    private String mensaje;
    private HttpServletRequest request;
    private SessionMap<String, Object> sessionMap;

    public VerPerfilSolicitanteEmpleadorAction() {
    }

    @Override
    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        //Captura el id de la solicitud
        int idSolicitud = Integer.parseInt(request.getParameter("idS"));
        //Busca la solicitud
        Solicitud solicitud = new SolicitudBusiness().buscarSolicitud(idSolicitud);
        //Captura el id del solicitante
        int idSolicitante = solicitud.getSolicitante().getId();
        //Busca al solicitante
        solicitanteAVer = new SolicitanteBusiness().buscarSolicitante(idSolicitante);
        //Coloca en sesion al solicitante
        sessionMap.put("solicitanteO", solicitanteAVer);
        //Coloca en sesion a la solicitud
        sessionMap.put("solicitud", solicitud);
    }

    @Override
    public Solicitante getModel() {
        return this.solicitanteAVer;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr
    ) {
        this.request = hsr;
    }
    
    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

    //Setter-Getter
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

}
