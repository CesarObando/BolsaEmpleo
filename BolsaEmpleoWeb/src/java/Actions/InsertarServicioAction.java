package Actions;

import Business.CategoriaBusiness;
import Business.ServicioBusiness;
import Dominio.Servicio;
import Dominio.Solicitante;
import Exception.DataException;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

public class InsertarServicioAction extends ActionSupport implements ModelDriven<Servicio>, Preparable, ServletRequestAware, SessionAware {

    //Variables globales
    private Servicio servicioAInsertar;
    private LinkedList listaCategorias;
    private String mensaje;
    private HttpServletRequest request;
    public SessionMap<String, Object> sessionMap;
    private Solicitante solicitante;
    private int idSolicitante;
    private File archivoImagen;
    private String imagenFileName;
    private String imagenContentType;

    public InsertarServicioAction() {
    }

    @Override
    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        //Obtiene el objeto en sesion
        solicitante = (Solicitante) sessionMap.get("solicitante");
        solicitante = (Solicitante) request.getSession().getAttribute("solicitante");
        //Inicializa el objeto
        servicioAInsertar = new Servicio();
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        CategoriaBusiness categoriaBusiness = new CategoriaBusiness();
        //Llamado al metodo que realiza la busqueda
        this.listaCategorias = categoriaBusiness.buscarCategorias();
    }

    @Override
    public void validate() {
        //Validaciones de los campos de entrada
        if (servicioAInsertar.getTitulo().length() == 0) {
            addFieldError("titulo", "Debe ingresar el título del servicio.");
        }
        if (servicioAInsertar.getDescripcion().length() == 0) {
            addFieldError("descripcion", "Debe ingresar una descripción del servicio.");
        }
        if (servicioAInsertar.getCategoria().getId() == -1) {
            addFieldError("categoria.id", "Debe seleccionar una categoría.");
        }
        if (servicioAInsertar.getProvincia().length() == 0) {
            addFieldError("provincia", "Debe seleccionar una provincia.");
        }
        if (servicioAInsertar.getCanton().length() == 0) {
            addFieldError("canton", "Debe seleccionar un cantón.");
        }
    }

    public String insertar() throws DataException {
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        ServicioBusiness servicioBusiness = new ServicioBusiness();
        //Inicializa las variables
        boolean insertado = true;
        try {
            //Asigna la imagen al objeto a insertar
            cargarImagen();
            //Asigna el solicitante al servicio
            servicioAInsertar.setSolicitante(solicitante);
            //Llamado al metodo que realiza la insercion
            servicioBusiness.insertarServicio(servicioAInsertar);
        } catch (SQLException e) {
            //Asigna valor a la variable
            insertado = false;
            //Define un mensaje que sera mostrado al usuario
            mensaje = "Ocurrió un error con la base de datos. Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
            //Coloca en sesion al mensaje
            sessionMap.put("mensaje", mensaje);
            //Coloca el mensaje como mensaje de error
            addActionError(mensaje);
        }
        if (insertado == true) {
            //Define un mensaje que sera mostrado al usuario
            this.mensaje = "El servicio fue insertado correctamente";
            //Coloca en sesion al mensaje
            addActionMessage(mensaje);
            //Coloca el mensaje como mensaje del action
            sessionMap.put("mensaje", mensaje);
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public void cargarImagen() {
        try {
            //Genera un buffer en memoria que va a almacenar nuestro archivoImagen
            BufferedImage buffer = ImageIO.read(this.archivoImagen);
            //Crea un stream de salida que escriba un arreglo de bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //Escribe el archivoImagen en el stream de salida
            ImageIO.write(buffer, "jpg", baos);
            baos.flush();
            //Asigna el archivoImagen al objeto por editar
            this.servicioAInsertar.setFoto(baos.toByteArray());
            //Cierra el stream de salida
            baos.close();
        } catch (IOException ex) {
            Logger.getLogger(InsertarSolicitanteAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Servicio getModel() {
        return this.servicioAInsertar;
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
    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public Servicio getServicioAInsertar() {
        return servicioAInsertar;
    }

    public void setServicioAInsertar(Servicio servicioAInsertar) {
        this.servicioAInsertar = servicioAInsertar;
    }

    public LinkedList getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(LinkedList listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }

    public int getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(int idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public File getArchivoImagen() {
        return archivoImagen;
    }

    public void setArchivoImagen(File archivoImagen) {
        this.archivoImagen = archivoImagen;
    }

    public String getImagenFileName() {
        return imagenFileName;
    }

    public void setImagenFileName(String imagenFileName) {
        this.imagenFileName = imagenFileName;
    }

    public String getImagenContentType() {
        return imagenContentType;
    }

    public void setImagenContentType(String imagenContentType) {
        this.imagenContentType = imagenContentType;
    }

}
