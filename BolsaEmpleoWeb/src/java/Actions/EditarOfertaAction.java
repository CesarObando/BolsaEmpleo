package Actions;

import Business.CategoriaBusiness;
import Business.OfertaBusiness;
import Dominio.Categoria;
import Dominio.Oferta;
import Exception.DataException;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
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

public class EditarOfertaAction extends ActionSupport implements SessionAware, Preparable, ModelDriven<Oferta>, ServletRequestAware {

    //Variables globales
    private Oferta ofertaAEditar;
    private LinkedList<Categoria> categorias;
    private String mensaje;
    private HttpServletRequest request;
    private SessionMap<String, Object> sessionMap;

    public EditarOfertaAction() {
    }

    @Override
    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        //Obtiene el objeto en sesion
        ofertaAEditar = (Oferta) sessionMap.get("oferta");
        //Llamado al metodo que realiza la busqueda
        categorias = new CategoriaBusiness().buscarCategorias();

    }

    @Override
    public void validate() {
        //Validaciones de los campos de entrada
        if (ofertaAEditar.getPuesto().length() == 0) {
            addFieldError("puesto", "Debe ingresar el puesto vacante");
        }
        if (ofertaAEditar.getRequerimientos().length() == 0) {
            addFieldError("requerimientos", "Debe ingresar los requerimientos del puesto.");
        }
        if (ofertaAEditar.getCantidadVacantes() < 0) {
            addFieldError("cantidadVacantes", "Debe seleccionar una catidad de vacantes");
        }
        if (ofertaAEditar.getDescripcion().length() == 0) {
            addFieldError("descripcion", "Debe ingresar una descripción del puesto.");
        }
        if (ofertaAEditar.getCategoria().getId() == -1) {
            addFieldError("categoria.id", "Debe seleccionar una categoría.");
        }
        if (ofertaAEditar.getProvincia().length() == 0) {
            addFieldError("provincia", "Debe seleccionar una provincia.");
        }
        if (ofertaAEditar.getCanton().length() == 0) {
            addFieldError("canton", "Debe seleccionar un cantón.");
        }
    }

    public String editar() throws DataException {
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        OfertaBusiness ofertaBusiness = new OfertaBusiness();
        //Inicializa las variables
        boolean editado = true;
        try {
            //Llamado al metodo que realiza la edicion
            ofertaBusiness.editarOferta(ofertaAEditar);
        } catch (SQLException e) {
            //Asigna valor a la variable
            editado = false;
            //Define un mensaje que sera mostrado al usuario
            mensaje = "Ocurrió un error con la base de datos. Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
            //Coloca en sesion al mensaje
            sessionMap.put("mensaje", mensaje);
            //Coloca el mensaje como mensaje de error
            addActionError(mensaje);
        }
        if (editado == true) {
            //Define un mensaje que sera mostrado al usuario
            this.mensaje = "La oferta fue editada correctamente";
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
        return this.ofertaAEditar;
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
    public Oferta getOfertaAEditar() {
        return ofertaAEditar;
    }

    public void setOfertaAEditar(Oferta ofertaAEditar) {
        this.ofertaAEditar = ofertaAEditar;
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

    public LinkedList<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(LinkedList<Categoria> categorias) {
        this.categorias = categorias;
    }

}
