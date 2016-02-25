package Actions;

import Business.AdministradorBusiness;
import Dominio.Administrador;
import Exception.DataException;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

public class BuscarAdministradoresAction extends ActionSupport implements Preparable, ServletRequestAware {

    //Variables globales
    private final String BUSCAR_ADMINISTRADORES = "buscarAdministradores";
    private LinkedList<Administrador> administradores;
    private HttpServletRequest request;
    private String cedula;
    private String nombre;
    private String apellidos;

    public BuscarAdministradoresAction() {
    }

    public String execute() throws Exception {
        return ActionSupport.SUCCESS;
    }

    @Override
    public void prepare() throws Exception {
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        AdministradorBusiness administradorBusiness = new AdministradorBusiness();
        //Captura de los campos de busqueda en el jsp
        cedula = request.getParameter("cedula");
        nombre = request.getParameter("nombre");
        apellidos = request.getParameter("apellidos");
        //Llamado al metodo que realiza la busqueda
        administradores = administradorBusiness.buscarAdministradoresFiltrados(cedula, nombre, apellidos);
    }

    public String buscar() throws DataException {
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        AdministradorBusiness administradorBusiness = new AdministradorBusiness();
        //Captura de los campos de busqueda en el jsp
        cedula = request.getParameter("cedula");
        nombre = request.getParameter("nombre");
        apellidos = request.getParameter("apellidos");
        try {
            //Llamado al metodo que realiza la busqueda
            administradores = administradorBusiness.buscarAdministradoresFiltrados(cedula, nombre, apellidos);
        } catch (SQLException e) {
            Logger.getLogger(BuscarAdministradoresAction.class.getName()).log(Level.SEVERE, null, e);
        }
        return BUSCAR_ADMINISTRADORES;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }
    
    //Setter-Getter
    public LinkedList<Administrador> getAdministradores() {
        return administradores;
    }

    public void setAdministradores(LinkedList<Administrador> administradores) {
        this.administradores = administradores;
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
