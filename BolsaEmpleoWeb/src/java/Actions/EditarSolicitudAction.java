/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.SolicitudBusiness;
import Dominio.Solicitud;
import Exception.DataException;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Cesar
 */
public class EditarSolicitudAction extends ActionSupport implements Preparable, ModelDriven<Solicitud>, ServletRequestAware, SessionAware {

    private Solicitud solicitudAEditar;
    private String mensaje;
    private HttpServletRequest request;
    private boolean existe;
    private SessionMap<String, Object> sessionMap;

    public EditarSolicitudAction() {
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
        solicitudAEditar = (Solicitud) sessionMap.get("solicitud");

    }

    @Override
    public Solicitud getModel() {
        return this.solicitudAEditar;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public String editar() throws DataException {
        SolicitudBusiness solicitudBusiness = new SolicitudBusiness();
        boolean editado = true;
        try {
            if (solicitudAEditar.isFavorito()) {
                solicitudAEditar.setFavorito(false);
            } else {
                solicitudAEditar.setFavorito(true);
            }
            solicitudBusiness.editarSolicitud(solicitudAEditar);
        } catch (SQLException e) {
            editado = false;
            mensaje = "Ocurrió un error con la base de datos.Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
            sessionMap.put("mensaje", mensaje);
            addActionError(mensaje);
        }
        if (editado == true) {
            this.mensaje = "La solicitud fue editada correctamente";
            sessionMap.put("mensaje", mensaje);
            addActionMessage(mensaje);
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public Solicitud getSolicitudAEditar() {
        return solicitudAEditar;
    }

    public void setSolicitudAEditar(Solicitud solicitudAEditar) {
        this.solicitudAEditar = solicitudAEditar;
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
