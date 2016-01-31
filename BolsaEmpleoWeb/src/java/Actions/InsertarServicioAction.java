/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.CategoriaBusiness;
import Business.ServicioBusiness;
import Dominio.Servicio;
import Dominio.Solicitante;
import static com.opensymphony.xwork2.Action.ERROR;
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
public class InsertarServicioAction extends ActionSupport implements ModelDriven<Servicio>, Preparable, ServletRequestAware, SessionAware {

    private Servicio servicioAInsertar;
    private LinkedList listaCategorias;
    private String mensaje;
    private HttpServletRequest request;
    public SessionMap<String, Object> sessionMap;
    private Solicitante solicitante;
    private int idSolicitante;

    public InsertarServicioAction() {
    }

    @Override
    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        solicitante = (Solicitante) sessionMap.get("solicitante");
        solicitante = (Solicitante) request.getSession().getAttribute("solicitante");
        servicioAInsertar = new Servicio();
        CategoriaBusiness categoriaBusiness = new CategoriaBusiness();
        this.listaCategorias = categoriaBusiness.getCategorias();
        mensaje = "";
    }

    @Override
    public Servicio getModel() {
        return this.servicioAInsertar;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    @Override
    public void validate() {
        if (servicioAInsertar.getTitulo().length() == 0) {
            addFieldError("titulo", "Debe ingresar el título del servicio.");
        }
        if (servicioAInsertar.getDescripcion().length() == 0) {
            addFieldError("descripcion", "Debe ingresar una descripción del servicio.");
        }
        if (servicioAInsertar.getCategoria().getId() == -1) {
            addFieldError("categoria.id", "Debe seleccionar una categoría.");
        }
    }

    public String insertar() {
        ServicioBusiness servicioBusiness = new ServicioBusiness();
        boolean insertado = true;
        try {
            servicioAInsertar.setSolicitante(solicitante);
            servicioBusiness.insertarServicio(servicioAInsertar);
        } catch (SQLException e) {
            insertado = false;
            mensaje = "Ocurrió un error con la base de datos. Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
            sessionMap.put("mensaje", mensaje);
            addActionError(mensaje);
        }
        if (insertado == true) {
            this.mensaje = "El servicio fue insertado correctamente";
            addActionMessage(mensaje);
            sessionMap.put("mensaje", mensaje);
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

    public Servicio getServicioAInsertar() {
        return servicioAInsertar;
    }

    public void setServicioAInsertar(Servicio servicioAInsertar) {
        this.servicioAInsertar = servicioAInsertar;
    }

    public LinkedList getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(LinkedList listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }

    public int getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(int idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

}
