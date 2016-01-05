/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.AdministradorBusiness;
import Business.CategoriaBusiness;
import Dominio.Categoria;
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
 * @author Tin
 */
public class BuscarCategoriasAction extends ActionSupport implements Preparable, ServletRequestAware {
 
    private final String BUSCAR_CATEGORIAS = "buscarCategorias";
    private LinkedList<Categoria> categorias;
    private HttpServletRequest request;
    private String nombre;
    
    @Override
      public String execute() throws Exception {
        return ActionSupport.SUCCESS;
    }
    @Override
    public void prepare() throws Exception {
        CategoriaBusiness categoriaBuss= new CategoriaBusiness();
        nombre = request.getParameter("nombre");
        categorias=categoriaBuss.getCategoriasFiltradas(nombre);
         
    }
    public String buscar() throws DataException{
         CategoriaBusiness categoriaBuss= new CategoriaBusiness();
        
        nombre = request.getParameter("nombre");
      
        try {
            categorias= categoriaBuss.getCategoriasFiltradas(nombre);
        } catch (SQLException e) {
            Logger.getLogger(BuscarCategoriasAction.class.getName()).log(Level.SEVERE, null, e);
        }
        return BUSCAR_CATEGORIAS;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public LinkedList<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(LinkedList<Categoria> categorias) {
        this.categorias = categorias;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
