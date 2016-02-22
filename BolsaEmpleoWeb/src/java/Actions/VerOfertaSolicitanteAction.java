/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.OfertaBusiness;
import Business.SolicitudBusiness;
import Dominio.Oferta;
import Dominio.Solicitante;
import Dominio.Solicitud;
import Exception.DataException;
import static com.opensymphony.xwork2.Action.ERROR;
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
 * @author Tin
 */
public class VerOfertaSolicitanteAction extends ActionSupport implements Preparable, ModelDriven<Oferta>, ServletRequestAware, SessionAware {

    private Oferta ofertaAVer;
    private String mensaje;
    private boolean existe;
    private boolean insertado;
    private HttpServletRequest request;
    private SessionMap<String, Object> sessionMap;

    public VerOfertaSolicitanteAction() {
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
        int idOferta;
        try {
            if (request.getParameter("id") != null) {
                idOferta = Integer.parseInt(request.getParameter("id"));
                ofertaAVer = new OfertaBusiness().buscarOferta(idOferta);
                if (sessionMap.get("solicitante") != null) {
                    insertado = new OfertaBusiness().buscarOfertaFavorita(ofertaAVer, (Solicitante) sessionMap.get("solicitante"));
                }
            }
        } catch (SQLException e) {
            existe = false;
        }
    }

    public String solicitar() throws SQLException, DataException {
        SolicitudBusiness solicitudBusiness = new SolicitudBusiness();
        Solicitud solicitud = new Solicitud();
        Solicitante solicitante = new Solicitante();
        int idOferta = Integer.parseInt(request.getParameter("id"));
        ofertaAVer = new OfertaBusiness().buscarOferta(idOferta);
        boolean insertado = true;
        try {
            solicitante = (Solicitante) sessionMap.get("solicitante");
            solicitud.setSolicitante(solicitante);
            if (request.getParameter("salario1") != null) {
                ofertaAVer.setSalario(0);
            }
            solicitud.setOferta(ofertaAVer);
            solicitudBusiness.insertarSolicitud(solicitud);
        } catch (SQLException e) {
            insertado = false;
            mensaje = "Ocurrió un error con la base de datos. Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
            sessionMap.put("mensaje", mensaje);
            addActionError(mensaje);
        }
        if (insertado == true) {
            this.mensaje = "La  oferta  fue insertada correctamente";
            sessionMap.put("mensaje", mensaje);
            addActionMessage(mensaje);
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public String marcarOfertaFavorita() throws SQLException, DataException {
        OfertaBusiness ofertaBusiness = new OfertaBusiness();
        Solicitante solicitante = new Solicitante();
        int idOferta = Integer.parseInt(request.getParameter("id"));
        ofertaAVer = new OfertaBusiness().buscarOferta(idOferta);
        try {
            solicitante = (Solicitante) sessionMap.get("solicitante");
            ofertaBusiness.insertaOfertaFavorita(ofertaAVer, solicitante);
            return SUCCESS;
        } catch (SQLException e) {
            insertado = false;
            mensaje = "Ocurrió un error con la base de datos. Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
            sessionMap.put("mensaje", mensaje);
            addActionError(mensaje);
            return ERROR;
        }
    }

    @Override
    public Oferta getModel() {
        return this.ofertaAVer;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public Oferta getOfertaAEliminar() {
        return ofertaAVer;
    }

    public void setOfertaAEliminar(Oferta ofertaAEliminar) {
        this.ofertaAVer = ofertaAEliminar;
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

    public boolean isInsertado() {
        return insertado;
    }

    public void setInsertado(boolean insertado) {
        this.insertado = insertado;
    }

}
