package Actions;

import Business.CategoriaBusiness;
import Business.OfertaBusiness;
import Dominio.Empleador;
import Dominio.Oferta;
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

public class InsertarOfertaAction extends ActionSupport implements ModelDriven<Oferta>, Preparable, ServletRequestAware, SessionAware {

    //Variables globales
    private Oferta ofertaAInsertar;
    private LinkedList listaCategorias;
    private String mensaje;
    private HttpServletRequest request;
    public SessionMap<String, Object> sessionMap;
    private Empleador empleador;
    private int idEmpleador;

    public InsertarOfertaAction() {
    }

    @Override
    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        //Obtiene el objeto en sesion
        empleador = (Empleador) sessionMap.get("empleador");
        empleador = (Empleador) request.getSession().getAttribute("empleador");
        //Inicializa el objeto
        ofertaAInsertar = new Oferta();
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        CategoriaBusiness categoriaBusiness = new CategoriaBusiness();
        //Llamado al metodo que realiza la busqueda
        this.listaCategorias = categoriaBusiness.buscarCategorias();
    }

    @Override
    public void validate() {
        //Validaciones de los campos de entrada
        if (ofertaAInsertar.getPuesto().length() == 0) {
            addFieldError("puesto", "Debe ingresar el puesto vacante");
        }
        if (ofertaAInsertar.getRequerimientos().length() == 0) {
            addFieldError("requerimientos", "Debe ingresar los requerimientos del puesto.");
        }
        if (ofertaAInsertar.getCantidadVacantes() < 0) {
            addFieldError("cantidadVacantes", "Debe ingresar una cantidad de vacantes válida.");
        }
        if (ofertaAInsertar.getDescripcion().length() == 0) {
            addFieldError("descripcion", "Debe ingresar una descripción.");
        }
        if (ofertaAInsertar.getCategoria().getId() == -1) {
            addFieldError("categoria.id", "Debe seleccionar una categoría.");
        }
        if (ofertaAInsertar.getProvincia().length() == 0) {
            addFieldError("provincia", "Debe seleccionar una provincia.");
        }
        if (ofertaAInsertar.getCanton().length() == 0) {
            addFieldError("canton", "Debe seleccionar un cantón.");
        }
    }

    public String insertar() throws DataException {
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        OfertaBusiness ofertaBusiness = new OfertaBusiness();
        //Inicializa las variables
        boolean insertado = true;
        try {
            //Asigna el empleador a la oferta
            ofertaAInsertar.setEmpleador(empleador);
            //Llamado al metodo que realiza la insercion
            ofertaBusiness.insertaOferta(ofertaAInsertar);
        } catch (SQLException e) {
            //Asigna valor a la variable
            insertado = false;
            //Define un mensaje que sera mostrado al usuario
            mensaje = "Ocurrió un error con la base de datos. Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
            //Coloca en sesion al mensaje
            sessionMap.put("mensaje", mensaje);
            //Coloca el mensaje como mensaje de error
            addActionError(mensaje);
        }
        if (insertado == true) {
            //Define un mensaje que sera mostrado al usuario
            this.mensaje = "La  oferta  fue insertada correctamente";
            //Coloca en sesion al mensaje
            sessionMap.put("mensaje", mensaje);
            //Coloca el mensaje como mensaje del action
            addActionMessage(mensaje);
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    @Override
    public Oferta getModel() {
        return this.ofertaAInsertar;
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
    public Oferta getOfertaAInsertar() {
        return ofertaAInsertar;
    }

    public void setOfertaAInsertar(Oferta ofertaAInsertar) {
        this.ofertaAInsertar = ofertaAInsertar;
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

    public LinkedList getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(LinkedList listaCategorias) {
        this.listaCategorias = listaCategorias;
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

}
