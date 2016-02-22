/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.CategoriaBusiness;
import Business.ServicioBusiness;
import Dominio.Categoria;
import Dominio.Servicio;
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

/**
 *
 * @author Tin
 */
public class EditarServicioAction extends ActionSupport implements SessionAware, Preparable, ModelDriven<Servicio>, ServletRequestAware {

    private Servicio servicioAEditar;
    private LinkedList<Categoria> categorias;
    private String mensaje;
    private HttpServletRequest request;
    private boolean existe;
    private SessionMap<String, Object> sessionMap;
    private File archivoImagen;
    private String archivoImagenContentType;
    private String archivoImagenFileName;

    public EditarServicioAction() {
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
        categorias = new CategoriaBusiness().getCategorias();
        servicioAEditar = (Servicio) sessionMap.get("servicio");
        int idCategoria = servicioAEditar.getCategoria().getId();
        Categoria categoria = new CategoriaBusiness().buscarCategoria(idCategoria);
        servicioAEditar.setCategoria(categoria);
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
    public void validate() {
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

    public String editar() {
        ServicioBusiness servicioBusiness = new ServicioBusiness();
        boolean editado = true;
        try {
            if (this.archivoImagen != null) {
                cargarImagen();
            }
            servicioBusiness.editarServicio(servicioAEditar);
        } catch (SQLException e) {
            editado = false;
            mensaje = "Ocurrió un error con la base de datos. Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
            sessionMap.put("mensaje", mensaje);
            addActionError(mensaje);
        }
        if (editado == true) {
            this.mensaje = "El servicio fue editado correctamente";
            sessionMap.put("mensaje", mensaje);
            addActionMessage(mensaje);
            return SUCCESS;
        } else {
            return ERROR;
        }
    }
    
    private void cargarImagen() {
        try {
            // Generamos un buffer en memoria que va a almacenar nuestra archivoImagen
            BufferedImage buffer = ImageIO.read(this.archivoImagen);
            // Creamos un stream de salida, que escriba un arreglo de bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // Nuestro objeto de utileria escribira la archivoImagen en el stream de salida
            ImageIO.write(buffer, "jpg", baos);
            baos.flush();
            // A nuestra instancia de Producto2 le asignamos la archivoImagen
            this.servicioAEditar.setFoto(baos.toByteArray());
            baos.close();
        } catch (IOException ex) {
            Logger.getLogger(InsertarSolicitanteAction.class.getName()).log(Level.SEVERE, null, ex);
        }
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
