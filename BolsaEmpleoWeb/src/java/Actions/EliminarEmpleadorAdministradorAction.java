package Actions;

import Business.EmpleadorBusiness;
import Dominio.Empleador;
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

public class EliminarEmpleadorAdministradorAction extends ActionSupport implements SessionAware, Preparable, ModelDriven<Empleador>, ServletRequestAware {

    //Variables globales
    private Empleador empleadorAEmpleador;
    private String mensaje;
    private HttpServletRequest request;
    private SessionMap<String, Object> sessionMap;

    public EliminarEmpleadorAdministradorAction() {
    }

    @Override
    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        //Obtiene el id del objeto a eliminar
        int idEmpleador = Integer.parseInt(request.getParameter("id"));
        //Llamado al metodo que realiza la busqueda
        empleadorAEmpleador = new EmpleadorBusiness().buscarEmpleador(idEmpleador);
    }

    public String eliminar() throws DataException, SQLException {
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        EmpleadorBusiness empleadorBusiness = new EmpleadorBusiness();
        //Obtiene el id del objeto a eliminar
        int idEmpleador = Integer.parseInt(request.getParameter("id"));
        //Llamado al metodo que realiza la busqueda
        empleadorAEmpleador = new EmpleadorBusiness().buscarEmpleador(idEmpleador);
        //Inicializa la variable
        boolean eliminado = true;
        try {
            //Llamado al metodo que realiza la eliminacion
            empleadorBusiness.eliminarEmpleador(empleadorAEmpleador.getId());
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
        return this.empleadorAEmpleador;
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
    public Empleador getEmpleadorAEmpleador() {
        return empleadorAEmpleador;
    }

    public void setEmpleadorAEmpleador(Empleador empleadorAEmpleador) {
        this.empleadorAEmpleador = empleadorAEmpleador;
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
