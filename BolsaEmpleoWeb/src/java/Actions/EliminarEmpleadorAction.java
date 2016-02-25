package Actions;

import Business.EmpleadorBusiness;
import Dominio.Empleador;
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

public class EliminarEmpleadorAction extends ActionSupport implements Preparable, ModelDriven<Empleador>, ServletRequestAware, SessionAware {

    //Variables globales
    private Empleador empleadorAEliminar;
    private String mensaje;
    private HttpServletRequest request;
    public SessionMap<String, Object> sessionMap;

    public EliminarEmpleadorAction() {
    }

    @Override
    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        //Inicializa el objeto
        empleadorAEliminar = new Empleador();
        //Obtiene el objeto en sesion
        empleadorAEliminar = (Empleador) sessionMap.get("empleador");
    }

    @Override
    public void validate() {

    }

    public String eliminar() throws DataException, SQLException {
        //Inicializa la variable
        boolean eliminado = true;
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        EmpleadorBusiness empleadorBusiness = new EmpleadorBusiness();
        //Inicializa el objeto
        empleadorAEliminar = new Empleador();
        //Obtiene el objeto en sesion
        empleadorAEliminar = (Empleador) sessionMap.get("empleador");
        //Obtiene el id del objeto a eliminar
        int idEmpleador = empleadorAEliminar.getId();
        try {
            //Llamado al metodo que realiza la eliminacion
            empleadorBusiness.eliminarEmpleador(idEmpleador);
            } catch (SQLException e) {
            //Asigna valor a la variable
            eliminado = !eliminado;
        }
        if (eliminado) {
            //Define un mensaje que sera mostrado al usuario
            mensaje = "El empleador fue eliminada correctamente.";
            //Coloca en sesion al mensaje
            sessionMap.put("mensaje", mensaje);
            //Coloca el mensaje como mensaje del action
            addActionMessage(mensaje);
            //Elimina la sesion del objeto
            sessionMap.remove("empleador");
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
    public Empleador getModel() {
        return this.empleadorAEliminar;
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
    public Empleador getEmpleadorAEliminar() {
        return empleadorAEliminar;
    }

    public void setEmpleadorAEliminar(Empleador empleadorAEliminar) {
        this.empleadorAEliminar = empleadorAEliminar;
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
