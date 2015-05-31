/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Actions;

import Business.AdministradorBusiness;
import Business.EmpleadorBusiness;
import Business.OferenteBusiness;
import Business.UsuarioBusiness;
import Dominio.Empleador;
import Dominio.Oferente;
import Dominio.Usuario;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ricardo
 */
public class ModificarUsuarioVariosAction extends ActionSupport implements ModelDriven, Preparable, ServletRequestAware, SessionAware{
    
    private SessionMap<String, Object> sessionMap;
    private HttpServletRequest request;
    private Usuario usuario;
    private boolean dialogo;
    private Oferente oferente;
    
    
    @Override
    public void prepare() throws Exception {
        
        this.usuario = (Usuario) this.sessionMap.get("usuario");
        oferente = new Oferente();
    }
    
    @Override
    public String execute() throws Exception {
        if (this.sessionMap.get("usuario") == null){
            return LOGIN;
        }
        dialogo = false;
        return INPUT;
    }
    
    public String eliminarOferente(){
        if (this.sessionMap.get("usuario") == null){
            return LOGIN;
        }
        if(!masDeUnTipoUsuario()){
            this.addActionMessage("Si elimina dicha cuenta se va a borrar todos sus datos de esta aplicación");
            dialogo = true;
            return ERROR;
        }
        try {
            new OferenteBusiness().borrarPostulante(usuario.getCedula());
            this.sessionMap.put("oferente", null);
            usuario.setOferente(false);
        } catch (SQLException ex) {
            this.addActionMessage("Ha sucedido un error en la base de datos, por favor inténtelo más tarde o comuníquese con nuestros administradores.");
            return ERROR;
        }
        return SUCCESS;
    }
    
    public String agregarOferente(){
        if (this.sessionMap.get("usuario") == null){
            return LOGIN;
        }
        try {
            usuario.setOferente(true);
            Oferente oferente = new Oferente();
            oferente.setCedula(usuario.getCedula());
            new OferenteBusiness().agregarOferente(oferente);
            new OferenteBusiness().cambiarUsuarioOferente(usuario);
            this.sessionMap.put("oferente", oferente);
        } catch (Exception ex) {
            this.addActionMessage("Ha sucedido un error en la base de datos, por favor inténtelo más tarde o comuníquese con nuestros administradores.");
            return ERROR;
        } 
    
        return SUCCESS;
    }
    
    public String eliminarEmpleador(){
        if (this.sessionMap.get("usuario") == null){
            return LOGIN;
        }
        if(!masDeUnTipoUsuario()){
            this.addActionMessage("Si elimina dicha cuenta se va a borrar todos sus datos de esta aplicación");
            dialogo = true;
            return ERROR;
        }
        try {
            new EmpleadorBusiness().borrarEmpleador(usuario.getCedula());
            usuario.setEmpleador(false);
            this.sessionMap.put("empleador", null);
        } catch (SQLException ex) {
            this.addActionMessage("Ha sucedido un error en la base de datos, por favor inténtelo más tarde o comuníquese con nuestros administradores.");
            return ERROR;
        }
        return SUCCESS;
    }
    
    public String agregarEmpleador(){
        if (this.sessionMap.get("usuario") == null){
            return LOGIN;
        }
        try {
            usuario.setEmpleador(true);
            Empleador empleador = new Empleador();
            empleador.setCedula(usuario.getCedula());
            new EmpleadorBusiness().agregarEmpleador(empleador);
            new EmpleadorBusiness().cambiarUsuarioEmpleador(usuario);
            this.sessionMap.put("empleador", empleador);
        } catch (Exception ex) {
            this.addActionMessage("Ha sucedido un error en la base de datos, por favor inténtelo más tarde o comuníquese con nuestros administradores.");
            return ERROR;
        } 
    
        return SUCCESS;
    }
    
    public String eliminarAdministrador(){
        if (this.sessionMap.get("usuario") == null){
            return LOGIN;
        }
        if(!masDeUnTipoUsuario()){
            this.addActionMessage("Si elimina dicha cuenta se va a borrar todos sus datos de esta aplicación");
            dialogo = true;
            return ERROR;
        }
        try {
            new AdministradorBusiness().cambiarUsuarioAdministrador(usuario, false);
            usuario.setAdministrador(false);
        } catch (SQLException ex) {
            this.addActionMessage("Ha sucedido un error en la base de datos, por favor inténtelo más tarde o comuníquese con nuestros administradores.");
            return ERROR;
        }
        return SUCCESS;
    }
    
    public String agregarAdministrador(){
        if (this.sessionMap.get("usuario") == null){
            return LOGIN;
        }
        try {
            new AdministradorBusiness().cambiarUsuarioAdministrador(usuario, true);
        } catch (SQLException ex) {
            if(ex.getErrorCode() == 1062){
              this.addActionMessage("Ya has hecho esta solicitud");
            }
            else{
                this.addActionMessage("Ha sucedido un error en la base de datos, por favor inténtelo más tarde o comuníquese con nuestros administradores.");
            }
            return ERROR;
        } 
    
        return SUCCESS;
    }
    
    public String eliminarUsuario(){
        if (this.sessionMap.get("usuario") == null){
            return LOGIN;
        }
        try {
            new UsuarioBusiness().eliminarUsuario(usuario);
        } catch (SQLException ex) {
            this.addActionMessage("Ha sucedido un error en la base de datos, por favor inténtelo más tarde o comuníquese con nuestros administradores.");
            return ERROR;
        }
        return SUCCESS;
    }
    
    public String actualziarCurriculo(){
        if (this.sessionMap.get("usuario") == null){
            return LOGIN;
        }
        
        if(oferente.getArchivoCurriculo() == null){
            this.addActionMessage("Ingrese un archivo");
            return ERROR;
        }
        
        try {
            new OferenteBusiness().actualizarCurriculo(usuario, oferente.getArchivoCurriculo() );
            this.addActionMessage("Se ha ingresado el curriculo correctamente");
        } catch (Exception ex) {
            this.addActionMessage("Ha sucedido un error en la base de datos, por favor inténtelo más tarde o comuníquese con nuestros administradores.");
            return ERROR;
        }
        return SUCCESS;
    }
    
    
    private boolean masDeUnTipoUsuario(){
        if(usuario.isEmpleador() && usuario.isOferente()){
            return true;
        }
        return (usuario.isAdministrador() && (usuario.isEmpleador() || usuario.isOferente()));            
    }
    

    public Usuario getUsuario() {
        return usuario;
    }

    public boolean isDialogo() {
        return dialogo;
    }

    public void setDialogo(boolean dialogo) {
        this.dialogo = dialogo;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Oferente getOferente() {
        return oferente;
    }

    public void setOferente(Oferente oferente) {
        this.oferente = oferente;
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

    @Override
    public Oferente getModel() {
        return oferente;
    }
    
}
