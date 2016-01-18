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
public class BuscarOfertasAdministradorAction extends ActionSupport implements Preparable, ServletRequestAware {

    private final String BUSCAR_OFERTAS = "buscarOfertasAdministrador";
    private LinkedList<Oferta> ofertas;
    private HttpServletRequest request;
    private String puesto;
    private int categoria;
    private LinkedList categorias;

    public BuscarOfertasAdministradorAction() {
    }

    @Override
    public String execute() throws Exception {
        return ActionSupport.SUCCESS;
    }

    @Override
    public void prepare() throws Exception {
        CategoriaBusiness categoriaBusiness = new CategoriaBusiness();
        OfertaBusiness ofertaBusiness = new OfertaBusiness();
        EmpleadorBusiness empleadorBusiness = new EmpleadorBusiness();
        this.categorias = categoriaBusiness.getCategorias();
        puesto = "";
        categoria = -1;
        if (request.getParameter("categoria.id") != null) {
            categoria = Integer.parseInt(request.getParameter("categoria.id"));
        }
        if(request.getParameter("puesto")!=null){
            puesto=request.getParameter("puesto");
        }
        ofertas = ofertaBusiness.getOfertasPorCategoria(categoria, puesto);

        for (int i = 0; i < ofertas.size(); i++) {
            Oferta oferta = ofertas.get(i);
            int idEmpleador = oferta.getEmpleador().getId();
            Empleador empleador = empleadorBusiness.buscarEmpleador(idEmpleador);
            oferta.setEmpleador(empleador);
            ofertas.set(i, oferta);
        }

    }

    public String buscar() throws DataException {
        OfertaBusiness ofertaBusiness = new OfertaBusiness();
        EmpleadorBusiness empleadorBusiness = new EmpleadorBusiness();
        puesto = request.getParameter("puesto");
        categoria = -1;
        if (request.getParameter("categoria.id") != null) {
            categoria = Integer.parseInt(request.getParameter("categoria.id"));
        }
        try {
            ofertas = ofertaBusiness.getOfertasPorCategoria(categoria, puesto);

            for (int i = 0; i < ofertas.size(); i++) {
                Oferta oferta = ofertas.get(i);
                int idEmpleador = oferta.getEmpleador().getId();
                Empleador empleador = empleadorBusiness.buscarEmpleador(idEmpleador);
                oferta.setEmpleador(empleador);
                ofertas.set(i, oferta);
            }
        } catch (SQLException e) {
            Logger.getLogger(BuscarOfertasAdministradorAction.class.getName()).log(Level.SEVERE, null, e);
        }
        return BUSCAR_OFERTAS;
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
