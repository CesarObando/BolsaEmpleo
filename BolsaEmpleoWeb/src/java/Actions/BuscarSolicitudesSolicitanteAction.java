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

public class BuscarSolicitudesSolicitanteAction extends ActionSupport implements Preparable, ServletRequestAware, SessionAware {

    //Variables globales
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
        //Obtiene el objeto en sesion
        solicitante = (Solicitante) sessionMap.get("solicitante");
        //Obtiene el id del objeto
        int idSolicitante = solicitante.getId();
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        SolicitudBusiness solicitudBusiness = new SolicitudBusiness();
        OfertaBusiness ofertaBusiness = new OfertaBusiness();
        EmpleadorBusiness empleadorBusiness = new EmpleadorBusiness();
        //Llamado al metodo que realiza la busqueda
        solicitudes = solicitudBusiness.buscarSolicitudesFiltradas(idSolicitante, 0);
        //Se recorre la lista de objetos y se le asigna a cada uno de ellos los objetos que tenga relacionados para mostrar mayor informacion al usuario
        for (int i = 0; i < solicitudes.size(); i++) {
            //Crea una solicitud a la que se le asignan las propiedades de la solicitud actual de la lista recorrida
            Solicitud solicitud = solicitudes.get(i);
            //Captura el id de la oferta
            int idOferta = solicitud.getOferta().getId();
            //Busca la oferta
            Oferta oferta = ofertaBusiness.buscarOferta(idOferta);
            //Captura el id del empleador
            int idEmpleador = oferta.getEmpleador().getId();
            //Busca el empleador
            Empleador empleador = empleadorBusiness.buscarEmpleador(idEmpleador);
            //Asigna el empleador a la solicitud
            oferta.setEmpleador(empleador);
            //Asigna el solicitante a la solicitud
            solicitud.setSolicitante(solicitante);
            //Asigna la oferta a la solicitud
            solicitud.setOferta(oferta);
            //Asigna la solicitud creada anteriormente a la lista de solicitudes
            solicitudes.set(i, solicitud);
        }
    }

    public String buscar() throws DataException {
        //Obtiene el objeto en sesion
        solicitante = (Solicitante) sessionMap.get("solicitante");
        //Obtiene el id del objeto
        int idSolicitante = solicitante.getId();
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        SolicitudBusiness solicitudBusiness = new SolicitudBusiness();
        OfertaBusiness ofertaBusiness = new OfertaBusiness();
        EmpleadorBusiness empleadorBusiness = new EmpleadorBusiness();
        try {
            //Llamado al metodo que realiza la busqueda
            solicitudes = solicitudBusiness.buscarSolicitudesFiltradas(idSolicitante, 0);
            //Se recorre la lista de objetos y se le asigna a cada uno de ellos los objetos que tenga relacionados para mostrar mayor informacion al usuario
            for (int i = 0; i < solicitudes.size(); i++) {
                //Crea una solicitud a la que se le asignan las propiedades de la solicitud actual de la lista recorrida
                Solicitud solicitud = solicitudes.get(i);
                //Captura el id de la oferta
                int idOferta = solicitud.getOferta().getId();
                //Busca la oferta
                Oferta oferta = ofertaBusiness.buscarOferta(idOferta);
                //Captura el id del empleador
                int idEmpleador = oferta.getEmpleador().getId();
                //Busca el empleador
                Empleador empleador = empleadorBusiness.buscarEmpleador(idEmpleador);
                //Asigna el empleador a la solicitud
                oferta.setEmpleador(empleador);
                //Asigna el solicitante a la solicitud
                solicitud.setSolicitante(solicitante);
                //Asigna la oferta a la solicitud
                solicitud.setOferta(oferta);
                //Asigna la solicitud creada anteriormente a la lista de solicitudes
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
    
    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }
    
    //Setter-Getter
    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
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
