/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.AdministradorBusiness;
import Business.CategoriaBusiness;
import Business.OfertaBusiness;
import Dominio.Administrador;
import Dominio.Categoria;
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
public class BuscarOfertasAction extends ActionSupport implements Preparable, ServletRequestAware{
    
    private final String BUSCAR_OFERTAS = "buscarOfertas";
    private LinkedList<Oferta> ofertas;
    private LinkedList<Categoria> categorias; 
    private HttpServletRequest request;
    private String categoria;
    private String puesto;
    
    public BuscarOfertasAction() {
    }
    
    public String execute() throws Exception {
        return ActionSupport.SUCCESS;
    }

    @Override
    public void prepare() throws Exception {
        CategoriaBusiness categoriaBusiness = new CategoriaBusiness();
        categorias = categoriaBusiness.getCategorias();
        OfertaBusiness ofertaBusiness = new OfertaBusiness();
        categoria = request.getParameter("categoria");
        puesto = request.getParameter("puesto");
        ofertas = ofertaBusiness.getOfertasPorCategoria(categoria, puesto);
    }
    
    public String buscar() throws DataException{
        OfertaBusiness ofertaBusiness = new OfertaBusiness();
        categoria = request.getParameter("cedula");
        puesto = request.getParameter("nombre");
        try {
            ofertas = ofertaBusiness.getOfertasPorCategoria(categoria, puesto);
        } catch (SQLException e) {
            Logger.getLogger(BuscarOfertasAction.class.getName()).log(Level.SEVERE, null, e);
        }
        return BUSCAR_OFERTAS;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public String getCedula() {
        return categoria;
    }

    public void setCedula(String cedula) {
        this.categoria = cedula;
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

    public LinkedList<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(LinkedList<Categoria> categorias) {
        this.categorias = categorias;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
    
}
