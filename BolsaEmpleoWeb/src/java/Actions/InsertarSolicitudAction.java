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

public class InsertarSolicitudAction extends ActionSupport implements SessionAware, Preparable, ModelDriven<Solicitud>, ServletRequestAware {

    //Variables globales
    private Solicitud solicitudInsertar;
    private String mensaje;
    private HttpServletRequest request;
    public SessionMap<String, Object> sessionMap;

    public InsertarSolicitudAction() {
    }

    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        //Inicializa el objeto
        solicitudInsertar = new Solicitud();
    }

    public String insertar() throws DataException {
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        SolicitudBusiness solicitudBusiness = new SolicitudBusiness();
        //Inicializa las variables
        boolean insertado = false;
        try {
            //Llamado al metodo que realiza la insercion
            solicitudBusiness.insertarSolicitud(solicitudInsertar);
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
            this.mensaje = "La solicitud fue registrada exitosamente.";
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
    public Solicitud getModel() {
        return this.solicitudInsertar;
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
    public Solicitud getSolicitudInsertar() {
        return solicitudInsertar;
    }

    public void setSolicitudInsertar(Solicitud solicitudInsertar) {
        this.solicitudInsertar = solicitudInsertar;
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
