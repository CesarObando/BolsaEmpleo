package Actions;

import Business.CategoriaBusiness;
import Business.EmpleadorBusiness;
import Business.OfertaBusiness;
import Dominio.Empleador;
import Dominio.Oferta;
import Exception.DataException;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

public class BuscarOfertasSolicitanteAction extends ActionSupport implements Preparable, ServletRequestAware {

    //Variables globales
    private final String BUSCAR_OFERTAS = "buscarOfertasSolicitante";
    private LinkedList<Oferta> ofertas;
    private HttpServletRequest request;
    private String puesto;
    private int categoria;
    private String provincia;
    private String canton;
    private LinkedList categorias;

    public BuscarOfertasSolicitanteAction() {
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
        OfertaBusiness ofertaBusiness = new OfertaBusiness();
        EmpleadorBusiness empleadorBusiness = new EmpleadorBusiness();
        //Captura de los campos de busqueda en el jsp
        puesto = request.getParameter("puesto");
        categoria = -1;
        provincia = "";
        canton = "";
        //Si el usuario selecciona una opcion del select
        if (request.getParameter("categoria.id") != null) {
            categoria = Integer.parseInt(request.getParameter("categoria.id"));
        }
        if (request.getParameter("provincia") != null) {
            provincia = request.getParameter("provincia");
        }
        if (request.getParameter("canton") != null) {
            canton = request.getParameter("canton");
        }
        try {
            //Llamado al metodo que realiza la busqueda
            ofertas = ofertaBusiness.buscarOfertasPorCategoria(categoria, puesto, provincia, canton);
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
            Logger.getLogger(BuscarOfertasSolicitanteAction.class.getName()).log(Level.SEVERE, null, e);
        }
        return BUSCAR_OFERTAS;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }
    
    //Setter-getter
    public String getNombre() {
        return puesto;
    }

    public void setNombre(String nombre) {
        this.puesto = nombre;
    }

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

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

}
