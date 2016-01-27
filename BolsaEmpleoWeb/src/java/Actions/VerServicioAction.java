/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.CategoriaBusiness;
import Business.ServicioBusiness;
import Business.SolicitanteBusiness;
import Dominio.Categoria;
import Dominio.Servicio;
import Dominio.Solicitante;
import static com.opensymphony.xwork2.Action.ERROR;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Tin
 */
public class VerServicioAction extends ActionSupport implements SessionAware, Preparable, ModelDriven<Servicio>, ServletRequestAware {

    private Servicio servicioAVer;
    private String mensaje;
    private boolean existe;
    private HttpServletRequest request;
    private SessionMap<String, Object> sessionMap;

    public VerServicioAction() {
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
        int idServicio = Integer.parseInt(request.getParameter("id"));
        servicioAVer = new ServicioBusiness().buscarServicio(idServicio);
        int idCategoria = servicioAVer.getCategoria().getId();
        int idSolicitante = servicioAVer.getSolicitante().getId();
        Categoria categoria = new CategoriaBusiness().buscarCategoria(idCategoria);
        Solicitante solicitante = new SolicitanteBusiness().buscarSolicitante(idSolicitante);
        servicioAVer.setCategoria(categoria);
        servicioAVer.setSolicitante(solicitante);
        sessionMap.put("servicio", servicioAVer);
    }

    @Override
    public Servicio getModel() {
        return this.servicioAVer;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public Servicio getServicioAVer() {
        return servicioAVer;
    }

    public void setServicioAVer(Servicio servicioAVer) {
        this.servicioAVer = servicioAVer;
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

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

}
