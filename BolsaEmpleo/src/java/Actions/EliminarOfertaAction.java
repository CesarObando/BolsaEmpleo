/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.EmpleadorBusiness;
import Business.OfertaBusiness;
import Dominio.Empleador;
import Dominio.Oferta;
import Exception.DataException;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Tin
 */
public class EliminarOfertaAction extends ActionSupport implements Preparable, ModelDriven<Oferta>, ServletRequestAware {

    private Oferta ofertaAEliminar;
    private String mensaje;
    private HttpServletRequest request;

    public EliminarOfertaAction() {
    }

    @Override
    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        ofertaAEliminar = new Oferta();
        mensaje = "";
    }

    @Override
    public Oferta getModel() {
        return this.ofertaAEliminar;
    }

    public String eliminar() {
        OfertaBusiness ofertaBusiness = new OfertaBusiness();
        try {
            ofertaBusiness.eliminarOferta(ofertaAEliminar.getId());
            return SUCCESS;
        } catch (SQLException e) {
            return ERROR;
        }
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public Oferta getOfertaAEliminar() {
        return ofertaAEliminar;
    }

    public void setOfertaAEliminar(Oferta ofertaAEliminar) {
        this.ofertaAEliminar = ofertaAEliminar;
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
