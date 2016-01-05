/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.AdministradorBusiness;
import Business.EmpleadorBusiness;
import Dominio.Administrador;
import Dominio.Empleador;
import Exception.DataException;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Tin
 */
public class EditarEmpleadorAction extends ActionSupport implements Preparable, ModelDriven<Empleador>, ServletRequestAware, SessionAware {

    private Empleador empleadorEditar;
    private String mensaje;
    private HttpServletRequest request;
    public SessionMap<String, Object> sessionMap;

    public EditarEmpleadorAction() {
    }

    public String execute() throws Exception {
        empleadorEditar = (Empleador) sessionMap.get("empleador");
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        empleadorEditar = (Empleador) sessionMap.get("empleador");
        System.out.println("");
        System.out.println("");
        System.out.println(empleadorEditar.getNombre());
    }

    @Override
    public Empleador getModel() {
        return this.empleadorEditar;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    @Override
    public void validate() {

    }

    public String editar() {
        EmpleadorBusiness empleadorBussines = new EmpleadorBusiness();
        boolean insertado = true;
        try {
            empleadorBussines.editarEmpleador(empleadorEditar);;
        } catch (SQLException e) {
            insertado = false;
            mensaje = "Ocurrió un error con la base de datos.Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
        }
        if (insertado == true) {
            this.mensaje = "El empleador fue editado correctamente";
            sessionMap.put("empleador", empleadorEditar);
            return SUCCESS;
        } else {
            return ERROR;
        }
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
    
    public Empleador getEmpleadorEditar() {
        return empleadorEditar;
    }

    public void setEmpleadorEditar(Empleador empleadorEditar) {
        this.empleadorEditar = empleadorEditar;
    }
}
