/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Tin
 */
public class InsertarEmpleadoresAction extends ActionSupport implements Preparable, ModelDriven<Empleador>, ServletRequestAware {

    private Empleador empleadorInsertar;
    private String mensaje;
    private HttpServletRequest request;

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
        if (empleadorInsertar.getCedula().length() != 9 || empleadorInsertar.getCedula().equals(null)) {
            addFieldError("cedula", "Debe ingresar un número de identificación válido. Formato de 9 dígitos. Ej.: 000000000");
        }
        if (empleadorInsertar.getNombre().length() == 0 || empleadorInsertar.getNombre().equals(null)) {
            addFieldError("nombre", "Debe ingresar su nombre.");
        }
        if (empleadorInsertar.getApellidos().length() == 0 || empleadorInsertar.getApellidos().equals(null)) {
            addFieldError("apellidos", "Debe ingresar sus apellidos.");
        }
        if (empleadorInsertar.getUsername().length() == 0 || empleadorInsertar.getUsername().equals(null)) {
            addFieldError("username", "Debe ingresar un nombre de usuario.");
        }
        if (empleadorInsertar.getPass().length() == 0 || empleadorInsertar.getPass().equals(null)) {
            addFieldError("pass", "Debe ingresar una contraseña.");
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
        }
        if (insertado == true) {
            this.mensaje = "El empleador fue insertado correctamente";
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

}
