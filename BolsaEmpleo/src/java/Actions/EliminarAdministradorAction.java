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
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author JonathanA
 */
public class EliminarAdministradorAction extends ActionSupport implements Preparable, ModelDriven<Administrador>, ServletRequestAware {

    private Administrador administradorEliminar;
    private String mensaje;
    private boolean existe;
    private HttpServletRequest request;

    public EliminarAdministradorAction() {
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
        existe = true;
        String cedulaAdministrador = request.getParameter("cedula");
        try {
            administradorEliminar = new AdministradorBusiness().buscarAdministrador(cedulaAdministrador);
        } catch (SQLException e) {
            existe = false;
        }
    }

    @Override
    public Administrador getModel() {
        return this.administradorEliminar;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public String eliminar() throws DataException {
        AdministradorBusiness administradorBusiness = new AdministradorBusiness();
        boolean eliminado = true;
        try {
            administradorBusiness.eliminarAdministrador(administradorEliminar.getCedula());
        } catch (SQLException e) {
            eliminado = !eliminado;
        }
        if (eliminado) {
            mensaje = "El administrador fue eliminado correctamente.";
            return SUCCESS;
        } else {
            mensaje = "Ocurrió un problema al eliminar.";
            return ERROR;
        }
    }

    public Administrador getAdministradorEliminar() {
        return administradorEliminar;
    }

    public void setAdministradorEliminar(Administrador administradorEliminar) {
        this.administradorEliminar = administradorEliminar;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

}
