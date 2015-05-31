/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.OferenteBusiness;
import Dominio.Oferente;
import Dominio.Usuario;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
public class BorrarOferenteAction extends ActionSupport implements ModelDriven,Preparable,ServletRequestAware, SessionAware  {

    private SessionMap<String, Object> sessionMap;
    private HttpServletRequest request;
    private Usuario usuario;
    private boolean dialogo;
    private LinkedList<Oferente> postulantes;
    private String cedul;
    private Oferente oferente;
    private String fechaNacimientoString;
   
    /**
     *
     * @throws Exception
     */
    @Override
    public void prepare() throws Exception {
        this.usuario = (Usuario) this.sessionMap.get("usuario");
        postulantes = new LinkedList<>();
        postulantes = new OferenteBusiness().getPostulantes();
    }

    @Override
    public String execute() throws Exception {
        //        if (this.sessionMap.get("usuario") == null){
//            return LOGIN;
//        }
        dialogo = false;
        return INPUT;
    }
    
    public String actualizarCamposOferente() throws Exception {
        //        if (this.sessionMap.get("usuario") == null){
//            return LOGIN;
//        }
        oferente = new Oferente();
        oferente = new OferenteBusiness().getPostulante(cedul);
        if (oferente.getFechaNacimiento() != null) {
            SimpleDateFormat fechaActualString = new SimpleDateFormat("dd-MM-yyyy");
            fechaNacimientoString = fechaActualString.format(oferente.getFechaNacimiento());
        }
        return SUCCESS;
    }

    public Oferente getOferente() {
        return oferente;
    }

    public void setOferente(Oferente oferente) {
        this.oferente = oferente;
    }

    public String getFechaNacimientoString() {
        return fechaNacimientoString;
    }

    public void setFechaNacimientoString(String fechaNacimientoString) {
        this.fechaNacimientoString = fechaNacimientoString;
    }

    public LinkedList<Oferente> getPostulantes() {
        return postulantes;
    }

    public void setPostulantes(LinkedList<Oferente> postulantes) {
        this.postulantes = postulantes;
    }

    public String getCedul() {
        return cedul;
    }

    public void setCedul(String cedul) {
        this.cedul = cedul;
    }

    public String borrarOferente() {
        //        if (this.sessionMap.get("usuario") == null){
//            return LOGIN;
//        }
        try {
            OferenteBusiness oferenteBus = new OferenteBusiness();
            oferenteBus.borrarPostulante(cedul);
            return SUCCESS;
        } catch (SQLException ex) {
            return ERROR;
        }
    }

    @Override
    public void validate() {
        if (this.request.getParameter("oferente.cedula").trim().equals("")) {
            addFieldError("cedul", "La cedula del oferente no puede estar en blanco");
        }
       
    }
    
        public boolean isDialogo() {
        return dialogo;
    }

    public void setDialogo(boolean dialogo) {
        this.dialogo = dialogo;
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
    
    @Override
    public Object getModel() {
        return oferente;
    }

    public SessionMap<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(SessionMap<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
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