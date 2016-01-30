/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.AdministradorBusiness;
import Dominio.Administrador;
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

/**
 *
 * @author JonathanA
 */
public class InsertarAdministradorAction extends ActionSupport implements SessionAware,Preparable, ModelDriven<Administrador>, ServletRequestAware {

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
        administradorInsertar = new Administrador();
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
    public void validate() {
        if (administradorInsertar.getCedula().length() != 9 || administradorInsertar.getCedula().equals(null)) {
            addFieldError("cedula", "Debe ingresar un número de identificación válido. Formato de 9 dígitos. Ej.: 000000000");
        }
        if (administradorInsertar.getNombre().length() == 0 || administradorInsertar.getNombre().equals(null)) {
            addFieldError("nombre", "Debe ingresar su nombre.");
        }
        if (administradorInsertar.getApellidos().length() == 0 || administradorInsertar.getApellidos().equals(null)) {
            addFieldError("apellidos", "Debe ingresar sus apellidos.");
        }
        if (administradorInsertar.getUsername().length() == 0 || administradorInsertar.getUsername().equals(null)) {
            addFieldError("username", "Debe ingresar un nombre de usuario.");
        }
        if (administradorInsertar.getPassword().length() == 0 || administradorInsertar.getPassword().equals(null)) {
            addFieldError("password", "Debe ingresar una contraseña.");
        }
    }

    public String insertar() throws DataException {
        AdministradorBusiness administradorBusiness = new AdministradorBusiness();
        boolean insertado = true;
        try {
            administradorBusiness.insertarAdministrador(administradorInsertar);
        } catch (SQLException e) {
            insertado = false;
            mensaje = "Ocurrió un error con la base de datos.Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
            sessionMap.put("mensaje", mensaje);
            addActionError(mensaje);
        }
        if (insertado == true) {
            this.mensaje = "El administrador fue insertado correctamente";
            sessionMap.put("mensaje", mensaje);
            addActionMessage(mensaje);
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

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

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

}
