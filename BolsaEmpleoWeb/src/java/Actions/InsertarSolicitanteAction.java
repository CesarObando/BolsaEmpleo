/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.SolicitanteBusiness;
import Dominio.Solicitante;
import Exception.DataException;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Cesar
 */
public class InsertarSolicitanteAction extends ActionSupport implements Preparable, ModelDriven<Solicitante>, ServletRequestAware {

    private Solicitante solicitanteAInsertar;
    private String mensaje;
    private HttpServletRequest request;
    private File archivoImagen;
    private String imagenFileName;
    private String imagenContentType;

    public InsertarSolicitanteAction() {

    }

    @Override
    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        solicitanteAInsertar = new Solicitante();
        mensaje = "";
    }

    @Override
    public Solicitante getModel() {
        return this.solicitanteAInsertar;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    @Override
    public void validate() {
        if (solicitanteAInsertar.getCedula().length() != 9 || solicitanteAInsertar.getCedula().equals(null)) {
            addFieldError("cedula", "Debe ingresar un número de identificación válido. Formato de 9 dígitos. Ej.: 000000000");
        }
        if (solicitanteAInsertar.getNombre().length() == 0 || solicitanteAInsertar.getNombre().equals(null)) {
            addFieldError("nombre", "Debe ingresar su nombre.");
        }
        if (solicitanteAInsertar.getApellidos().length() == 0 || solicitanteAInsertar.getApellidos().equals(null)) {
            addFieldError("apellidos", "Debe ingresar sus apellidos.");
        }
        if (solicitanteAInsertar.getUsername().length() == 0 || solicitanteAInsertar.getUsername().equals(null)) {
            addFieldError("username", "Debe ingresar un nombre de usuario.");
        }
        if (solicitanteAInsertar.getPassword().length() == 0 || solicitanteAInsertar.getPassword().equals(null)) {
            addFieldError("password", "Debe ingresar una contraseña.");
        }
    }

    public String insertar() {
        SolicitanteBusiness solicitanteBusiness = new SolicitanteBusiness();
        boolean insertado = true;
        try {
            cargarImagen();
            solicitanteBusiness.insertarSolicitante(solicitanteAInsertar);
        } catch (SQLException | DataException e) {
            insertado = false;
            mensaje = "Ocurrió un error con la base de datos. Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
        }
        if (insertado == true) {
            this.mensaje = "El solicitante fue insertado correctamente";
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public void cargarImagen() {
        try {
            // Generamos un buffer en memoria que va a almacenar nuestra archivoImagen
            BufferedImage buffer = ImageIO.read(this.archivoImagen);
            // Creamos un stream de salida, que escriba un arreglo de bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // Nuestro objeto de utileria escribira la archivoImagen en el stream de salida
            ImageIO.write(buffer, "jpg", baos);
            baos.flush();
            this.solicitanteAInsertar.setFoto(baos.toByteArray());
            baos.close();
        } catch (IOException ex) {
            Logger.getLogger(InsertarSolicitanteAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Solicitante getSolicitanteAInsertar() {
        return solicitanteAInsertar;
    }

    public void setSolicitanteAInsertar(Solicitante solicitanteAInsertar) {
        this.solicitanteAInsertar = solicitanteAInsertar;
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
