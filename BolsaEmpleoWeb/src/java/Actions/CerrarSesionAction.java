package Actions;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

public class CerrarSesionAction extends ActionSupport implements SessionAware {

    //Variables globales
    private SessionMap<String, Object> sessionMap;
    private String sesionCerrada;

    public String cerrarSesion() {
        //Cierra la sesion del objeto que se requiera
        sessionMap.remove("solicitante");
        sessionMap.remove("empleador");
        sessionMap.remove("administrador");
        //Limpia el mapa de sesiones
        sessionMap.clear();
        //Pone un mensaje que se muestra al usuario
        sesionCerrada = "La sesión se ha cerrado. No puedes regresar a la pantalla anterior.";
        //Pone en sesion el mensaje
        sessionMap.put("sesionCerrada", sesionCerrada);
        return SUCCESS;
    }
    
    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

    //Setter-Getter
    public String getSesionCerrada() {
        return sesionCerrada;
    }

    public void setSesionCerrada(String sesionCerrada) {
        this.sesionCerrada = sesionCerrada;
    }

}
