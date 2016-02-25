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

public class VerOfertaSolicitudesFavoritasAction extends ActionSupport implements SessionAware, Preparable, ModelDriven<Oferta>, ServletRequestAware {

    //Variables globales
    private Oferta ofertaAVer;
    private LinkedList<Solicitud> solicitudes;
    private String mensaje;
    private HttpServletRequest request;
    private SessionMap<String, Object> sessionMap;

    public VerOfertaSolicitudesFavoritasAction() {
    }

    @Override
    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        //Captura el id de la oferta
        int idOferta = Integer.parseInt(request.getParameter("id"));
        //Llamado al metodo que realiza la busqueda
        solicitudes = new SolicitudBusiness().buscarSolicitudesFavoritas(idOferta);
        //Se recorre la lista de objetos y se le asigna a cada uno de ellos los objetos que tenga relacionados para mostrar mayor informacion al usuario
        for (int i = 0; i < solicitudes.size(); i++) {
            //Crea una solicitud a la que se le asignan las propiedades de la solicitud actual de la lista recorrida
            Solicitud solicitud = solicitudes.get(i);
            //Busca el solicitante
            Solicitante solicitante = new SolicitanteBusiness().buscarSolicitante(solicitud.getSolicitante().getId());
            //Asigna el solicitante a la solicitud
            solicitud.setSolicitante(solicitante);
            //Asigna la solicitud creada anteriormente a la lista de solicitudes
            solicitudes.set(i, solicitud);
        }
        //Llamado al metodo que realiza la busqueda
        ofertaAVer = new OfertaBusiness().buscarOferta(idOferta);
        //Coloca al objeto en sesion
        sessionMap.put("oferta", ofertaAVer);
    }

    @Override
    public Oferta getModel() {
        return this.ofertaAVer;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
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

    public LinkedList<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(LinkedList<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

}
