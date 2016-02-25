package Actions;

import Business.CategoriaBusiness;
import Business.EmpleadorBusiness;
import Business.OfertaBusiness;
import Business.SolicitanteBusiness;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

public class BuscarSolicitudesAdministradorAction extends ActionSupport implements Preparable, ServletRequestAware {

    //Variables globales
    private final String BUSCAR_SOLICITUDES = "buscarSolicitudesAdministrador";
    private LinkedList<Solicitud> solicitudes;
    private HttpServletRequest request;
    private String puesto;
    private int categoria;
    private LinkedList categorias;

    public BuscarSolicitudesAdministradorAction() {
    }

    @Override
    public String execute() throws Exception {
        return ActionSupport.SUCCESS;
    }

    @Override
    public void prepare() throws Exception {
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        CategoriaBusiness categoriaBusiness = new CategoriaBusiness();
        //Llamado al metodo que realiza la busqueda
        this.categorias = categoriaBusiness.buscarCategorias();
    }

    public String buscar() throws DataException {
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        SolicitudBusiness solicitudBusiness = new SolicitudBusiness();
        EmpleadorBusiness empleadorBusiness = new EmpleadorBusiness();
        OfertaBusiness ofertaBusiness = new OfertaBusiness();
        SolicitanteBusiness solicitanteBusiness = new SolicitanteBusiness();
        //Captura de los campos de busqueda en el jsp
        puesto = request.getParameter("puesto");
        categoria = -1;
        //Si el usuario selecciona una opcion del select
        if (request.getParameter("categoria.id") != null) {
            categoria = Integer.parseInt(request.getParameter("categoria.id"));
        }
        try {
            //Llamado al metodo que realiza la busqueda
            solicitudes = solicitudBusiness.buscarSolicitudesPorCategoria(categoria, puesto);
            //Se recorre la lista de objetos y se le asigna a cada uno de ellos los objetos que tenga relacionados para mostrar mayor informacion al usuario
            for (int i = 0; i < solicitudes.size(); i++) {
                //Crea una solicitud a la que se le asignan las propiedades de la solicitud actual de la lista recorrida
                Solicitud solicitud = solicitudes.get(i);
                //Captura el id del solicitante
                int idSolicitante = solicitud.getSolicitante().getId();
                //Captura el id de la oferta
                int idOferta = solicitud.getOferta().getId();
                //Busca el solicitante
                Solicitante solicitante = solicitanteBusiness.buscarSolicitante(idSolicitante);
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
            Logger.getLogger(BuscarSolicitudesAdministradorAction.class.getName()).log(Level.SEVERE, null, e);
        }
        return BUSCAR_SOLICITUDES;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }
    
    //Setter-Getter
    public String getNombre() {
        return puesto;
    }

    public void setNombre(String nombre) {
        this.puesto = nombre;
    }

    public LinkedList<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(LinkedList<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public LinkedList getCategorias() {
        return categorias;
    }

    public void setCategorias(LinkedList categorias) {
        this.categorias = categorias;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

}
