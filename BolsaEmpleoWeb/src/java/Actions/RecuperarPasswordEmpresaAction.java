/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.EmpleadorBusiness;
import Business.SolicitanteBusiness;
import Dominio.Empleador;
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
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Cesar
 */
public class RecuperarPasswordEmpresaAction extends ActionSupport implements Preparable, ModelDriven<Empleador>, ServletRequestAware, SessionAware {
    
    private Empleador empleador;
    private HttpServletRequest request;
    private String nombreUsuario;
    private String mensaje;
    private SessionMap<String, Object> sessionMap;

    @Override
    public void prepare() throws Exception {
        empleador = new Empleador();
    }

    
    public String recuperarPassword() {
        EmpleadorBusiness empleadorBusiness = new EmpleadorBusiness();
        try {
            empleador = empleadorBusiness.buscarEmpleadorPorNombreUsuario(nombreUsuario);
            if (empleador.getId() == 0) {
                this.addFieldError("nombreUsuario", "No existe ningun usuario con ese nombre");
                return ERROR;
            }
            EnviarCorreos enviaCorreo = new EnviarCorreos();
            enviaCorreo.EnviarCorreo(empleador.getCorreo(), "Solicitud de recuperación de contraseña", "Se recibio una solicitud para la recuperacion de la contraseña del usuario: "+ empleador.getUsername() + " la contraseña es: " +empleador.getPass());
            mensaje = "Se ha enviado la contraseña a su correo.";
            this.addActionMessage(mensaje);
            sessionMap.put("mensaje", this.mensaje);
        } catch (SQLException ex) {
            Logger.getLogger(IniciarSesionAction.class.getName()).log(Level.SEVERE, null, ex);
            mensaje = "Ha ocurrido un error en la base de datos, por favor espere. O si el error persiste comuníquese con nosotros.\nGracias";
            this.addActionError(mensaje);
            return ERROR;
        } catch (DataException ex) {
            Logger.getLogger(IniciarSesionAction.class.getName()).log(Level.SEVERE, null, ex);
            mensaje = "\"Ha ocurrido un error en la base de datos, por favor espere. O si el error persiste comuníquese con nosotros.\\nGracias\"";
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
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

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
    
     @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }
}
