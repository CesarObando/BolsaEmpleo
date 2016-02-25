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

public class EditarSolicitudAction extends ActionSupport implements Preparable, ModelDriven<Solicitud>, ServletRequestAware, SessionAware {

    private Solicitud solicitudAEditar;
    private String mensaje;
    private HttpServletRequest request;
    private SessionMap<String, Object> sessionMap;

    public EditarSolicitudAction() {
    }

    @Override
    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        //Obtiene el objeto en sesion
        solicitudAEditar = (Solicitud) sessionMap.get("solicitud");

    }

    public String editar() throws DataException {
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        SolicitudBusiness solicitudBusiness = new SolicitudBusiness();
        //Inicializa las variables
        boolean editado = true;
        try {
            //Valida si la solicitud es favorita o no
            if (solicitudAEditar.isFavorito()) {
                //Coloca la solicitud como no favorita
                solicitudAEditar.setFavorito(false);
            } else {
                //Coloca la solicitud como favorita
                solicitudAEditar.setFavorito(true);
            }
            //Llamado al metodo que realiza la edicion
            solicitudBusiness.editarSolicitud(solicitudAEditar);
        } catch (SQLException e) {
            //Asigna valor a la variable
            editado = false;
            //Define un mensaje que sera mostrado al usuario
            mensaje = "Ocurrió un error con la base de datos.Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
            //Coloca en sesion al mensaje
            sessionMap.put("mensaje", mensaje);
            //Coloca el mensaje como mensaje de error
            addActionError(mensaje);
        }
        if (editado == true) {
            //Define un mensaje que sera mostrado al usuario
            this.mensaje = "La solicitud fue editada correctamente";
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
        return this.solicitudAEditar;
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
    public Solicitud getSolicitudAEditar() {
        return solicitudAEditar;
    }

    public void setSolicitudAEditar(Solicitud solicitudAEditar) {
        this.solicitudAEditar = solicitudAEditar;
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
