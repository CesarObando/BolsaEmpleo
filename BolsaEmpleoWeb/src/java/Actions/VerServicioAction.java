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

public class VerServicioAction extends ActionSupport implements SessionAware, Preparable, ModelDriven<Servicio>, ServletRequestAware {

    //Variables globales
    private Servicio servicioAVer;
    private String mensaje;
    private HttpServletRequest request;
    private SessionMap<String, Object> sessionMap;

    public VerServicioAction() {
    }

    @Override
    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        int idServicio;

            //Captura el id del servicio
            idServicio = Integer.parseInt(request.getParameter("idS"));
        
        //Busca el servicio
        servicioAVer = new ServicioBusiness().buscarServicio(idServicio);
        //Captura el id de la categoria
        int idCategoria = servicioAVer.getCategoria().getId();
        //Captura el id del solicitante
        int idSolicitante = servicioAVer.getSolicitante().getId();
        //Busca la categoria
        Categoria categoria = new CategoriaBusiness().buscarCategoria(idCategoria);
        //Busca el solicitante
        Solicitante solicitante = new SolicitanteBusiness().buscarSolicitante(idSolicitante);
        //Asigna la categoria al servicio
        servicioAVer.setCategoria(categoria);
        //Asigna el solicitante al servicio
        servicioAVer.setSolicitante(solicitante);
        //Coloca en sesion al servicio
        sessionMap.put("servicio", servicioAVer);
        //Coloca en sesion al solicitante
        sessionMap.put("solicitanteO", solicitante);
    }

    @Override
    public Servicio getModel() {
        return this.servicioAVer;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

    //Setter-Getter
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

}
