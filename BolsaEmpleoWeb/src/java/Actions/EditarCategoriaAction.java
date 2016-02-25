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

public class EditarCategoriaAction extends ActionSupport implements SessionAware, Preparable, ModelDriven<Categoria>, ServletRequestAware {

    //Variables globales
    private Categoria categoriaAEditar;
    private String mensaje;
    private HttpServletRequest request;
    private SessionMap<String, Object> sessionMap;

    public EditarCategoriaAction() {
    }

    @Override
    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        //Captura el parametro necesario para realizar la busqueda
        int id = Integer.parseInt(request.getParameter("id"));
        //Llamado al metodo que realiza la busqueda
        categoriaAEditar = new CategoriaBusiness().buscarCategoria(id);
    }

    @Override
    public void validate() {
        //Validaciones de los campos de entrada
        if (categoriaAEditar.getNombre().length() == 0) {
            addFieldError("nombre", "Debe ingresar un nombre.");
        }
    }

    public String editar() throws DataException {
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        CategoriaBusiness categoriaBusiness = new CategoriaBusiness();
        //Inicializa la variable
        boolean editado = true;
        try {
            //Llamado al metodo que realiza la edicion
            categoriaBusiness.editarCategoria(categoriaAEditar);
        } catch (SQLException e) {
            //Asigna valor a la variable
            editado = false;
            //Define un mensaje que sera mostrado al usuario
            mensaje = "Ocurrió un error con la base de datos.Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
            //Coloca en sesion al mensaje
            sessionMap.put("mensaje", mensaje);
            //Coloca el mensaje como mensaje de error
            addActionError(mensaje);
        }
        if (editado == true) {
            //Define un mensaje que sera mostrado al usuario
            this.mensaje = "La categoria fue editada correctamente";
            //Coloca en sesion al mensaje
            sessionMap.put("mensaje", mensaje);
            //Coloca el mensaje como mensaje del action
            addActionMessage(mensaje);
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    @Override
    public Categoria getModel() {
        return this.categoriaAEditar;
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
    public Categoria getCategoriaAEditar() {
        return categoriaAEditar;
    }

    public void setCategoriaAEditar(Categoria categoriaAEditar) {
        this.categoriaAEditar = categoriaAEditar;
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
