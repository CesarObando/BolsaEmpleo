/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Actions;

import Business.ServicioBusiness;
import Dominio.Servicio;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.dispatcher.SessionMap;

/**
 *
 * @author ricardo
 */
public class ServiciosBuscar extends ActionSupport implements Preparable, ModelDriven<Servicio>{
    
    private Servicio servicio;
    private String palabraClave;
    private LinkedList<Servicio> servicios;
    private LinkedList<String> provincias;
    private SessionMap<String, Object> sessionMap;
    
    @Override
    public void prepare() throws Exception {
        servicios = new ServicioBusiness().obtenerServicios();
        servicio = new Servicio();
        cargarProvincias();
    }
    
    @Override
    public String execute() throws Exception {
        return INPUT;
    }
    
    public String buscarServicio(){
        try {
            servicios = new ServicioBusiness().buscarServicio(this.palabraClave, servicio.getProvincia());
        } catch (SQLException ex) {
            Logger.getLogger(ServiciosBuscar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return INPUT;
    }
    
    public void cargarProvincias(){
		provincias = new LinkedList<String>();
		provincias.add("");
		provincias.add("GAM");
		provincias.add("San José");
		provincias.add("Cartago");
                provincias.add("Heredia");
                provincias.add("Alajuela");
                provincias.add("Limón");
                provincias.add("Puntarenas");
                provincias.add("Guanacaste");     
    }

    public LinkedList<String> getProvincias() {
        return provincias;
    }

    public void setProvincias(LinkedList<String> provincias) {
        this.provincias = provincias;
    }

    @Override
    public Servicio getModel() {
        return this.servicio;
    }

    public String getPalabraClave() {
        return palabraClave;
    }

    public void setPalabraClave(String palabraClave) {
        this.palabraClave = palabraClave;
    }

    
    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public LinkedList<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(LinkedList<Servicio> servicios) {
        this.servicios = servicios;
    }

    public SessionMap<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(SessionMap<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }
    
    
    
}
