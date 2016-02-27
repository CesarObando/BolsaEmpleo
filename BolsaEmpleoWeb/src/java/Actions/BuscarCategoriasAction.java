package Actions;

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

public class BuscarCategoriasAction extends ActionSupport implements Preparable, ServletRequestAware {

    //Variables globales
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
    }

    public String buscar() throws DataException {
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        CategoriaBusiness categoriaBuss = new CategoriaBusiness();
        //Captura de los campos de busqueda en el jsp
        nombre = request.getParameter("nombre");
        try {
            //Llamado al metodo que realiza la busqueda de categorias con su parametro de busqueda
            categorias = categoriaBuss.buscarCategoriasFiltradas(nombre);
        } catch (SQLException e) {
            Logger.getLogger(BuscarCategoriasAction.class.getName()).log(Level.SEVERE, null, e);
        }
        return BUSCAR_CATEGORIAS;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }
    
    //Setter-Getter
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
