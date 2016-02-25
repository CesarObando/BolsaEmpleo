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
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

public class InsertarEmpleadoresAction extends ActionSupport implements SessionAware, Preparable, ModelDriven<Empleador> {

    //Variables globales
    private Empleador empleadorInsertar;
    private String mensaje;
    public SessionMap<String, Object> sessionMap;

    public InsertarEmpleadoresAction() {
    }

    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        //Inicializa el objeto
        empleadorInsertar = new Empleador();
    }

    @Override
    public void validate() {
        //Validaciones de los campos de entrada
        if (empleadorInsertar.getCedula().length() != 9) {
            addFieldError("cedula", "Debe ingresar un número de identificación válido. Ej: 000000000");
        }
        if (empleadorInsertar.getNombre().length() == 0) {
            addFieldError("nombre", "Debe ingresar su nombre.");
        }
        if (empleadorInsertar.getApellidos().length() == 0) {
            addFieldError("apellidos", "Debe ingresar sus apellidos.");
        }
        if (empleadorInsertar.getUsername().length() == 0) {
            addFieldError("username", "Debe ingresar un nombre de usuario.");
        }
        if (empleadorInsertar.getPassword().length() < 6) {
            addFieldError("pass", "La contraseña debe ser mayor a 6 caracteres");
        }
    }

    public String insertar() throws DataException {
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
                //Valida que la cedula, correo y nombre de usuario que se esta insertando no exista
                if (empleadorInsertar.getCedula().equals(empleador.getCedula()) || empleadorInsertar.getCorreo().equals(empleador.getCorreo())
                        || empleadorInsertar.getUsername().equals(empleador.getUsername())) {
                    existe = true;
                }
                i++;
            }
            //Si no existe la cedula, correo y nombre de usuario
            if (existe == false) {
                //Llamado al metodo que realiza la insercion
                empleadorBusiness.insertarEmpleador(empleadorInsertar);
            } else {
                //Asigna valor a la variable
                insertado = false;
                //Define un mensaje que sera mostrado al usuario
                mensaje = "La cédula, correo o nombre de usuario ya se encuentran registrados. Inténtelo nuevamente.";
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
            this.mensaje = "El empleador fue insertado correctamente";
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
    public Empleador getModel() {
        return this.empleadorInsertar;
    }
    
    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

    //Setter-Getter
    public Empleador getEmpleadorInsertar() {
        return empleadorInsertar;
    }

    public void setEmpleadorInsertar(Empleador empleadorInsertar) {
        this.empleadorInsertar = empleadorInsertar;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
