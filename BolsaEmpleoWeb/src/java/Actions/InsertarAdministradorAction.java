package Actions;

import Business.AdministradorBusiness;
import Dominio.Administrador;
import Exception.DataException;
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

public class InsertarAdministradorAction extends ActionSupport implements SessionAware, Preparable, ModelDriven<Administrador>, ServletRequestAware {

    //Variables globales
    private Administrador administradorInsertar;
    private String mensaje;
    private HttpServletRequest request;
    public SessionMap<String, Object> sessionMap;

    public InsertarAdministradorAction() {

    }

    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        //Inicializa el objeto
        administradorInsertar = new Administrador();
    }

    @Override
    public void validate() {
        //Validaciones de los campos de entrada
        if (administradorInsertar.getCedula().length() != 9 || administradorInsertar.getCedula() == null) {
            addFieldError("cedula", "Debe ingresar un número de identificación válido. Formato de 9 dígitos. Ej.: 000000000");
        }
        if (administradorInsertar.getNombre().length() == 0 || administradorInsertar.getNombre() == null) {
            addFieldError("nombre", "Debe ingresar su nombre.");
        }
        if (administradorInsertar.getApellidos().length() == 0 || administradorInsertar.getApellidos() == null) {
            addFieldError("apellidos", "Debe ingresar sus apellidos.");
        }
        if (administradorInsertar.getUsername().length() == 0 || administradorInsertar.getUsername() == null) {
            addFieldError("username", "Debe ingresar un nombre de usuario.");
        }
        if (administradorInsertar.getPassword().length() == 0 || administradorInsertar.getPassword() == null) {
            addFieldError("password", "Debe ingresar una contraseña.");
        }
    }

    public String insertar() throws DataException {
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        AdministradorBusiness administradorBusiness = new AdministradorBusiness();
        //Inicializa las variables
        boolean insertado = true;
        boolean existe = false;
        try {
            //Define una lista de objetos
            LinkedList<Administrador> administradores = new AdministradorBusiness().buscarAdministradores();
            int i = 0;
            //Recorre la lista
            while (existe == false && i < administradores.size()) {
                Administrador administrador = administradores.get(i);
                //Valida que la cedula y nombre de usuario que se esta insertando no exista
                if (administradorInsertar.getCedula().equals(administrador.getCedula()) || administradorInsertar.getUsername().equals(administrador.getUsername())) {
                    existe = true;
                }
                i++;
            }
            //Si no existe la cedula y nombre de usuario
            if (existe == false) {
                //Llamado al metodo que realiza la insercion
                administradorBusiness.insertarAdministrador(administradorInsertar);
            } else {
                //Asigna valor a la variable
                insertado = false;
                //Define un mensaje que sera mostrado al usuario
                mensaje = "La cédula o nombre de usuario ya se encuentran registrados. Inténtelo nuevamente.";
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
            this.mensaje = "El administrador fue insertado correctamente";
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
        return this.administradorInsertar;
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
    public Administrador getAdministradorInsertar() {
        return administradorInsertar;
    }

    public void setAdministradorInsertar(Administrador administradorInsertar) {
        this.administradorInsertar = administradorInsertar;
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
