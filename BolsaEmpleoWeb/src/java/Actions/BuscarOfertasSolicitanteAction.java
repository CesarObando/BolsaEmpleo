/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.CategoriaBusiness;
import Business.OfertaBusiness;
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
public class BuscarOfertasSolicitanteAction extends ActionSupport implements Preparable, ServletRequestAware{
    
    private final String BUSCAR_OFERTAS = "buscarOfertasSolicitante";
    private LinkedList<Oferta> ofertas; 
    private HttpServletRequest request;
    private String puesto;
    private int categoria;
    private LinkedList categorias;
    
    public BuscarOfertasSolicitanteAction() {
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
    
    public String buscar() throws DataException{
        OfertaBusiness ofertaBusiness = new OfertaBusiness();
        puesto = request.getParameter("puesto");
        categoria = Integer.parseInt(request.getParameter("categoria.id"));
        try {
            ofertas = ofertaBusiness.getOfertasPorCategoria(categoria, puesto);
        } catch (SQLException e) {
            Logger.getLogger(BuscarOfertasSolicitanteAction.class.getName()).log(Level.SEVERE, null, e);
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