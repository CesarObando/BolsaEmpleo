/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.OferenteBusiness;
import Dominio.Oferente;
import Dominio.Usuario;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
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
public class ListaPostulantesPorVacanteAction extends ActionSupport implements Preparable, ServletRequestAware, SessionAware {

    private SessionMap<String, Object> sessionMap;
    private HttpServletRequest request;
    private Usuario usuario;
    private boolean dialogo;
    private int idPuesto;
    private LinkedList<Oferente> postulantes;
    private String cedul;

    @Override
    public void prepare() throws Exception {
        this.usuario = (Usuario) this.sessionMap.get("usuario");
        postulantes = new LinkedList<>();
        if (this.request.getParameter("idPuesto") != null) {
            this.idPuesto = Integer.parseInt(this.request.getParameter("idPuesto"));
            postulantes = new OferenteBusiness().getPostulantesPorVacante(idPuesto);
        }
    }

    @Override
    public String execute() throws Exception {
        //        if (this.sessionMap.get("usuario") == null){
//            return LOGIN;
//        }
        return INPUT;
    }

    public int getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(int idPuesto) {
        this.idPuesto = idPuesto;
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

    public String borrarOferentePorVacante() {
        //        if (this.sessionMap.get("usuario") == null){
//            return LOGIN;
//        }
        try {
            int value = Integer.parseInt(this.request.getParameter("value"));
            OferenteBusiness oferenteBus = new OferenteBusiness();
            oferenteBus.eliminarOferenteVacante(cedul, idPuesto);
            postulantes = new OferenteBusiness().getPostulantesPorVacante(idPuesto);
            return SUCCESS;
        } catch (SQLException ex) {
            return ERROR;
        }
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

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

}
