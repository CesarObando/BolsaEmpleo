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

public class VerOfertaSolicitanteAction extends ActionSupport implements Preparable, ModelDriven<Oferta>, ServletRequestAware, SessionAware {

    //Variables globales
    private Oferta ofertaAVer;
    private String mensaje;
    private boolean insertado;
    private HttpServletRequest request;
    private SessionMap<String, Object> sessionMap;

    public VerOfertaSolicitanteAction() {
    }

    @Override
    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        int idOferta;
        if (request.getParameter("id") != null) {
            //Captura el id de la oferta
            idOferta = Integer.parseInt(request.getParameter("id"));
            //Llamado al metodo que realiza la busqueda
            ofertaAVer = new OfertaBusiness().buscarOferta(idOferta);
            if (sessionMap.get("solicitante") != null) {
                //Llamado al metodo que realiza la busqueda
                insertado = new OfertaBusiness().buscarOfertaFavorita(ofertaAVer, (Solicitante) sessionMap.get("solicitante"));
            }
        }
    }

    public String solicitar() throws SQLException, DataException {
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        SolicitudBusiness solicitudBusiness = new SolicitudBusiness();
        //Inicializa el objeto
        Solicitud solicitud = new Solicitud();
        Solicitante solicitante;
        //Captura el id de la oferta
        int idOferta = Integer.parseInt(request.getParameter("id"));
        //Llamado al metodo que realiza la busqueda
        ofertaAVer = new OfertaBusiness().buscarOferta(idOferta);
        //Asigna valor a la variable
        insertado = true;
        try {
            //Captura el objeto en sesion
            solicitante = (Solicitante) sessionMap.get("solicitante");
            //Asigna el solicitante a la solicitud
            solicitud.setSolicitante(solicitante);
            //Evita que ponga un String en salario
            if (request.getParameter("salario1") != null) {
                ofertaAVer.setSalario(0);
            }
            //Asigna la oferta a la solicitud
            solicitud.setOferta(ofertaAVer);
            //Llamado al metodo que realiza la insercion
            solicitudBusiness.insertarSolicitud(solicitud);
        } catch (SQLException e) {
            //Asigna valor a la variable
            insertado = false;
            //Define un mensaje que sera mostrado al usuario
            mensaje = "Ocurrió un error con la base de datos.Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
            //Coloca en sesion al mensaje
            sessionMap.put("mensaje", mensaje);
            //Coloca el mensaje como mensaje de error
            addActionError(mensaje);
        }
        if (insertado == true) {
            //Define un mensaje que sera mostrado al usuario
            this.mensaje = "El solicitante fue editado correctamente";
            //Coloca en sesion al mensaje
            sessionMap.put("mensaje", mensaje);
            //Coloca el mensaje como mensaje del action
            addActionMessage(mensaje);
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public String marcarOfertaFavorita() throws SQLException, DataException {
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        OfertaBusiness ofertaBusiness = new OfertaBusiness();
        Solicitante solicitante;
        //Captura el id de la oferta
        int idOferta = Integer.parseInt(request.getParameter("id"));
        //Llamado al metodo que realiza la busqueda
        ofertaAVer = new OfertaBusiness().buscarOferta(idOferta);
        try {
            //Captura el objeto en sesion
            solicitante = (Solicitante) sessionMap.get("solicitante");
            //Llamado al metodo que realiza la insercion
            ofertaBusiness.insertaOfertaFavorita(ofertaAVer, solicitante);
            return SUCCESS;
        } catch (SQLException e) {
            //Asigna valor a la variable
            insertado = false;
            //Define un mensaje que sera mostrado al usuario
            mensaje = "Ocurrió un error con la base de datos.Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
            //Coloca en sesion al mensaje
            sessionMap.put("mensaje", mensaje);
            //Coloca el mensaje como mensaje de error
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

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

    //Setter-Getter
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

    public boolean isInsertado() {
        return insertado;
    }

    public void setInsertado(boolean insertado) {
        this.insertado = insertado;
    }

}
