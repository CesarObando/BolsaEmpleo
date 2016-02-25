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

public class EditarSolicitanteAction extends ActionSupport implements Preparable, ModelDriven<Solicitante>, ServletRequestAware, SessionAware {

    //Variables globales
    private Solicitante solicitanteAEditar;
    private String mensaje;
    private boolean existe;
    private HttpServletRequest request;
    private File archivoImagen;
    private String archivoImagenContentType;
    private String archivoImagenFileName;
    public SessionMap<String, Object> sessionMap;

    public EditarSolicitanteAction() {
    }

    @Override
    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        //Inicializa el objeto
        solicitanteAEditar = new Solicitante();
        //Obtiene el objeto en sesion
        solicitanteAEditar = (Solicitante) sessionMap.get("solicitante");
    }

    @Override
    public void validate() {
        //Validaciones de los campos de entrada
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
        //Definicion de un objeto de la capa Business para comunicarse con los metodos de la capa Data
        SolicitanteBusiness solicitanteBusiness = new SolicitanteBusiness();
        //Inicializa las variables
        boolean editado = true;
        try {
            //Define una lista de objetos
            LinkedList<Solicitante> solicitantes = new SolicitanteBusiness().buscarSolicitantes();
            int i = 0;
            //Recorre la lista
            while (existe == false && i < solicitantes.size()) {
                Solicitante solicitante = solicitantes.get(i);
                //Valida que el correo que se esta editando no exista
                if (solicitanteAEditar.getCorreo().equals(solicitante.getCorreo()) && solicitanteAEditar.getId() != solicitante.getId()) {
                    existe = true;
                }
                i++;
            }
            //Si no existe el correo
            if (existe == false) {
                //Valida si se ha seleccionado una nueva imagen
                if (this.archivoImagen != null) {
                    cargarImagen();
                }
                //Llamado al metodo que realiza la edicion
                solicitanteBusiness.editarSolicitante(solicitanteAEditar);
                //Coloca en sesion al objeto
                sessionMap.put("solicitante", this.solicitanteAEditar);
            } else {
                //Asigna valor a la variable
                editado = false;
                //Define un mensaje que sera mostrado al usuario
                mensaje = "El correo ya se encuentran registrado. Inténtelo nuevamente.";
                //Coloca en sesion al mensaje
                sessionMap.put("mensaje", mensaje);
                //Coloca el mensaje como mensaje de error
                addActionError(mensaje);
                return ERROR;
            }
        } catch (SQLException e) {
            //Asigna valor a la variable
            editado = false;
            //Define un mensaje que sera mostrado al usuario
            mensaje = "Ocurrió un error con la base de datos.Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
            //Coloca en sesion al mensaje
            sessionMap.put("mensaje", mensaje);
            //Coloca el mensaje como mensaje de error
            addActionError(mensaje);
        }
        if (editado == true) {
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
            this.solicitanteAEditar.setFoto(baos.toByteArray());
            //Cierra el stream de salida
            baos.close();
        } catch (IOException ex) {
            Logger.getLogger(InsertarSolicitanteAction.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }
    
    //Setter-Getter
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

}
