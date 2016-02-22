/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.SolicitanteBusiness;
import Dominio.Solicitante;
import Exception.DataException;
import static com.opensymphony.xwork2.Action.ERROR;
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
 * @author Cesar
 */
public class EditarSolicitanteAction extends ActionSupport implements Preparable, ModelDriven<Solicitante>, ServletRequestAware, SessionAware {

    private Solicitante solicitanteAEditar;
    private String mensaje;
    private HttpServletRequest request;
    private File archivoImagen;
    private String archivoImagenContentType;
    private String archivoImagenFileName;
    private boolean existe;
    public SessionMap<String, Object> sessionMap;

    public EditarSolicitanteAction() {
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
        solicitanteAEditar = new Solicitante();
        solicitanteAEditar = (Solicitante) sessionMap.get("solicitante");
    }

    @Override
    public Solicitante getModel() {
        return this.solicitanteAEditar;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    @Override
    public void validate() {
        if (solicitanteAEditar.getNombre().length() == 0) {
            addFieldError("nombre", "Debe ingresar su nombre.");
        }
        if (solicitanteAEditar.getApellidos().length() == 0) {
            addFieldError("apellidos", "Debe ingresar sus apellidos.");
        }
        if (solicitanteAEditar.getUsername().length() == 0) {
            addFieldError("username", "Debe ingresar un nombre de usuario.");
        }
        if (solicitanteAEditar.getPassword().length() < 6) {
            addFieldError("password", "La contraseña debe ser mayor a 6 caracteres");
        }
        if (solicitanteAEditar.getEscolaridad().equals("-1")) {
            addFieldError("escolaridad", "Debe seleccionar una escolaridad");
        }
        if (solicitanteAEditar.getSexo().equals("-1")) {
            addFieldError("sexo", "Debe seleccionar un género");
        }
        if (solicitanteAEditar.getEdad() <= 0) {
            addFieldError("edad", "Debe ingresar una edad válida");
        }
        if (solicitanteAEditar.getExperienciaLaboral() < 0) {
            addFieldError("experienciaLaboral", "Debe ingresar un número válido");
        }
    }

    public String editar() throws DataException {
        SolicitanteBusiness solicitanteBusiness = new SolicitanteBusiness();
        boolean editado = true;
        try {
            LinkedList<Solicitante> solicitantes = new SolicitanteBusiness().buscarSolicitantes();
            int i = 0;
            while (existe == false && i < solicitantes.size()) {
                Solicitante solicitante = solicitantes.get(i);
                if (solicitanteAEditar.getCorreo().equals(solicitante.getCorreo()) && solicitanteAEditar.getId() != solicitante.getId()) {
                    existe = true;
                }
                i++;
            }
            if (existe == false) {
                if (this.archivoImagen != null) {
                    cargarImagen();
                }
                solicitanteBusiness.editarSolicitante(solicitanteAEditar);
                sessionMap.put("solicitante", this.solicitanteAEditar);
            } else {
                editado = false;
                mensaje = "El correo ya se encuentran registrado. Inténtelo nuevamente.";
                sessionMap.put("mensaje", mensaje);
                addActionError(mensaje);
                return ERROR;
            }
        } catch (SQLException e) {
            editado = false;
            mensaje = "Ocurrió un error con la base de datos.Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
            sessionMap.put("mensaje", mensaje);
            addActionError(mensaje);
        }
        if (editado == true) {
            this.mensaje = "El solicitante fue editado correctamente";
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
            this.solicitanteAEditar.setFoto(baos.toByteArray());
            baos.close();
        } catch (IOException ex) {
            Logger.getLogger(InsertarSolicitanteAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Solicitante getSolicitanteAEditar() {
        return solicitanteAEditar;
    }

    public void setSolicitanteAEditar(Solicitante solicitanteAEditar) {
        this.solicitanteAEditar = solicitanteAEditar;
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
