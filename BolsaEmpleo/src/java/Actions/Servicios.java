/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Actions;

import Business.ServicioBusiness;
import Dominio.Servicio;
import Dominio.Usuario;
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
public class Servicios extends ActionSupport implements Preparable, ModelDriven<Servicio>, ServletRequestAware, SessionAware{
    
    private Usuario usuario;
    private Servicio servicio;
    private LinkedList<Servicio> serviciosUsuario;
    private LinkedList<String> provincias;
    private SessionMap<String, Object> sessionMap;
    private HttpServletRequest request;
    
    @Override
    public void prepare() throws Exception {
        usuario = (Usuario) sessionMap.get("usuario");
        serviciosUsuario = new ServicioBusiness().obtenerServicios(usuario.getCedula());
        servicio = new Servicio();
        cargarProvincias();
    }
    
    @Override
    public String execute() throws Exception {
        if (this.sessionMap.get("usuario") != null) { // Hay una sesion de empleado abierta
             servicio = new ServicioBusiness().obtenerServicio(servicio.getIdServicio());
             boolean mio = false;
             for(Servicio servicioActual : serviciosUsuario){
                if(servicio.equals(servicioActual)){
                    mio = true;
                }
             }
             if(!mio){
                 servicio = new Servicio();
             }
            return INPUT;
        } else {
            return LOGIN;
        }
    }
    
    public void cargarProvincias(){
		provincias = new LinkedList<String>();
		provincias.add("Ninguna en específica");
		provincias.add("GAM");
		provincias.add("San José");
		provincias.add("Cartago");
                provincias.add("Heredia");
                provincias.add("Alajuela");
                provincias.add("Limón");
                provincias.add("Puntarenas");
                provincias.add("Guanacaste");     
    }
    
    public String insertarServicio(){
        ServicioBusiness servicioBusiness = new ServicioBusiness();
        try{
            servicioBusiness.ingresarServicio(this.servicio, usuario.getCedula());
            this.addActionMessage("Se ha ingresado correctamente el servicio "+servicio.getNombre());
            serviciosUsuario = new ServicioBusiness().obtenerServicios(usuario.getCedula());
        }catch(SQLException ex){
            this.addActionMessage("Ha ocurrido un error en la base de datos, por favor espere. O si el error persiste comuníquese con nosotros.\nGracias");
            return ERROR;
        }
        return INPUT;
    }
    
    public String eliminarServicio(){
        ServicioBusiness servicioBusiness = new ServicioBusiness();
        try{
            servicioBusiness.eliminarServicio(this.servicio.getIdServicio());
            this.addActionMessage("Se ha eliminado correctamente el servicio "+servicio.getNombre());
            serviciosUsuario = new ServicioBusiness().obtenerServicios(usuario.getCedula());
        }catch(SQLException ex){
            this.addActionMessage("Ha ocurrido un error en la base de datos, por favor espere. O si el error persiste comuníquese con nosotros.\nGracias");
            return ERROR;
        }
        return INPUT;
    }
    
    public String actualizarServicio(){
        ServicioBusiness servicioBusiness = new ServicioBusiness();
        try{
            servicioBusiness.actualizarServicio(this.servicio);
            this.addActionMessage("Se ha actualizado correctamente el servicio "+servicio.getNombre());
        }catch(SQLException ex){
            this.addActionMessage("Ha ocurrido un error en la base de datos, por favor espere. O si el error persiste comuníquese con nosotros.\nGracias");
            return ERROR;
        }
        return INPUT;
    }
    
    @Override
    public void validate(){
        if (this.request.getParameter("servicio.nombre").trim().equals("")) {
            addFieldError("servicio.nombre", "Ingrese un nombre");
        }
        if (this.request.getParameter("servicio.descripcion").trim().equals("")) {
            addFieldError("servicio.descripcion", "Ingrese descripción");
        }
        if (this.request.getParameter("servicio.telefono").trim().equals("")) {
            addFieldError("servicio.telefono", "Se requiere teléfono");
        }
        if (this.request.getParameter("servicio.provincia").trim().equals("-1")) {
            addFieldError("servicio.provincia", "Seleccione provincia");
        }
    }
    


    public Servicio getServicio() {
        return servicio;
    }

    public LinkedList<String> getProvincias() {
        return provincias;
    }

    public void setProvincias(LinkedList<String> provincias) {
        this.provincias = provincias;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public LinkedList<Servicio> getServiciosUsuario() {
        return serviciosUsuario;
    }

    public void setServiciosUsuario(LinkedList<Servicio> serviciosUsuario) {
        this.serviciosUsuario = serviciosUsuario;
    }

    public SessionMap<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(SessionMap<String, Object> sessionMap) {
        
    }

    @Override
    public Servicio getModel() {
       return this.servicio;
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
