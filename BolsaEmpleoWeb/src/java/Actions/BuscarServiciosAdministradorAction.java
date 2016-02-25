package Actions;

import Business.CategoriaBusiness;
import Business.ServicioBusiness;
import Business.SolicitanteBusiness;
import Dominio.Servicio;
import Dominio.Solicitante;
import Exception.DataException;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

public class BuscarServiciosAdministradorAction extends ActionSupport implements Preparable, ServletRequestAware {

    //Variables globales
    private final String BUSCAR_SERVICIOS = "buscarServiciosAdministrador";
    private LinkedList<Servicio> servicios;
    private HttpServletRequest request;
    private String titulo;
    private int categoria;
    private String provincia;
    private String canton;
    private LinkedList categorias;

    public BuscarServiciosAdministradorAction() {
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
        ServicioBusiness servicioBusiness = new ServicioBusiness();
        SolicitanteBusiness solicitanteBusiness = new SolicitanteBusiness();
        //Captura de los campos de busqueda en el jsp
        titulo = request.getParameter("titulo");
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
            servicios = servicioBusiness.buscarServiciosFiltrados(categoria, titulo, provincia,canton);
            //Se recorre la lista de objetos y se le asigna a cada uno de ellos los objetos que tenga relacionados para mostrar mayor informacion al usuario
            for (int i = 0; i < servicios.size(); i++) {
                //Crea un servicio al que se le asignan las propiedades del servicio actual de la lista recorrida
                Servicio servicio = servicios.get(i);
                //Captura el id del solicitante
                int idSolicitante = servicio.getSolicitante().getId();
                //Busca el solicitante
                Solicitante solicitante = solicitanteBusiness.buscarSolicitante(idSolicitante);
                //Asigna el solicitante al servicio
                servicio.setSolicitante(solicitante);
                //Asigna el servicio creado anteriormente a la lista de servicios
                servicios.set(i, servicio);
            }
        } catch (SQLException e) {
            Logger.getLogger(BuscarServiciosAdministradorAction.class.getName()).log(Level.SEVERE, null, e);
        }
        return BUSCAR_SERVICIOS;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    //Setter-Getter
    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public LinkedList<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(LinkedList<Servicio> servicios) {
        this.servicios = servicios;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public LinkedList getCategorias() {
        return categorias;
    }

    public void setCategorias(LinkedList categorias) {
        this.categorias = categorias;
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
