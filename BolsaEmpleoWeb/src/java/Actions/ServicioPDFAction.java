/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Data.BaseData;
import Dominio.Servicio;
import Dominio.Solicitante;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Tin
 */
public class ServicioPDFAction  extends ActionSupport implements Preparable, ModelDriven<Solicitante>, ServletRequestAware, SessionAware {
      private Connection conexion;
    private Map parametros;
    private boolean existe;
    private Solicitante solicitanteAVer;
    private Servicio servicioAVer;
    private HttpServletRequest request;
    public SessionMap<String, Object> sessionMap;

    public void prepare() throws Exception {
        existe = true;

        solicitanteAVer = (Solicitante) sessionMap.get("solicitanteO");

    }

    public String reportePDF() throws SQLException, IOException {
        BaseData bas = new BaseData();
        setConexion(bas.getConnection());
        solicitanteAVer = (Solicitante) sessionMap.get("solicitanteO");
        servicioAVer=(Servicio) sessionMap.get("servicio");
        parametros = new HashMap();
        parametros.put("idSolicitante", solicitanteAVer.getId());
        parametros.put("idServicio", servicioAVer.getId());
        return SUCCESS;
    }

    @Override
    public Solicitante getModel() {
        return this.solicitanteAVer; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr; //To change body of generated methods, choose Tools | Templates.
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public Map getParametros() {
        return parametros;
    }

    public void setParametros(Map parametros) {
        this.parametros = parametros;
    }

    public Solicitante getSolicitanteAVer() {
        return solicitanteAVer;
    }

    public void setSolicitanteAVer(Solicitante solicitanteAVer) {
        this.solicitanteAVer = solicitanteAVer;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;//To change body of generated methods, choose Tools | Templates.
    }

    public SessionMap<String, Object> getSessionMap() {
        return sessionMap;
    }

    public Servicio getServicioAVer() {
        return servicioAVer;
    }

    public void setServicioAVer(Servicio servicioAVer) {
        this.servicioAVer = servicioAVer;
    }

}


