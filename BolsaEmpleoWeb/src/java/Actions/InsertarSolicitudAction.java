/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.SolicitudBusiness;
import Dominio.Administrador;
import Dominio.Solicitud;
import Exception.DataException;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.jboss.weld.context.http.HttpRequestContext;

/**
 *
 * @author JonathanA
 */
public class InsertarSolicitudAction extends ActionSupport implements Preparable, ModelDriven<Solicitud>, ServletRequestAware {

    private Solicitud solicitudInsertar;
    private String mensaje;
    private HttpServletRequest request;

    public InsertarSolicitudAction() {
    }

    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        solicitudInsertar = new Solicitud();
    }

    @Override
    public Solicitud getModel() {
        return this.solicitudInsertar;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public String insertar() throws DataException {
        SolicitudBusiness solicitudBusiness = new SolicitudBusiness();
        boolean insertado = false;
        try {
            solicitudBusiness.insertarSolicitud(solicitudInsertar);
        } catch (SQLException e) {
            insertado = false;
            mensaje = "Ocurrió un error con la base de datos. Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
        }
        if (insertado == true) {
            this.mensaje = "La solicitud fue registrada exitosamente.";
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public Solicitud getSolicitudInsertar() {
        return solicitudInsertar;
    }

    public void setSolicitudInsertar(Solicitud solicitudInsertar) {
        this.solicitudInsertar = solicitudInsertar;
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
