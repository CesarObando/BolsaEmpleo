/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.CategoriaBusiness;
import Dominio.Categoria;
import Exception.DataException;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Cesar
 */
public class EditarCategoriaAction extends ActionSupport implements Preparable, ModelDriven<Categoria>, ServletRequestAware {

    private Categoria categoriaAEditar;
    private String mensaje;
    private HttpServletRequest request;
    private boolean existe;

    public EditarCategoriaAction() {
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
        int id = Integer.parseInt(request.getParameter("id"));
        categoriaAEditar = new CategoriaBusiness().buscarCategoria(id);
    }

    @Override
    public Categoria getModel() {
        return this.categoriaAEditar;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    @Override
    public void validate() {
        if (categoriaAEditar.getNombre().length() == 0 || categoriaAEditar.getNombre().equals(null)) {
            addFieldError("nombre", "Debe ingresar un nombre.");
        }
    }

    public String editar() throws DataException {
        CategoriaBusiness categoriaBusiness = new CategoriaBusiness();
        boolean editado = true;
        try {
            categoriaBusiness.editarCategoria(categoriaAEditar);
        } catch (SQLException e) {
            editado = false;
            mensaje = "Ocurrió un error con la base de datos.Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
        }
        if (editado == true) {
            this.mensaje = "La categoria fue editada correctamente";
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public Categoria getCategoriaAEditar() {
        return categoriaAEditar;
    }

    public void setCategoriaAEditar(Categoria categoriaAEditar) {
        this.categoriaAEditar = categoriaAEditar;
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
