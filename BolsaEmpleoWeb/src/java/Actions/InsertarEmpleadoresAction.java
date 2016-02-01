/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.EmpleadorBusiness;
import Dominio.Empleador;
import static com.opensymphony.xwork2.Action.ERROR;
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

/**
 *
 * @author Tin
 */
public class InsertarEmpleadoresAction extends ActionSupport implements SessionAware, Preparable, ModelDriven<Empleador>, ServletRequestAware {

    private Empleador empleadorInsertar;
    private String mensaje;
    private HttpServletRequest request;
    public SessionMap<String, Object> sessionMap;

    public InsertarEmpleadoresAction() {
    }

    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        empleadorInsertar = new Empleador();
    }

    @Override
    public Empleador getModel() {
        return this.empleadorInsertar;
    }

    @Override
    public void validate() {
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
        if (empleadorInsertar.getPass().length() < 6) {
            addFieldError("pass", "La contraseña debe ser mayor a 6 caracteres");
        }
    }

    public String insertar() {
        EmpleadorBusiness empleadorBusiness = new EmpleadorBusiness();
        boolean insertado = true;
        try {
            empleadorBusiness.insertarEmpleador(empleadorInsertar);
        } catch (SQLException e) {
            insertado = false;
            mensaje = "Ocurrió un error con la base de datos.Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
            sessionMap.put("mensaje", e);
            addActionError(mensaje);
        }
        if (insertado == true) {
            this.mensaje = "El empleador fue insertado correctamente";
            sessionMap.put("mensaje", mensaje);
            addActionMessage(mensaje);
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

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

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

}
