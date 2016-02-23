/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.CategoriaBusiness;
import Business.EmpleadorBusiness;
import Business.OfertaBusiness;
import Dominio.Empleador;
import Dominio.Oferta;
import Dominio.Solicitante;
import Exception.DataException;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author JonathanA
 */
public class BuscarOfertasFavoritasAction extends ActionSupport implements Preparable, ServletRequestAware, SessionAware {

    private final String BUSCAR_OFERTAS = "buscarOfertasFavoritas";
    private LinkedList<Oferta> ofertas;
    private HttpServletRequest request;
    private SessionMap<String, Object> sessionMap;

    public BuscarOfertasFavoritasAction() {
    }

    @Override
    public String execute() throws Exception {
        return ActionSupport.SUCCESS;
    }

    @Override
    public void prepare() throws Exception {
    }

    public String buscar() throws DataException {
        OfertaBusiness ofertaBusiness = new OfertaBusiness();
        EmpleadorBusiness empleadorBusiness = new EmpleadorBusiness();
        try {
            ofertas = ofertaBusiness.buscarOfertasFavoritas((Solicitante) sessionMap.get("solicitante"));

            for (int i = 0; i < ofertas.size(); i++) {
                Oferta oferta = ofertas.get(i);
                int idEmpleador = oferta.getEmpleador().getId();
                Empleador empleador = empleadorBusiness.buscarEmpleador(idEmpleador);
                oferta.setEmpleador(empleador);
                ofertas.set(i, oferta);
            }
        } catch (SQLException e) {
            Logger.getLogger(BuscarOfertasFavoritasAction.class.getName()).log(Level.SEVERE, null, e);
        }
        return BUSCAR_OFERTAS;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public LinkedList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(LinkedList<Oferta> ofertas) {
        this.ofertas = ofertas;
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

}
