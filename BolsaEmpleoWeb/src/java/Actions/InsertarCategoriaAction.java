/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.CategoriaBusiness;
import Dominio.Categoria;
import Exception.DataException;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
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
public class InsertarCategoriaAction extends ActionSupport implements Preparable, ModelDriven<Categoria>, ServletRequestAware {

    private Categoria categoriaAInsertar;
    private String mensaje;
    private HttpServletRequest request;

    public InsertarCategoriaAction() {
    }

    @Override
    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        this.categoriaAInsertar = new Categoria();
        this.mensaje = "";
    }

    @Override
    public Categoria getModel() {
        return this.categoriaAInsertar;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    @Override
    public void validate() {
        if (categoriaAInsertar.getNombre().length() == 0 ){
            addFieldError("nombre", "Debe ingresar un nombre.");
        }
    }

    public String insertar() throws DataException {
        CategoriaBusiness categoriaBusiness = new CategoriaBusiness();
        boolean insertado = true;
        try {
            categoriaBusiness.insertarCategoria(categoriaAInsertar);
        } catch (SQLException e) {
            insertado = false;
            mensaje = "Ocurrió un error con la base de datos.Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
        }
        if (insertado == true) {
            this.mensaje = "La categoria fue insertada correctamente";
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public Categoria getCategoriaAInsertar() {
        return categoriaAInsertar;
    }

    public void setCategoriaAInsertar(Categoria categoriaAInsertar) {
        this.categoriaAInsertar = categoriaAInsertar;
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

}
