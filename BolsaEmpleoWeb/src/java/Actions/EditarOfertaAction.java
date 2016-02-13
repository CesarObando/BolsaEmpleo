/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.CategoriaBusiness;
import Business.OfertaBusiness;
import Dominio.Categoria;
import Dominio.Oferta;
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
public class EditarOfertaAction extends ActionSupport implements SessionAware, Preparable, ModelDriven<Oferta>, ServletRequestAware {

    private Oferta ofertaAEditar;
    private LinkedList<Categoria> categorias;
    private String mensaje;
    private HttpServletRequest request;
    private boolean existe;
    private SessionMap<String, Object> sessionMap;

    public EditarOfertaAction() {
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
        ofertaAEditar = (Oferta) sessionMap.get("oferta");
        categorias = new CategoriaBusiness().getCategorias();

    }

    @Override
    public Oferta getModel() {
        return this.ofertaAEditar;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    @Override
    public void validate() {
        if (ofertaAEditar.getPuesto().length() == 0) {
            addFieldError("puesto", "Debe ingresar el puesto vacante");
        }
        if (ofertaAEditar.getRequerimientos().length() == 0) {
            addFieldError("requerimientos", "Debe ingresar los requerimientos del puesto.");
        }
        if (ofertaAEditar.getCantidadVacantes() < 0) {
            addFieldError("cantidadVacantes", "Debe seleccionar una catidad de vacantes");
        }
        if (ofertaAEditar.getDescripcion().length() == 0) {
            addFieldError("descripcion", "Debe ingresar una descripción del puesto.");
        }
        if (ofertaAEditar.getCategoria().getId() == -1) {
            addFieldError("categoria.id", "Debe seleccionar una categoría.");
        }
        if (ofertaAEditar.getProvincia().length() == 0) {
            addFieldError("provincia", "Debe seleccionar una provincia.");
        }
        if (ofertaAEditar.getCanton().length() == 0) {
            addFieldError("canton", "Debe seleccionar un cantón.");
        }
    }

    public String editar() {
        OfertaBusiness ofertaBusiness = new OfertaBusiness();
        boolean editado = true;
        try {
            ofertaBusiness.editarOferta(ofertaAEditar);
        } catch (SQLException e) {
            editado = false;
            mensaje = "Ocurrió un error con la base de datos. Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
            sessionMap.put("mensaje", mensaje);
            addActionError(mensaje);
        }
        if (editado == true) {
            this.mensaje = "La oferta fue editada correctamente";
            sessionMap.put("mensaje", mensaje);
            addActionMessage(mensaje);
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public Oferta getOfertaAEditar() {
        return ofertaAEditar;
    }

    public void setOfertaAEditar(Oferta ofertaAEditar) {
        this.ofertaAEditar = ofertaAEditar;
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
