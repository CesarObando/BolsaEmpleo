package Actions;

import Business.CategoriaBusiness;
import Dominio.Categoria;
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

public class EliminarCategoriaAction extends ActionSupport implements SessionAware, Preparable, ModelDriven<Categoria>, ServletRequestAware {

    //Variables globales
    private Categoria categoriaAEliminar;
    private String mensaje;
    private HttpServletRequest request;
    private SessionMap<String, Object> sessionMap;

    public EliminarCategoriaAction() {
    }

    @Override
    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        //Obtiene el id del objeto a eliminar
        int idCategoria = Integer.parseInt(request.getParameter("id"));
        //Llamado al metodo que realiza la busqueda
        categoriaAEliminar = new CategoriaBusiness().buscarCategoria(idCategoria);
    }

    public String eliminar() throws DataException {
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        CategoriaBusiness categoriaBusiness = new CategoriaBusiness();
        //Inicializa la variable
        boolean eliminado = true;
        try {
            //Llamado al metodo que realiza la eliminacion
            categoriaBusiness.eliminarCategoria(categoriaAEliminar.getId());
        } catch (SQLException e) {
            //Asigna valor a la variable
            eliminado = !eliminado;
        }
        if (eliminado) {
            //Define un mensaje que sera mostrado al usuario
            mensaje = "La categoría fue eliminada correctamente.";
            //Coloca en sesion al mensaje
            sessionMap.put("mensaje", mensaje);
            //Coloca el mensaje como mensaje del action
            addActionMessage(mensaje);
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
    public Categoria getModel() {
        return this.categoriaAEliminar;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

    //Setter-Getter
    public Categoria getCategoriaAEliminar() {
        return categoriaAEliminar;
    }

    public void setCategoriaAEliminar(Categoria categoriaAEliminar) {
        this.categoriaAEliminar = categoriaAEliminar;
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
