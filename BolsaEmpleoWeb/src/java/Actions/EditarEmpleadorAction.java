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
import java.util.LinkedList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

public class EditarEmpleadorAction extends ActionSupport implements Preparable, ModelDriven<Empleador>, ServletRequestAware, SessionAware {

    //Variables globales
    private Empleador empleadorEditar;
    private String mensaje;
    private HttpServletRequest request;
    public SessionMap<String, Object> sessionMap;

    public EditarEmpleadorAction() {
    }

    @Override
    public String execute() {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        //Inicializa el objeto
        empleadorEditar = new Empleador();
        //Obtiene el objeto en sesion
        empleadorEditar = (Empleador) sessionMap.get("empleador");
    }

    @Override
    public void validate() {
        //Validaciones de los campos de entrada
        if (empleadorEditar.getNombre().length() == 0) {
            addFieldError("nombre", "Debe ingresar su nombre.");
        }
        if (empleadorEditar.getApellidos().length() == 0) {
            addFieldError("apellidos", "Debe ingresar sus apellidos.");
        }
        if (empleadorEditar.getUsername().length() == 0) {
            addFieldError("username", "Debe ingresar un nombre de usuario.");
        }
        if (empleadorEditar.getPassword().length() < 6) {
            addFieldError("pass", "La contraseña debe ser mayor a 6 caracteres");
        }
    }

    public String editar() throws DataException {
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        EmpleadorBusiness empleadorBusiness = new EmpleadorBusiness();
        //Inicializa las variables
        boolean insertado = true;
        boolean existe = false;
        try {
            //Define una lista de objetos
            LinkedList<Empleador> empleadores = new EmpleadorBusiness().buscarEmpleadores();
            int i=0;
            //Recorre la lista
            while (existe == false && i< empleadores.size()) {
                Empleador empleador = empleadores.get(i);
                //Valida que el correo que se esta editando no exista
                if (empleadorEditar.getCorreo().equals(empleador.getCorreo()) && empleadorEditar.getId() != empleador.getId()) {
                    existe = true;
                }
                i++;
            }
            //Si no existe el correo
            if (existe == false) {
                //Llamado al metodo que realiza la edicion
                empleadorBusiness.editarEmpleador(empleadorEditar);
            } else {
                //Asigna valor a la variable
                insertado = false;
                //Define un mensaje que sera mostrado al usuario
                mensaje = "El correo ya se encuentran registrado. Inténtelo nuevamente.";
                //Coloca en sesion al mensaje
                sessionMap.put("mensaje", mensaje);
                //Coloca el mensaje como mensaje de error
                addActionError(mensaje);
                return ERROR;
            }
        } catch (SQLException e) {
            //Asigna valor a la variable
            insertado = false;
            //Define un mensaje que sera mostrado al usuario
            mensaje = "Ocurrió un error con la base de datos.Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
            //Coloca en sesion al mensaje
            sessionMap.put("mensaje", mensaje);
            //Coloca el mensaje como mensaje de error
            addActionError(mensaje);
        }
        if (insertado == true) {
            //Define un mensaje que sera mostrado al usuario
            this.mensaje = "El empleador fue editado correctamente";
            //Coloca en sesion al mensaje
            sessionMap.put("mensaje", mensaje);
            //Coloca el mensaje como mensaje del action
            addActionMessage(mensaje);
            //Coloca en sesion al objeto
            sessionMap.put("empleador", empleadorEditar);
            return SUCCESS;
        } else {
            return ERROR;
        }
    }
    
    @Override
    public Empleador getModel() {
        return this.empleadorEditar;
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

    public Empleador getEmpleadorEditar() {
        return empleadorEditar;
    }

    public void setEmpleadorEditar(Empleador empleadorEditar) {
        this.empleadorEditar = empleadorEditar;
    }
}
