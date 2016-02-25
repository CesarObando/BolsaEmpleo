package Actions;

import Business.SolicitanteBusiness;
import Dominio.Solicitante;
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
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

public class RecuperarPasswordAction extends ActionSupport implements Preparable, ModelDriven<Solicitante>, SessionAware {

    //Variables globales
    private Solicitante solicitante;
    private String nombreUsuario;
    private String mensaje;
    private SessionMap<String, Object> sessionMap;

    @Override
    public void prepare() throws Exception {
        //Inicializa el objeto
        solicitante = new Solicitante();
    }

    public String recuperarPassword() {
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        SolicitanteBusiness solicitanteBusiness = new SolicitanteBusiness();
        try {
            //Llamado al metodo que realiza la busqueda
            solicitante = solicitanteBusiness.buscarSolicitantePorNombreUsuario(nombreUsuario);
            //Si no hay resultado de busqueda
            if (solicitante.getId() == 0) {
                this.addFieldError("nombreUsuario", "No existe ningun usuario con ese nombre");
                return ERROR;
            }
            //Genera objeto para enviar el correo
            EnviarCorreos enviaCorreo = new EnviarCorreos();
            //Llamado al metodo que envia el correo
            enviaCorreo.EnviarCorreo(solicitante.getCorreo(), "Solicitud de recuperación de contraseña", "Se recibió una solicitud para la recuperación de la contraseña del usuario: " + solicitante.getUsername() + ". La contraseña es: " + solicitante.getPassword());
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
    public Solicitante getModel() {
        return this.solicitante;
    }
    
    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

    //Setter-Getter
    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
}
