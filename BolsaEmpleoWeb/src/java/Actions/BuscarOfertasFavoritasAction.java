package Actions;

import Business.EmpleadorBusiness;
import Business.OfertaBusiness;
import Dominio.Empleador;
import Dominio.Oferta;
import Dominio.Solicitante;
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

public class BuscarOfertasFavoritasAction extends ActionSupport implements Preparable, ServletRequestAware, SessionAware {

    //Variables globales
    private final String BUSCAR_OFERTAS = "buscarOfertasFavoritas";
    private LinkedList<Oferta> ofertas;
    private HttpServletRequest request;
    private SessionMap<String, Object> sessionMap;

    public BuscarOfertasFavoritasAction() {
    }

    @Override
    public String execute() throws Exception {
        return ActionSupport.SUCCESS;
    }

    @Override
    public void prepare() throws Exception {
    }

    public String buscar() throws DataException {
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        OfertaBusiness ofertaBusiness = new OfertaBusiness();
        EmpleadorBusiness empleadorBusiness = new EmpleadorBusiness();
        try {
            //Llamado al metodo que realiza la busqueda con el objeto en sesion
            ofertas = ofertaBusiness.buscarOfertasFavoritas((Solicitante) sessionMap.get("solicitante"));
            //Se recorre la lista de objetos y se le asigna a cada uno de ellos los objetos que tenga relacionados para mostrar mayor informacion al usuario
            for (int i = 0; i < ofertas.size(); i++) {
                //Crea una oferta a la que se le asignan las propiedades de la oferta actual de la lista recorrida
                Oferta oferta = ofertas.get(i);
                //Captura el id del empleador
                int idEmpleador = oferta.getEmpleador().getId();
                //Busca el empleador
                Empleador empleador = empleadorBusiness.buscarEmpleador(idEmpleador);
                //Asigna el empleador a la oferta
                oferta.setEmpleador(empleador);
                //Asigna la oferta creada anteriormente a la lista de ofertas
                ofertas.set(i, oferta);
            }
        } catch (SQLException e) {
            Logger.getLogger(BuscarOfertasFavoritasAction.class.getName()).log(Level.SEVERE, null, e);
        }
        return BUSCAR_OFERTAS;
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
    public LinkedList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(LinkedList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

}
