package Actions;

import Business.CategoriaBusiness;
import Business.OfertaBusiness;
import Dominio.Empleador;
import Dominio.Oferta;
import Exception.DataException;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
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

public class BuscarOfertasAction extends ActionSupport implements Preparable, ServletRequestAware, ModelDriven<Empleador>, SessionAware {

    //Variables globales
    private final String BUSCAR_OFERTAS = "buscarOfertas";
    private LinkedList<Oferta> ofertas;
    private HttpServletRequest request;
    private String puesto;
    private int categoria;
    private String provincia;
    private String canton;
    private int idEmpleador;
    private LinkedList categorias;
    private Empleador empleador;
    public SessionMap<String, Object> sessionMap;

    public BuscarOfertasAction() {
    }

    @Override
    public String execute() throws Exception {
        return ActionSupport.SUCCESS;
    }

    @Override
    public void prepare() throws Exception {
        //Obtiene el objeto en sesion
        empleador = (Empleador) sessionMap.get("empleador");
        empleador = (Empleador) request.getSession().getAttribute("empleador");
        //Obtiene el id del objeto
        idEmpleador = empleador.getId();
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        CategoriaBusiness categoriaBusiness = new CategoriaBusiness();
        //Llamado al metodo que realiza la busqueda
        this.categorias = categoriaBusiness.buscarCategorias();
    }

    public String buscar() throws DataException {
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        OfertaBusiness ofertaBusiness = new OfertaBusiness();
        //Captura de los campos de busqueda en el jsp
        puesto = request.getParameter("puesto");
        //Si el usuario no selecciona ninguna opcion del select
        if (request.getParameter("categoria.id")==null) {
            categoria=-1;
        }
        else{ 
            //Si el usuario selecciona una opcion del select
            categoria = Integer.parseInt(request.getParameter("categoria.id"));
        }
        //Si el usuario no selecciona ninguna opcion del select
        if (request.getParameter("provincia")==null) {
            provincia="";
        }
        else{
            //Si el usuario selecciona una opcion del select
            provincia = request.getParameter("provincia");
        }
        //Si el usuario no selecciona ninguna opcion del select
        if (request.getParameter("canton")==null) {
            canton="";
        }
        else{
            //Si el usuario selecciona una opcion del select
            canton = request.getParameter("canton");
        }
        //Obtiene el id del objeto
        idEmpleador = empleador.getId();
        try {
            //Llamado al metodo que realiza la busqueda
            ofertas = ofertaBusiness.buscarOfertasPorEmpleador(categoria, puesto, idEmpleador, provincia,canton);
        } catch (SQLException e) {
            Logger.getLogger(BuscarOfertasAction.class.getName()).log(Level.SEVERE, null, e);
        }
        return BUSCAR_OFERTAS;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }
    
    @Override
    public Empleador getModel() {
        return this.empleador;
    }
    
    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }
    
    //Setter-Getter
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

    public Empleador getEmpleador() {
        return empleador;
    }

    public void setEmpleador(Empleador empleador) {
        this.empleador = empleador;
    }

    public int getIdEmpleador() {
        return idEmpleador;
    }

    public void setIdEmpleador(int idEmpleador) {
        this.idEmpleador = idEmpleador;
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
