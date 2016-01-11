/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.CategoriaBusiness;
import Business.OfertaBusiness;
import Dominio.Empleador;
import Dominio.Oferta;
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
public class InsertarOfertaAction extends ActionSupport implements ModelDriven<Oferta>, Preparable, ServletRequestAware, SessionAware {

    private Oferta ofertaAInsertar;
    private LinkedList listaCategorias;
    private String mensaje;
    private HttpServletRequest request;
    public SessionMap<String, Object> sessionMap;
    private Empleador empleador;
    private int idEmpleador;

    public InsertarOfertaAction() {
    }

    @Override
    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        empleador = (Empleador) sessionMap.get("empleador");
        empleador = (Empleador) request.getSession().getAttribute("empleador");
        ofertaAInsertar = new Oferta();
        CategoriaBusiness categoriaBusiness = new CategoriaBusiness();
        this.listaCategorias = categoriaBusiness.getCategorias();
        mensaje = "";
    }

    @Override
    public Oferta getModel() {
        return this.ofertaAInsertar;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    @Override
    public void validate() {
        if (ofertaAInsertar.getPuesto().length() == 0 || ofertaAInsertar.getPuesto() == null) {
            addFieldError("puesto", "Debe ingresar el puesto vacante");
        }
        if (ofertaAInsertar.getRequerimientos().length() == 0 || ofertaAInsertar.getRequerimientos() == null) {
            addFieldError("requerimientos", "Debe ingresar los requerimientos del puesto.");
        }
        if (ofertaAInsertar.getCantidadVacantes() == 0) {
            addFieldError("vacantes", "Debe seleccionar una catidad de vacantes");
        }

    }

    public String insertar() {
        OfertaBusiness ofertaBusiness = new OfertaBusiness();
        boolean insertado = true;
        try {
            ofertaAInsertar.setEmpleador(empleador);
            ofertaBusiness.insertaOferta(ofertaAInsertar);
        } catch (SQLException e) {
            insertado = false;
            mensaje = "Ocurrió un error con la base de datos. Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
        }
        if (insertado == true) {
            this.mensaje = "La  oferta  fue insertada correctamente";
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public Oferta getOfertaAInsertar() {
        return ofertaAInsertar;
    }

    public void setOfertaAInsertar(Oferta ofertaAInsertar) {
        this.ofertaAInsertar = ofertaAInsertar;
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

    public LinkedList getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(LinkedList listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

    public Empleador getEmpleador() {
        return empleador;
    }

    public void setEmpleador(Empleador empleador) {
        this.empleador = empleador;
    }

    public int getIdEmpleador() {
        return idEmpleador;
    }

    public void setIdEmpleador(int idEmpleador) {
        this.idEmpleador = idEmpleador;
    }

}
