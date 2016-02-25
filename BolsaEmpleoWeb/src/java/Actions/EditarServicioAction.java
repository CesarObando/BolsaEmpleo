package Actions;

import Business.CategoriaBusiness;
import Business.ServicioBusiness;
import Dominio.Categoria;
import Dominio.Servicio;
import Exception.DataException;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
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

public class EditarServicioAction extends ActionSupport implements SessionAware, Preparable, ModelDriven<Servicio>, ServletRequestAware {

    //Variables globales
    private Servicio servicioAEditar;
    private LinkedList<Categoria> categorias;
    private String mensaje;
    private HttpServletRequest request;
    private SessionMap<String, Object> sessionMap;
    private File archivoImagen;
    private String archivoImagenContentType;
    private String archivoImagenFileName;

    public EditarServicioAction() {
    }

    @Override
    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        //Llamado al metodo que realiza la busqueda
        categorias = new CategoriaBusiness().buscarCategorias();
        //Obtiene el objeto en sesion
        servicioAEditar = (Servicio) sessionMap.get("servicio");
        //Obtiene el id del objeto
        int idCategoria = servicioAEditar.getCategoria().getId();
        //Llamado al procedimiento que realiza la busqueda
        Categoria categoria = new CategoriaBusiness().buscarCategoria(idCategoria);
        //Asigna la cateogria al objeto por editar
        servicioAEditar.setCategoria(categoria);
    }

    @Override
    public void validate() {
        //Validaciones de los campos de entrada
        if (servicioAEditar.getTitulo().length() == 0) {
            addFieldError("titulo", "Debe ingresar el título del servicio.");
        }
        if (servicioAEditar.getDescripcion().length() == 0) {
            addFieldError("descripcion", "Debe ingresar una descripción del servicio.");
        }
        if (servicioAEditar.getCategoria().getId() == -1) {
            addFieldError("categoria.id", "Debe seleccionar una categoría.");
        }
        if (servicioAEditar.getProvincia().length() == 0) {
            addFieldError("provincia", "Debe seleccionar una provincia.");
        }
        if (servicioAEditar.getCanton().length() == 0) {
            addFieldError("canton", "Debe seleccionar un canton.");
        }
    }

    public String editar() throws DataException {
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        ServicioBusiness servicioBusiness = new ServicioBusiness();
        //Inicializa las variables
        boolean editado = true;
        try {
            //Valida si se ha seleccionado una nueva imagen
            if (this.archivoImagen != null) {
                cargarImagen();
            }
            //Llamado al metodo que realiza la edicion
            servicioBusiness.editarServicio(servicioAEditar);
        } catch (SQLException e) {
            //Asigna valor a la variable
            editado = false;
            //Define un mensaje que sera mostrado al usuario
            mensaje = "Ocurrió un error con la base de datos. Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
            //Coloca en sesion al mensaje
            sessionMap.put("mensaje", mensaje);
            //Coloca el mensaje como mensaje de error
            addActionError(mensaje);
        }
        if (editado == true) {
            //Define un mensaje que sera mostrado al usuario
            this.mensaje = "El servicio fue editado correctamente";
            //Coloca en sesion al mensaje
            sessionMap.put("mensaje", mensaje);
            //Coloca el mensaje como mensaje del action
            addActionMessage(mensaje);
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    private void cargarImagen() {
        try {
            //Genera un buffer en memoria que va a almacenar nuestro archivoImagen
            BufferedImage buffer = ImageIO.read(this.archivoImagen);
            //Crea un stream de salida que escriba un arreglo de bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //Escribe el archivoImagen en el stream de salida
            ImageIO.write(buffer, "jpg", baos);
            baos.flush();
            //Asigna el archivoImagen al objeto por editar
            this.servicioAEditar.setFoto(baos.toByteArray());
            //Cierra el stream de salida
            baos.close();
        } catch (IOException ex) {
            Logger.getLogger(InsertarSolicitanteAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Servicio getModel() {
        return this.servicioAEditar;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

    public Servicio getServicioAEditar() {
        return servicioAEditar;
    }

    public void setServicioAEditar(Servicio servicioAEditar) {
        this.servicioAEditar = servicioAEditar;
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

    public LinkedList<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(LinkedList<Categoria> categorias) {
        this.categorias = categorias;
    }

    public File getArchivoImagen() {
        return archivoImagen;
    }

    public void setArchivoImagen(File archivoImagen) {
        this.archivoImagen = archivoImagen;
    }

    public String getArchivoImagenContentType() {
        return archivoImagenContentType;
    }

    public void setArchivoImagenContentType(String archivoImagenContentType) {
        this.archivoImagenContentType = archivoImagenContentType;
    }

    public String getArchivoImagenFileName() {
        return archivoImagenFileName;
    }

    public void setArchivoImagenFileName(String archivoImagenFileName) {
        this.archivoImagenFileName = archivoImagenFileName;
    }

}
