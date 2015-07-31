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
    private HttpServletRequest request;

    public EliminarAdministradorAction() {
    }

    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        String cedulaAdministrador = request.getParameter("cedula");
        administradorEliminar = new AdministradorBusiness().buscarAdministrador(cedulaAdministrador);
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
        try {
            administradorBusiness.eliminarAdministrador(administradorEliminar.getCedula());
            return SUCCESS;
        } catch (SQLException e) {
            return ERROR;
        }
    }

    public Administrador getAdministradorEliminar() {
        return administradorEliminar;
    }

    public void setAdministradorEliminar(Administrador administradorEliminar) {
        this.administradorEliminar = administradorEliminar;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
    
}
