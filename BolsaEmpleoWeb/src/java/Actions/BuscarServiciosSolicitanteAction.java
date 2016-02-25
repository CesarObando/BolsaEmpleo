/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.CategoriaBusiness;
import Business.ServicioBusiness;
import Dominio.Servicio;
import Dominio.Solicitante;
import Exception.DataException;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author JonathanA
 */
public class BuscarServiciosSolicitanteAction extends ActionSupport implements Preparable, ServletRequestAware, ModelDriven<Solicitante>, SessionAware {

    private final String BUSCAR_SERVICIOS = "buscarServicios";
    private LinkedList<Servicio> servicios;
    private HttpServletRequest request;
    private String titulo;
    private int categoria;
    private String provincia;
    private String canton;
    private int idSolicitante;
    private LinkedList categorias;
    private Solicitante solicitante;
    public SessionMap<String, Object> sessionMap;

    public BuscarServiciosSolicitanteAction() {
    }

    @Override
    public String execute() throws Exception {
        return ActionSupport.SUCCESS;
    }

    @Override
    public void prepare() throws Exception {
        //Obtiene el objeto en sesion
        solicitante = (Solicitante) sessionMap.get("solicitante");
        solicitante = (Solicitante) request.getSession().getAttribute("solicitante");
        //Obtiene el id del objeto
        idSolicitante = solicitante.getId();
        //Llamado al metodo que realiza la busqueda
        servicios = new ServicioBusiness().buscarServiciosPorSolicitante(-1, "", idSolicitante, "", "");
    }

    public String buscar() throws DataException {
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        ServicioBusiness servicioBusiness = new ServicioBusiness();
        //Captura de los campos de busqueda en el jsp
        titulo = request.getParameter("titulo");
        categoria = Integer.parseInt(request.getParameter("categoria.id"));
        provincia = request.getParameter("provincia");
        canton = request.getParameter("canton");
        //Obtiene el id del objeto
        idSolicitante = solicitante.getId();
        try {
            //Llamado al metodo que realiza la busqueda
            servicios = servicioBusiness.buscarServiciosPorSolicitante(categoria, titulo, idSolicitante, provincia,canton);
        } catch (SQLException e) {
            Logger.getLogger(BuscarServiciosSolicitanteAction.class.getName()).log(Level.SEVERE, null, e);
        }
        return BUSCAR_SERVICIOS;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }
    
    @Override
    public Solicitante getModel() {
        return this.solicitante;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }
    
    //Setter-Getter
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

    public int getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(int idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public LinkedList getCategorias() {
        return categorias;
    }

    public void setCategorias(LinkedList categorias) {
        this.categorias = categorias;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

}
