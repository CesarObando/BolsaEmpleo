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
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author JonathanA
 */
public class BuscarEmpleadoresAction extends ActionSupport implements Preparable, ServletRequestAware {

    private final String BUSCAR_EMPLEADORES = "buscarEmpleadores";
    private LinkedList<Empleador> empleadores;
    private HttpServletRequest request;
    private String cedula;
    private String nombre;
    private String apellidos;

    public BuscarEmpleadoresAction() {
    }

    public String execute() throws Exception {
        return ActionSupport.SUCCESS;
    }

    @Override
    public void prepare() throws Exception {
        EmpleadorBusiness empleadorBusiness = new EmpleadorBusiness();
        cedula = request.getParameter("cedula");
        nombre = request.getParameter("nombre");
        apellidos = request.getParameter("apellidos");
        empleadores = empleadorBusiness.buscarEmpleadoresFiltrados(cedula, nombre, apellidos);
    }

    public String buscar() throws DataException {
        EmpleadorBusiness empleadorBusiness = new EmpleadorBusiness();
        cedula = request.getParameter("cedula");
        nombre = request.getParameter("nombre");
        apellidos = request.getParameter("apellidos");
        try {
            empleadores = empleadorBusiness.buscarEmpleadoresFiltrados(cedula, nombre, apellidos);
        } catch (SQLException e) {
            Logger.getLogger(BuscarEmpleadoresAction.class.getName()).log(Level.SEVERE, null, e);
        }
        return BUSCAR_EMPLEADORES;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public LinkedList<Empleador> getEmpleadores() {
        return empleadores;
    }

    public void setEmpleadores(LinkedList<Empleador> empleadores) {
        this.empleadores = empleadores;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

}
