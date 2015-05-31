/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Actions;

import Business.UsuarioBusiness;
import Dominio.Empleador;
import Dominio.Oferente;
import Dominio.Usuario;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 *
 * @author ricardo
 */
public class RegistrarUsuarioAction extends ActionSupport implements Preparable, ModelDriven, ServletRequestAware, SessionAware{
    
    private Usuario usuarioNuevo;
    private LinkedList<String> provincias;
    private LinkedList<String> radio;
    private SessionMap<String, Object> sessionMap;
    private HttpServletRequest request;
    private String repitaClave;
    private String actualClave;
    private static boolean actualizando;
    
    @Override
    public void prepare() throws Exception {
        actualizando = false;
        cargarProvincias();
        cargarRadio();
        if (sessionMap.get("usuario") != null){
             this.usuarioNuevo = (Usuario) this.sessionMap.get("usuario");
             actualizando = true;
        }
        else{
            this.usuarioNuevo = new Oferente();
        }
    }
   
    @Override
    public String execute() throws Exception {
            return INPUT;
    }
    
    //para admi hacer una tabla que indique cédula. Y un admi con eso le ponga sí o no
    public String insertarUsuario(){
        try {
            new UsuarioBusiness().ingresarUsuario(usuarioNuevo);
        } catch (SQLException | FileNotFoundException ex) {
            Logger.getLogger(RegistrarUsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            this.addActionMessage("Ha ocurrido un error en la base de datos, por favor espere. O si el error persiste comuníquese con nosotros. Gracias");
            return ERROR;
        }
        
        String tipoUsuario = request.getParameter("radioUsuario");
        this.sessionMap.put("usuario", usuarioNuevo);
        if (tipoUsuario.equals("Vacante")){
            this.usuarioNuevo.setOferente(true);
            Oferente oferente =new Oferente();
            oferente.setCedula(usuarioNuevo.getCedula());
            this.sessionMap.put("oferente", oferente);
        }
        if (tipoUsuario.equals("Empleador")){
            Empleador empleador = new Empleador();
            empleador.setCedula(usuarioNuevo.getCedula());
            this.sessionMap.put("empleador", empleador);
            this.usuarioNuevo.setEmpleador(true);
        }
        if (tipoUsuario.equals("Administrador")){
            this.usuarioNuevo.setAdministrador(true);
        }
        
        this.addActionMessage("Te has registrado correctamente.\n"
                    + "Haz clic en las opciones del menú de arriba para agregar la información sobre tu cuenta");
        
        return SUCCESS;
    }
    

    public String actualizarUsuario(){
        try {
            new UsuarioBusiness().actualizarUsuario(usuarioNuevo);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarUsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            this.addActionMessage("Ha ocurrido un error en la base de datos, por favor espere. O si el error persiste comuníquese con nosotros. Gracias");
            return ERROR;
        }
        this.addActionMessage("Se ha actualizado correctamente");
        return SUCCESS;
    }
    
    @SkipValidation
    public String cambiarContrasena(){
        boolean error = false;

        validateClave(error);
        if (this.request.getParameter("actualClave").trim().equals("") ){
            addFieldError("actualClave", "Requiere su contraseña actual");
            error = true;
        }
        else{
            Usuario u = new Usuario();
            u.setNombreUsuario(usuarioNuevo.getNombreUsuario());
            u.setClave(this.request.getParameter("actualClave").trim());
            try {
                if (!new UsuarioBusiness().iniciarSesion(u)) {
                    addFieldError("actualClave", "Contraseña incorrecta");
                    error = true;
                }
            } catch (SQLException ex) {
                this.addActionMessage("Ha ocurrido un error en la base de datos, por favor espere. O si el error persiste comuníquese con nosotros. Gracias");
                return ERROR;
            }
        }
        
        if (error)
            return ERROR;
        try {
            new UsuarioBusiness().actualizarContrasena(usuarioNuevo);
        } catch (SQLException ex) {
            this.addActionMessage("Ha ocurrido un error en la base de datos, por favor espere. O si el error persiste comuníquese con nosotros. Gracias");
            return ERROR;
        }
        
        this.addActionMessage("Se ha cambiado correctamente su contraseña.\nGracias.");
        return SUCCESS;
    }
    
    public void cargarProvincias(){
		provincias = new LinkedList<>();
		provincias.add("San José");
		provincias.add("Cartago");
                provincias.add("Heredia");
                provincias.add("Alajuela");
                provincias.add("Limón");
                provincias.add("Puntarenas");
                provincias.add("Guanacaste");     
    }
    
    public void cargarRadio(){
		radio = new LinkedList<>();
		radio.add("Vacante");
		radio.add("Empleador");
                radio.add("Administrador");  
    }

    public void validateClave(boolean error){
        if (this.request.getParameter("clave").trim().equals("")){
            addFieldError("clave", "Contraseña requerida");
             error = true;
        }else if(this.request.getParameter("clave").length()<8){
            addFieldError("clave", "Mínimo de caracteres de contraseña: 8");
             error = true;
        }
        if (this.request.getParameter("repitaClave").trim().equals("") ){
            addFieldError("repitaClave", "Repita la contraseña");
             error = true;
        } 
        else
            if (!this.request.getParameter("clave").trim().equals(this.request.getParameter("repitaClave").trim())){
                addFieldError("repitaClave", "Contraseñas no coinciden");
                 error = true;
            }
    }
    
    @Override
    public void validate() {
        if(!actualizando){
            validateClave(true);
        }
        if (this.request.getParameter("cedula").trim().equals("")) {
            addFieldError("cedula", "Cédula requerida");
        }
        if (this.request.getParameter("nombre").trim().equals("")) {
            addFieldError("nombre", "Nombre requerido");
        }
        if (getUsuarioNuevo().getApellidos().trim().equals("")){
            addFieldError("apellidos", "Apellidos requeridos");
        }
        if (this.request.getParameter("nombreUsuario").trim().equals("") ){
            addFieldError("nombreUsuario", "Nombre usuario requerido");
        }
        if (this.request.getParameter("email").trim().equals("") ){
            addFieldError("email", "Email requerido");
        }
        if (this.request.getParameter("telefonoCasa").trim().equals("") && this.request.getParameter("telefonoCelular").trim().equals("")){
            addFieldError("telefonoCasa", "Es necesario al menos un teléfono");
        }
        if(!actualizando){
            if (request.getParameter("radioUsuario").equals("Vacante")){
                Oferente oferente = (Oferente) usuarioNuevo;
                if(oferente.getArchivoCurriculo() == null){
                    addFieldError("archivoCurriculo", "Se necesita currículo");  
                }
            }
        }
    }
    
    @Override
    public Usuario getModel() {
        return this.usuarioNuevo;
    }

    public Usuario getUsuarioNuevo() {
        return usuarioNuevo;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public LinkedList<String> getRadio() {
        return radio;
    }

    public String getActualClave() {
        return actualClave;
    }

    public void setActualClave(String actualClave) {
        this.actualClave = actualClave;
    }
    
    public void setRadio(LinkedList<String> radio) {
        this.radio = radio;
    }
    
    public String getDefaultRadio(){
		return "Vacante";
    }

    public String getRepitaClave() {
        return repitaClave;
    }

    public void setRepitaClave(String repitaClave) {
        this.repitaClave = repitaClave;
    }
    
    public void setUsuarioNuevo(Usuario usuarioNuevo) {
        this.usuarioNuevo = usuarioNuevo;
    }

    public LinkedList<String> getProvincias() {
        return provincias;
    }

    public void setProvincias(LinkedList<String> provincias) {
        this.provincias = provincias;
    }

    public SessionMap<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(SessionMap<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }
    
    


    
}
