/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.EmpleadorBusiness;
import Dominio.Empleador;
import Exception.DataException;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
            ordenarLista();
        } catch (SQLException e) {
            Logger.getLogger(BuscarEmpleadoresAction.class.getName()).log(Level.SEVERE, null, e);
        }
        return BUSCAR_EMPLEADORES;
    }

    public void ordenarLista() {
        java.util.Date fechaActual = new java.util.Date();
        java.sql.Date fechaUltimaActualizacion;
        for (Empleador empleador : empleadores) {
            fechaUltimaActualizacion = empleador.getUltimaActualizacion();
            if (fechaActual.getMonth() - fechaUltimaActualizacion.getMonth() > 0 && fechaActual.getYear() == fechaUltimaActualizacion.getYear()) {
                if(fechaActual.getMonth() - fechaUltimaActualizacion.getMonth()==1){
                    empleador.setDireccion("Hace " + (fechaActual.getMonth() - fechaUltimaActualizacion.getMonth()) + " mes");
                }else{
                    empleador.setDireccion("Hace " + (fechaActual.getMonth() - fechaUltimaActualizacion.getMonth()) + " meses");
                }
            } else if (fechaActual.getMonth() - fechaUltimaActualizacion.getMonth() == 0 && fechaActual.getYear() == fechaUltimaActualizacion.getYear()) {
                empleador.setDireccion("Hace menos de un mes");
            } else {
                if (fechaActual.getYear() - fechaUltimaActualizacion.getYear() == 1) {
                    empleador.setDireccion("Hace " + ((fechaActual.getMonth() + 11) - fechaUltimaActualizacion.getMonth()) + " meses");
                }
            }
        }
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
