/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.CategoriaBusiness;
import Business.ServicioBusiness;
import Dominio.Categoria;
import Dominio.Servicio;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Tin
 */
public class EditarServicioAction extends ActionSupport implements SessionAware, Preparable, ModelDriven<Servicio>, ServletRequestAware {

    private Servicio servicioAEditar;
    private LinkedList<Categoria> categorias;
    private String mensaje;
    private HttpServletRequest request;
    private boolean existe;
    private SessionMap<String, Object> sessionMap;

    public EditarServicioAction() {
    }

    @Override
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
        categorias = new CategoriaBusiness().getCategorias();
        servicioAEditar = (Servicio) sessionMap.get("servicio");
        int idCategoria = servicioAEditar.getCategoria().getId();
        Categoria categoria = new CategoriaBusiness().buscarCategoria(idCategoria);
        servicioAEditar.setCategoria(categoria);
    }

    @Override
    public Servicio getModel() {
        return this.servicioAEditar;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    @Override
    public void validate() {
        if (servicioAEditar.getTitulo().length() == 0) {
            addFieldError("titulo", "Debe ingresar el título del servicio.");
        }
        if (servicioAEditar.getDescripcion().length() == 0) {
            addFieldError("descripcion", "Debe ingresar una descripción del servicio.");
        }
        if (servicioAEditar.getCategoria().getId() == -1) {
            addFieldError("categoria.id", "Debe seleccionar una categoría.");
        }

    }

    public String editar() {
        ServicioBusiness servicioBusiness = new ServicioBusiness();
        boolean editado = true;
        try {
            servicioBusiness.editarServicio(servicioAEditar);
        } catch (SQLException e) {
            editado = false;
            mensaje = "Ocurrió un error con la base de datos. Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
            sessionMap.put("mensaje", mensaje);
            addActionError(mensaje);
        }
        if (editado == true) {
            this.mensaje = "El servicio fue editado correctamente";
            sessionMap.put("mensaje", mensaje);
            addActionMessage(mensaje);
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public Servicio getServicioAEditar() {
        return servicioAEditar;
    }

    public void setServicioAEditar(Servicio servicioAEditar) {
        this.servicioAEditar = servicioAEditar;
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

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

    public LinkedList<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(LinkedList<Categoria> categorias) {
        this.categorias = categorias;
    }

}
