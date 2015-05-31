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
import java.sql.SQLException;
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
public class IniciarSesionAction extends ActionSupport implements Preparable, ModelDriven<Usuario>, SessionAware, ServletRequestAware {

    private HttpServletRequest request;
    private SessionMap<String, Object> sessionMap;
    private Usuario usuario;

    @Override
    public void prepare() throws Exception {
        usuario = new Usuario();
    }

    @Override
    public String execute() throws Exception {
        return INPUT;
    }

    /**
     * prueba de victor
     *
     * @return
     */
    @SkipValidation
    public String logout() {
        sessionMap.clear();
        return "success";
    }

    public String iniciarSesionUsuario() {

        sessionMap.clear();
        UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
        try {
            if (!usuarioBusiness.iniciarSesion(usuario)) {
                this.addActionMessage("Usuario o contraseña incorrectas");
                return ERROR;
            }
            sessionMap.put("usuario", usuario);

            //pongo los usuarios en sesion; mantengo al usuario master para poder desplegar a todos los demás
            //y los ingreso a sesión si sí cuenta con ese rol.
            if (usuario.isOferente()) {
                Oferente oferente = new Oferente();
                oferente.setCedula(usuario.getCedula());
                sessionMap.put("oferente", oferente);
            }

            if (usuario.isEmpleador()) {
                Empleador empleador = new Empleador();
                empleador.setCedula(usuario.getCedula());
                sessionMap.put("empleador", empleador);
            }

            if (usuario.isOferente()) {
                return "oferente";
            }

            if (usuario.isEmpleador()) {
                return "empleador";
            }

            if (usuario.isAdministrador()) {
                return "administrador";
            }

        } catch (SQLException ex) {
            Logger.getLogger(IniciarSesionAction.class.getName()).log(Level.SEVERE, null, ex);
            this.addActionMessage("Ha ocurrido un error en la base de datos, por favor espere. O si el error persiste comuníquese con nosotros.\nGracias");
            return ERROR;
        }
        return ERROR;
    }

    @Override
    public void validate() {
        if (this.request.getParameter("nombreUsuario").trim().equals("")) {
            addFieldError("nombreUsuario", "Ingrese un nombre de usuario o correo");
        }
        if (this.request.getParameter("clave").trim().equals("")) {
            addFieldError("clave", "Ingrese una contraseña");
        }
    }
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Usuario getModel() {
        return this.usuario;
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
