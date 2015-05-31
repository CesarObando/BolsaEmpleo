/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Actions;

import Business.ContactoEmpleadorBusiness;
import Dominio.ContactoEmpleador;
import Dominio.Usuario;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ricardo
 */
public class EmpresaAction extends ActionSupport implements Preparable, ModelDriven, ServletRequestAware, SessionAware {
   
    private SessionMap<String, Object> sessionMap;
    private HttpServletRequest request;
    private Usuario usuario;
    private boolean dialogo;
    private ContactoEmpleador contactoEmpleador;
    private LinkedList<ContactoEmpleador> contactosEmpleadores;
    private String cedulJuridica;

    /**
     *
     * @throws Exception
     */
    @Override
    public void prepare() throws Exception {
        this.usuario = (Usuario) this.sessionMap.get("usuario");
        contactosEmpleadores = new LinkedList<>();
        contactosEmpleadores = new ContactoEmpleadorBusiness().getContactosEmpleadores();
    }

    @Override
    public String execute() throws Exception {
        //        if (this.sessionMap.get("usuario") == null){
//            return LOGIN;
//        }
        dialogo = false;
        return INPUT;
    }

    public String actualizarCamposEmpresa() throws Exception {
//        if (this.sessionMap.get("usuario") == null){
//            return LOGIN;
//        }
        contactoEmpleador = new ContactoEmpleador();
        contactoEmpleador = new ContactoEmpleadorBusiness().getContactoEmpleador(cedulJuridica);
        return SUCCESS;
    }
    
    public String modificarEmpresa() throws Exception {
        //        if (this.sessionMap.get("usuario") == null){
//            return LOGIN;
//        }
        ContactoEmpleadorBusiness cEmpBus=new ContactoEmpleadorBusiness();
        cEmpBus.modificarEmpresa(contactoEmpleador);
        contactosEmpleadores = new ContactoEmpleadorBusiness().getContactosEmpleadores();
        return SUCCESS;
    }

    /**
     *
     */
    @Override
    public void validate() {
        if(this.contactoEmpleador.getCedulaJuridica().trim().isEmpty()){
            addFieldError("contactoEmpleador.cedulaJuridica", "Por favor ingrese la cedula juridica");
        }
        if(this.contactoEmpleador.getEmpresa().trim().isEmpty()){
            addFieldError("contactoEmpleador.empresa", "Por favor ingrese la empresa ");
        }
        if(this.contactoEmpleador.getDesingnacion().trim().isEmpty()){
            addFieldError("contactoEmpleador.desingnacion", "Por favor ingrese la designacion");
        }
        if(this.contactoEmpleador.getTelefono().trim().isEmpty()){
            addFieldError("contactoEmpleador.telefono", "Por favor ingrese un telefono");
        }
        if(this.contactoEmpleador.getExtension()<=0){
            addFieldError("contactoEmpleador.extension", "Por favor ingrese una extension correcta");
        }
        if(this.contactoEmpleador.getFax().trim().isEmpty()){
            addFieldError("contactoEmpleador.fax", "Por favor ingrese un numero de fax");
        }
        if(this.contactoEmpleador.getEmail().trim().isEmpty()){
            addFieldError("contactoEmpleador.email", "Por favor ingrese el email de la empresa");
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public LinkedList<ContactoEmpleador> getContactosEmpleadores() {
        return contactosEmpleadores;
    }

    public void setContactosEmpleadores(LinkedList<ContactoEmpleador> contactosEmpleadores) {
        this.contactosEmpleadores = contactosEmpleadores;
    }

    public ContactoEmpleador getContactoEmpleador() {
        return contactoEmpleador;
    }

    public void setContactoEmpleador(ContactoEmpleador contactoEmpleador) {
        this.contactoEmpleador = contactoEmpleador;
    }

    public String getCedulJuridica() {
        return cedulJuridica;
    }

    public void setCedulJuridica(String cedulJuridica) {
        this.cedulJuridica = cedulJuridica;
    }

    public boolean isDialogo() {
        return dialogo;
    }

    public void setDialogo(boolean dialogo) {
        this.dialogo = dialogo;
    }
    
    @Override
    public Object getModel() {
        return contactoEmpleador;
    }

    public String borrarEmpresa(){
        try {
                ContactoEmpleadorBusiness contactEmpBus=new ContactoEmpleadorBusiness();
                contactEmpBus.borrarContactoEmpleador(cedulJuridica);
                return SUCCESS;
            } catch (SQLException ex) {
                return ERROR;
            }
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

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

}

