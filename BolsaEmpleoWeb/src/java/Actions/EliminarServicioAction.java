package Actions;

import Business.ServicioBusiness;
import Dominio.Servicio;
import Exception.DataException;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

public class EliminarServicioAction extends ActionSupport implements SessionAware, Preparable, ModelDriven<Servicio>, ServletRequestAware {

    //Variables globales
    private Servicio servicioAEliminar;
    private String mensaje;
    private HttpServletRequest request;
    private SessionMap<String, Object> sessionMap;

    public EliminarServicioAction() {
    }

    @Override
    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        //Obtiene el objeto en sesion
        servicioAEliminar = (Servicio) sessionMap.get("servicio");
    }

    public String eliminar() throws SQLException, DataException {
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        ServicioBusiness servicioBusiness = new ServicioBusiness();
        //Inicializa la variable
        boolean eliminado = true;
        try {
            //Llamado al metodo que realiza la eliminacion
            servicioBusiness.eliminarServicio(servicioAEliminar.getId());
        } catch (SQLException e) {
            //Asigna valor a la variable
            eliminado = !eliminado;
        }
        if (eliminado) {
            //Define un mensaje que sera mostrado al usuario
            mensaje = "El servicio fue eliminado correctamente.";
            //Coloca en sesion al mensaje
            sessionMap.put("mensaje", mensaje);
            //Coloca el mensaje como mensaje del action
            addActionMessage(mensaje);
            return SUCCESS;
        } else {
            //Define un mensaje que sera mostrado al usuario
            mensaje = "Ocurrió un problema al eliminar.";
            //Coloca en sesion al mensaje
            sessionMap.put("mensaje", mensaje);
            //Coloca el mensaje como mensaje de error
            addActionError(mensaje);
            return ERROR;
        }
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    @Override
    public Servicio getModel() {
        return this.servicioAEliminar;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

    public Servicio getServicioAEliminar() {
        return servicioAEliminar;
    }

    public void setServicioAEliminar(Servicio servicioAEliminar) {
        this.servicioAEliminar = servicioAEliminar;
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
