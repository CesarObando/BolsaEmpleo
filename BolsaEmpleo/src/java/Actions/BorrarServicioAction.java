/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Actions;

import Business.ServicioBusiness;
import Dominio.Servicio;
import Dominio.Usuario;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ricardo
 */
public class BorrarServicioAction extends ActionSupport implements Preparable, ModelDriven, ServletRequestAware, SessionAware {
    
    private SessionMap<String, Object> sessionMap;
    private HttpServletRequest request;
    private Usuario usuario;
    private boolean dialogo;
    private LinkedList<Servicio> servicios;
    private int idServ;
    private Servicio servicio;

    @Override
    public void prepare() throws Exception {
        this.usuario = (Usuario) this.sessionMap.get("usuario");
        servicios = new LinkedList<>();
        servicios = new ServicioBusiness().obtenerServicios();
    }

    @Override
    public String execute() throws Exception {
                //        if (this.sessionMap.get("usuario") == null){
//            return LOGIN;
//        }
        return INPUT;
    }
    
    public String actualizarCamposServicio() throws Exception {
        servicio = new Servicio();
        servicio = new ServicioBusiness().obtenerServicio(idServ);
        return SUCCESS;
    }
    
    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
    
    @Override
    public Object getModel() {
        return servicio;
    }

    public LinkedList<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(LinkedList<Servicio> servicios) {
        this.servicios = servicios;
    }

    public int getIdServ() {
        return idServ;
    }

    public void setIdServ(int idServ) {
        this.idServ = idServ;
    }

    public SessionMap<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(SessionMap<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isDialogo() {
        return dialogo;
    }

    public void setDialogo(boolean dialogo) {
        this.dialogo = dialogo;
    }
    
    

    public String borrarServicio(){
                //        if (this.sessionMap.get("usuario") == null){
//            return LOGIN;
//        }
        try {
                ServicioBusiness servBus=new ServicioBusiness();
                servBus.eliminarServicio(idServ);
                return SUCCESS;
            } catch (SQLException ex) {
                return ERROR;
            }
    }
    
    @Override
    public void validate() {
        if (this.servicio.getNombre().trim().isEmpty()) {
            addFieldError("servicio.nombre", "Debe elegir un servicio");
        }
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

}



