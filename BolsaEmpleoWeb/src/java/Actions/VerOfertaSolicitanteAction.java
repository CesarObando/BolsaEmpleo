/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.CategoriaBusiness;
import Business.EmpleadorBusiness;
import Business.OfertaBusiness;
import Dominio.Categoria;
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
public class VerOfertaSolicitanteAction extends ActionSupport implements Preparable, ModelDriven<Oferta>, ServletRequestAware {

    private Oferta ofertaAVer;
    private String mensaje;
    private boolean existe;
    private HttpServletRequest request;

    public VerOfertaSolicitanteAction() {
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
        int idOferta = Integer.parseInt(request.getParameter("id"));
        try {
            ofertaAVer = new OfertaBusiness().buscarOferta(idOferta);
        } catch (SQLException e) {
            existe = false;
        }
    }

    @Override
    public Oferta getModel() {
        return this.ofertaAVer;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public Oferta getOfertaAEliminar() {
        return ofertaAVer;
    }

    public void setOfertaAEliminar(Oferta ofertaAEliminar) {
        this.ofertaAVer = ofertaAEliminar;
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

}
