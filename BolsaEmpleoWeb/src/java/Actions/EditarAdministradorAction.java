/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author JonathanA
 */
public class EditarAdministradorAction extends ActionSupport implements Preparable, ModelDriven<Administrador>, ServletRequestAware, SessionAware {

    private Administrador administradorEditar;
    private String mensaje;
    private HttpServletRequest request;
    private boolean existe;
    public SessionMap<String, Object> sessionMap;

    public EditarAdministradorAction() {
    }

    public String execute() throws Exception {
        if (existe) {
            return INPUT;
        } else {
            return ERROR;
        }
    }

    @Override
    public void prepare() throws Exception {
        administradorEditar = (Administrador) sessionMap.get("administrador");
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
    public void validate() {
        if (administradorEditar.getCedula().length() != 9 || administradorEditar.getCedula().equals(null)) {
            addFieldError("cedula", "Debe ingresar un número de identificación válido. Formato de 9 dígitos. Ej.: 000000000");
        }
        if (administradorEditar.getNombre().length() == 0 || administradorEditar.getNombre().equals(null)) {
            addFieldError("nombre", "Debe ingresar su nombre.");
        }
        if (administradorEditar.getApellidos().length() == 0 || administradorEditar.getApellidos().equals(null)) {
            addFieldError("apellidos", "Debe ingresar sus apellidos.");
        }
        if (administradorEditar.getUsername().length() == 0 || administradorEditar.getUsername().equals(null)) {
            addFieldError("username", "Debe ingresar un nombre de usuario.");
        }
        if (administradorEditar.getPassword().length() == 0 || administradorEditar.getPassword().equals(null)) {
            addFieldError("password", "Debe ingresar una contraseña.");
        }
    }

    public String editar() throws DataException {
        AdministradorBusiness administradorBusiness = new AdministradorBusiness();
        boolean insertado = true;
        try {
            administradorBusiness.editarAdministrador(administradorEditar);
        } catch (SQLException e) {
            insertado = false;
            mensaje = "Ocurrió un error con la base de datos.Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
        }
        if (insertado == true) {
            this.mensaje = "El administrador fue editado correctamente";
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

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

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

}
