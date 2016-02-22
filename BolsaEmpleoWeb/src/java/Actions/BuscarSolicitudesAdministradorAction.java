/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.CategoriaBusiness;
import Business.EmpleadorBusiness;
import Business.OfertaBusiness;
import Business.SolicitanteBusiness;
import Business.SolicitudBusiness;
import Dominio.Empleador;
import Dominio.Oferta;
import Dominio.Solicitante;
import Dominio.Solicitud;
import Exception.DataException;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author JonathanA
 */
public class BuscarSolicitudesAdministradorAction extends ActionSupport implements Preparable, ServletRequestAware {

    private final String BUSCAR_SOLICITUDES = "buscarSolicitudesAdministrador";
    private LinkedList<Solicitud> solicitudes;
    private HttpServletRequest request;
    private String puesto;
    private int categoria;
    private LinkedList categorias;

    public BuscarSolicitudesAdministradorAction() {
    }

    @Override
    public String execute() throws Exception {
        return ActionSupport.SUCCESS;
    }

    @Override
    public void prepare() throws Exception {
        CategoriaBusiness categoriaBusiness = new CategoriaBusiness();
        this.categorias = categoriaBusiness.getCategorias();
    }

    public String buscar() throws DataException {
        SolicitudBusiness solicitudBusiness = new SolicitudBusiness();
        EmpleadorBusiness empleadorBusiness = new EmpleadorBusiness();
        OfertaBusiness ofertaBusiness = new OfertaBusiness();
        SolicitanteBusiness solicitanteBusiness = new SolicitanteBusiness();
        puesto = request.getParameter("puesto");
        categoria = -1;
        if (request.getParameter("categoria.id") != null) {
            categoria = Integer.parseInt(request.getParameter("categoria.id"));
        }
        try {
            solicitudes = solicitudBusiness.getOfertasPorCategorias(categoria, puesto);

            for (int i = 0; i < solicitudes.size(); i++) {
                Solicitud solicitud = solicitudes.get(i);
                int idSolicitante = solicitud.getSolicitante().getId();
                int idOferta = solicitud.getOferta().getId();
                Solicitante solicitante = solicitanteBusiness.buscarSolicitante(idSolicitante);
                Oferta oferta = ofertaBusiness.buscarOferta(idOferta);
                int idEmpleador = oferta.getEmpleador().getId();
                Empleador empleador = empleadorBusiness.buscarEmpleador(idEmpleador);
                oferta.setEmpleador(empleador);
                solicitud.setSolicitante(solicitante);
                solicitud.setOferta(oferta);
                solicitudes.set(i, solicitud);
            }
        } catch (SQLException e) {
            Logger.getLogger(BuscarSolicitudesAdministradorAction.class.getName()).log(Level.SEVERE, null, e);
        }
        return BUSCAR_SOLICITUDES;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public String getNombre() {
        return puesto;
    }

    public void setNombre(String nombre) {
        this.puesto = nombre;
    }

    public LinkedList<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(LinkedList<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public LinkedList getCategorias() {
        return categorias;
    }

    public void setCategorias(LinkedList categorias) {
        this.categorias = categorias;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

}
