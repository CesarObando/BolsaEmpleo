package Actions;

import Business.SolicitanteBusiness;
import Dominio.Solicitante;
import Exception.DataException;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

public class BuscarSolicitantesAction extends ActionSupport implements Preparable, ServletRequestAware {

    //Variables globales
    private final String BUSCAR_SOLICITANTES = "buscarSolicitantes";
    private LinkedList<Solicitante> solicitantes;
    private HttpServletRequest request;
    private String cedula;
    private String nombre;
    private String apellidos;

    public BuscarSolicitantesAction() {
    }

    public String execute() throws Exception {
        return ActionSupport.SUCCESS;
    }

    @Override
    public void prepare() throws Exception {
    }
    
    

    public String buscar() throws DataException {
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        SolicitanteBusiness solicitanteBusiness = new SolicitanteBusiness();
        //Captura de los campos de busqueda en el jsp
        cedula = request.getParameter("cedula");
        nombre = request.getParameter("nombre");
        apellidos = request.getParameter("apellidos");
        try {
            //Llamado al metodo que realiza la busqueda
            solicitantes = solicitanteBusiness.buscarSolicitantesFiltrados(cedula, nombre, apellidos);
            ordenarLista();
        } catch (SQLException e) {
            Logger.getLogger(BuscarSolicitantesAction.class.getName()).log(Level.SEVERE, null, e);
        }
        return BUSCAR_SOLICITANTES;
    }
    
    public void ordenarLista() { //Calcula los meses que han transcurrido desde la ultima actualizacion
        java.util.Date fechaActual = new java.util.Date();
        java.sql.Date fechaUltimaActualizacion;
        for (Solicitante solicitante : solicitantes) {
            fechaUltimaActualizacion = solicitante.getUltimaActualizacion();
            if (fechaActual.getMonth() - fechaUltimaActualizacion.getMonth() > 0 && fechaActual.getYear() == fechaUltimaActualizacion.getYear()) {
                if(fechaActual.getMonth() - fechaUltimaActualizacion.getMonth()==1){
                    solicitante.setEscolaridad("Hace " + (fechaActual.getMonth() - fechaUltimaActualizacion.getMonth()) + " mes");
                }else{
                    solicitante.setEscolaridad("Hace " + (fechaActual.getMonth() - fechaUltimaActualizacion.getMonth()) + " meses");
                }
            } else if (fechaActual.getMonth() - fechaUltimaActualizacion.getMonth() == 0 && fechaActual.getYear() == fechaUltimaActualizacion.getYear()) {
                solicitante.setEscolaridad("Hace menos de un mes");
            } else {
                if (fechaActual.getYear() - fechaUltimaActualizacion.getYear() == 1) {
                    solicitante.setEscolaridad("Hace " + ((fechaActual.getMonth() + 11) - fechaUltimaActualizacion.getMonth()) + " meses");
                }
            }
        }
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    //Setter-Getter
    public LinkedList<Solicitante> getSolicitantes() {
        return solicitantes;
    }

    public void setSolicitantes(LinkedList<Solicitante> solicitantes) {
        this.solicitantes = solicitantes;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
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
