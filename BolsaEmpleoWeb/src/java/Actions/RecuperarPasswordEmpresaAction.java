package Actions;

import Business.EmpleadorBusiness;
import Dominio.Empleador;
import Exception.DataException;
import Utilitarios.EnviarCorreos;
import static com.opensymphony.xwork2.Action.ERROR;
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
import org.apache.struts2.interceptor.SessionAware;

public class RecuperarPasswordEmpresaAction extends ActionSupport implements Preparable, ModelDriven<Empleador>, SessionAware {

    //Variables globales
    private Empleador empleador;
    private String nombreUsuario;
    private String mensaje;
    private SessionMap<String, Object> sessionMap;

    @Override
    public void prepare() throws Exception {
        //Inicializa el objeto
        empleador = new Empleador();
    }

    public String recuperarPassword() {
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        EmpleadorBusiness empleadorBusiness = new EmpleadorBusiness();
        try {
            //Llamado al metodo que realiza la busqueda
            empleador = empleadorBusiness.buscarEmpleadorPorNombreUsuario(nombreUsuario);
            //Si no hay resultado de busqueda
            if (empleador.getId() == 0) {
                this.addFieldError("nombreUsuario", "No existe ningun usuario con ese nombre");
                return ERROR;
            }
            //Genera objeto para enviar el correo
            EnviarCorreos enviaCorreo = new EnviarCorreos();
            //Llamado al metodo que envia el correo
            enviaCorreo.EnviarCorreo(empleador.getCorreo(), "Solicitud de recuperación de contraseña", "Se recibió una solicitud para la recuperación de la contraseña del usuario: " + empleador.getUsername() + ". La contraseña es: " + empleador.getPassword());
            //Define un mensaje que sera mostrado al usuario
            mensaje = "Se ha enviado la contraseña a su correo.";
            //Coloca el mensaje como mensaje del action
            this.addActionMessage(mensaje);
            //Coloca en sesion al mensaje
            sessionMap.put("mensaje", this.mensaje);
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
    public Empleador getModel() {
        return this.empleador;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

    //Getter-Setter
    public Empleador getEmpleador() {
        return empleador;
    }

    public void setEmpleador(Empleador empleador) {
        this.empleador = empleador;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

}
