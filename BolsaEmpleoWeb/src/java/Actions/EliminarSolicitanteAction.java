package Actions;

import Business.SolicitanteBusiness;
import Dominio.Solicitante;
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

public class EliminarSolicitanteAction extends ActionSupport implements Preparable, ModelDriven<Solicitante>, ServletRequestAware, SessionAware {

    //Variables globales
    private Solicitante solicitanteAEliminar;
    private String mensaje;
    private HttpServletRequest request;
    public SessionMap<String, Object> sessionMap;

    public EliminarSolicitanteAction() {
    }

    @Override
    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        //Inicializa el objeto
        solicitanteAEliminar = new Solicitante();
        //Obtiene el objeto en sesion
        solicitanteAEliminar = (Solicitante) sessionMap.get("solicitante");
        if (request.getParameter("id") != null) {
            //Captura el id del objeto a eliminar
            int idSolicitante = Integer.parseInt(request.getParameter("id"));
            //Llamado al metodo que realiza la busqueda
            solicitanteAEliminar = new SolicitanteBusiness().buscarSolicitante(idSolicitante);
        }
    }

    @Override
    public void validate() {

    }

    public String eliminar() throws DataException {
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        SolicitanteBusiness solicitanteBusiness = new SolicitanteBusiness();
        //Inicializa la variable
        boolean eliminado = true;
        try {
            //Llamado al metodo que realiza la eliminacion
            solicitanteBusiness.eliminarSolicitante(solicitanteAEliminar.getId());
        } catch (SQLException e) {
            //Asigna valor a la variable
            eliminado = !eliminado;
        }
        if (eliminado) {
            //Define un mensaje que sera mostrado al usuario
            mensaje = "El solicitante fue eliminado correctamente.";
            //Coloca en sesion al mensaje
            sessionMap.put("mensaje", mensaje);
            //Coloca el mensaje como mensaje del action
            addActionMessage(mensaje);
            //Elimina la sesion del objeto
            sessionMap.remove("solicitante");
            return SUCCESS;
        } else {
            //Define un mensaje que sera mostrado al usuario
            mensaje = "Ocurrió un problema al eliminar.";
            //Coloca en sesion al mensaje
            sessionMap.put("mensaje", mensaje);
            //Coloca el mensaje como mensaje de error
            addActionError(mensaje);
            return ERROR;
        }
    }

    @Override
    public Solicitante getModel() {
        return this.solicitanteAEliminar;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

    public Solicitante getSolicitanteAEliminar() {
        return solicitanteAEliminar;
    }

    public void setSolicitanteAEliminar(Solicitante solicitanteAEliminar) {
        this.solicitanteAEliminar = solicitanteAEliminar;
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

}
