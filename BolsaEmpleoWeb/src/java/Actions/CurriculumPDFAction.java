package Actions;

import Data.BaseData;
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

public class CurriculumPDFAction extends ActionSupport implements Preparable, ModelDriven<Solicitante>, ServletRequestAware, SessionAware {

    private Connection conexion;
    private Map parametros;
    private Solicitante solicitanteAVer;
    private HttpServletRequest request;
    public SessionMap<String, Object> sessionMap;

    public void prepare() throws Exception {
        solicitanteAVer = (Solicitante) sessionMap.get("solicitanteO");

    }

    public String reportePDF() throws SQLException, IOException {
        //Define un objeto BaseData que se comunica con la base de datos
        BaseData baseData = new BaseData();
        //Establece la conexion
        setConexion(baseData.getConnection());
        //Obtiene el objeto en sesion
        solicitanteAVer = (Solicitante) sessionMap.get("solicitanteO");
        //Inicializa el mapa de parametros
        parametros = new HashMap();
        //Incluye el atributo al mapa de parametros
        parametros.put("idSolicitante", solicitanteAVer.getId());
        return SUCCESS;
    }

    @Override
    public Solicitante getModel() {
        return this.solicitanteAVer;
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

}
