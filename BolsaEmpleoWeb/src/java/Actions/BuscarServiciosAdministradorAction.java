/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.CategoriaBusiness;
import Business.ServicioBusiness;
import Business.SolicitanteBusiness;
import Dominio.Servicio;
import Dominio.Solicitante;
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
public class BuscarServiciosAdministradorAction extends ActionSupport implements Preparable, ServletRequestAware {

    private final String BUSCAR_SERVICIOS = "buscarServiciosAdministrador";
    private LinkedList<Servicio> servicios;
    private HttpServletRequest request;
    private String titulo;
    private int categoria;
    private LinkedList categorias;

    public BuscarServiciosAdministradorAction() {
    }

    @Override
    public String execute() throws Exception {
        return ActionSupport.SUCCESS;
    }

    @Override
    public void prepare() throws Exception {
        CategoriaBusiness categoriaBusiness = new CategoriaBusiness();
        ServicioBusiness servicioBusiness = new ServicioBusiness();
        SolicitanteBusiness solicitanteBusiness = new SolicitanteBusiness();
        this.categorias = categoriaBusiness.getCategorias();
        titulo = "";
        categoria = -1;
        if (request.getParameter("categoria.id") != null) {
            categoria = Integer.parseInt(request.getParameter("categoria.id"));
        }
        if (request.getParameter("titulo") != null) {
            titulo = request.getParameter("titulo");
        }
        servicios = servicioBusiness.buscarServiciosFiltrados(categoria, titulo);

        for (int i = 0; i < servicios.size(); i++) {
            Servicio servicio = servicios.get(i);
            int idSolicitante = servicio.getSolicitante().getId();
            Solicitante solicitante = solicitanteBusiness.buscarSolicitante(idSolicitante);
            servicio.setSolicitante(solicitante);
            servicios.set(i, servicio);
        }

    }

    public String buscar() throws DataException {
        ServicioBusiness servicioBusiness = new ServicioBusiness();
        SolicitanteBusiness solicitanteBusiness = new SolicitanteBusiness();
        titulo = request.getParameter("titulo");
        categoria = -1;
        if (request.getParameter("categoria.id") != null) {
            categoria = Integer.parseInt(request.getParameter("categoria.id"));
        }
        try {
            servicios = servicioBusiness.buscarServiciosFiltrados(categoria, titulo);

            for (int i = 0; i < servicios.size(); i++) {
                Servicio servicio = servicios.get(i);
                int idSolicitante = servicio.getSolicitante().getId();
                Solicitante solicitante = solicitanteBusiness.buscarSolicitante(idSolicitante);
                servicio.setSolicitante(solicitante);
                servicios.set(i, servicio);
            }
        } catch (SQLException e) {
            Logger.getLogger(BuscarServiciosAdministradorAction.class.getName()).log(Level.SEVERE, null, e);
        }
        return BUSCAR_SERVICIOS;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public LinkedList<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(LinkedList<Servicio> servicios) {
        this.servicios = servicios;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public LinkedList getCategorias() {
        return categorias;
    }

    public void setCategorias(LinkedList categorias) {
        this.categorias = categorias;
    }

}