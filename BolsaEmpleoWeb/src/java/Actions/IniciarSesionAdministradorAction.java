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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

public class IniciarSesionAdministradorAction extends ActionSupport implements Preparable, ModelDriven<Administrador>, SessionAware, ServletRequestAware {

    private HttpServletRequest request;
    public SessionMap<String, Object> sessionMap;
    private Administrador administrador;
    private String nombreUsuario;
    private String clave;
    private String mensaje;

    @Override
    public void prepare() throws Exception {
        //Inicializa el objeto
        administrador = new Administrador();
    }

    @Override
    public String execute() throws Exception {
        // Si no hay una sesion iniciada
        if (this.sessionMap.get("administrador") == null) {
            return INPUT;
        } else {
            this.addActionError("Ya se ha iniciado una sesion en el sistema");
            return ERROR;
        }
    }

    public String iniciarSesionAdministrador() {
        //Limpia el mapa de sesiones
        sessionMap.clear();
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        AdministradorBusiness administradorBusiness = new AdministradorBusiness();
        try {
            //Llamado al metodo que realiza la busqueda para iniciar sesion
            administrador = administradorBusiness.iniciarSesion(nombreUsuario, clave);
            //Si no hay resultado de busqueda
            if (administrador.getId() == 0) {
                this.addFieldError("nombreUsuario", "Usuario o contraseña incorrectas");
                return ERROR;
            }
            //Define un mensaje que sera mostrado al usuario
            mensaje = "Ha iniciado sesión correctamente.";
            //Coloca en sesion al mensaje
            sessionMap.put("mensaje", mensaje);
            //Coloca el mensaje como mensaje del action
            this.addActionMessage(mensaje);
            //Coloca en sesion al administrador
            sessionMap.put("administrador", administrador);
            //Coloca en sesion el numero de visitas a la pagina
            sessionMap.put("numeroVisitas", administradorBusiness.getNumeroVisitas());
        } catch (SQLException | DataException ex) {
            Logger.getLogger(IniciarSesionAction.class.getName()).log(Level.SEVERE, null, ex);
            //Define un mensaje que sera mostrado al usuario
            mensaje = "Ha ocurrido un error en la base de datos, por favor espere. O si el error persiste comuníquese con nosotros.\nGracias";
            //Coloca en sesion al mensaje
            sessionMap.put("mensaje", mensaje);
            //Coloca el mensaje como mensaje de error
            this.addActionError(mensaje);
            return ERROR;
        }
        return SUCCESS;
    }

    @Override
    public void validate() {
        //Validaciones de los campos de entrada
        if (this.request.getParameter("nombreUsuario").trim().equals("")) {
            addFieldError("nombreUsuario", "Ingrese un nombre de usuario o correo");
        }
        if (this.request.getParameter("clave").trim().equals("")) {
            addFieldError("clave", "Ingrese una contraseña");
        }
    }
    
    @Override
    public Administrador getModel() {
        return this.administrador;
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
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
