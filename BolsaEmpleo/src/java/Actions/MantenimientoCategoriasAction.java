/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.CategoriaBusiness;
import Dominio.Categoria;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.util.LinkedList;

/**
 *
 * @author Cesar
 */
public class MantenimientoCategoriasAction extends ActionSupport implements Preparable{
    
    private LinkedList<Categoria> categorias;
    private CategoriaBusiness categoriaBusiness;

    public MantenimientoCategoriasAction() {
    }
    
    @Override
    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        categoriaBusiness = new CategoriaBusiness();
        categorias = categoriaBusiness.getCategorias();
    }

    public LinkedList<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(LinkedList<Categoria> categorias) {
        this.categorias = categorias;
    }
    
}
