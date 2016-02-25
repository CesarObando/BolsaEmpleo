package Actions;

import Business.SolicitudBusiness;
import Dominio.Solicitud;
import Exception.DataException;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

public class EliminarSolicitudEmpleadorAction extends ActionSupport implements SessionAware, Preparable, ModelDriven<Solicitud>, ServletRequestAware {

    private Solicitud solicitudEliminar;
    private String mensaje;
    private HttpServletRequest request;
    private SessionMap<String, Object> sessionMap;

    public EliminarSolicitudEmpleadorAction() {
    }

    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        //Obtiene el objeto en sesion
        solicitudEliminar = (Solicitud) sessionMap.get("solicitud");
    }

    public String eliminar() throws DataException {
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        SolicitudBusiness solicitudBusiness = new SolicitudBusiness();
        //Inicializa la variable
        boolean eliminado = true;
        try {
            //Llamado al metodo que realiza la eliminacion
            solicitudBusiness.eliminarSolicitud(solicitudEliminar.getId());
        } catch (SQLException e) {
            //Asigna valor a la variable
            eliminado = !eliminado;
        }
        if (eliminado) {
            //Define un mensaje que sera mostrado al usuario
            mensaje = "La solicitud fue eliminada correctamente.";
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
    public Solicitud getModel() {
        return this.solicitudEliminar;
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
    public Solicitud getSolicitudEliminar() {
        return solicitudEliminar;
    }

    public void setSolicitudEliminar(Solicitud solicitudEliminar) {
        this.solicitudEliminar = solicitudEliminar;
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
