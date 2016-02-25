package Actions;

import Business.AdministradorBusiness;
import Dominio.Administrador;
import Exception.DataException;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
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

public class EditarAdministradorAction extends ActionSupport implements Preparable, ModelDriven<Administrador>, ServletRequestAware, SessionAware {

    //Variables globales
    private Administrador administradorEditar;
    private String mensaje;
    private HttpServletRequest request;
    public SessionMap<String, Object> sessionMap;

    public EditarAdministradorAction() {
    }

    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        //Inicializa el objeto
        administradorEditar = new Administrador();
        //Obtiene el objeto en sesion
        administradorEditar = (Administrador) sessionMap.get("administrador");
    }
    
    @Override
    public void validate() {
        //Validaciones de los campos de entrada
        if (administradorEditar.getCedula().length() != 9 || administradorEditar.getCedula() == null) {
            addFieldError("cedula", "Debe ingresar un número de identificación válido. Formato de 9 dígitos. Ej.: 000000000");
        }
        if (administradorEditar.getNombre().length() == 0 || administradorEditar.getNombre() == null) {
            addFieldError("nombre", "Debe ingresar su nombre.");
        }
        if (administradorEditar.getApellidos().length() == 0 || administradorEditar.getApellidos() == null) {
            addFieldError("apellidos", "Debe ingresar sus apellidos.");
        }
        if (administradorEditar.getUsername().length() == 0 || administradorEditar.getUsername() == null) {
            addFieldError("username", "Debe ingresar un nombre de usuario.");
        }
        if (administradorEditar.getPassword().length() == 0 || administradorEditar.getPassword() == null) {
            addFieldError("password", "Debe ingresar una contraseña.");
        }
    }

    public String editar() throws DataException {
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        AdministradorBusiness administradorBusiness = new AdministradorBusiness();
        //Inicializa el objeto
        administradorEditar = new Administrador();
        //Obtiene el objeto en sesion
        administradorEditar = (Administrador) sessionMap.get("administrador");
        //Inicializa la variable
        boolean insertado = true;
        try {
            //Llamado al metodo que realiza la edicion
            administradorBusiness.editarAdministrador(administradorEditar);
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
            this.mensaje = "El administrador fue editado correctamente";
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
    public Administrador getModel() {
        return this.administradorEditar;
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
    public Administrador getAdministradorEditar() {
        return administradorEditar;
    }

    public void setAdministradorEditar(Administrador administradorEditar) {
        this.administradorEditar = administradorEditar;
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
