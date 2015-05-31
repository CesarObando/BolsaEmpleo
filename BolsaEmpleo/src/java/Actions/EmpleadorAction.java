/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.EmpleadorBusiness;
import Dominio.Empleador;
import Dominio.Usuario;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class EmpleadorAction extends ActionSupport implements Preparable, ModelDriven, ServletRequestAware, SessionAware {

    private SessionMap<String, Object> sessionMap;
    private HttpServletRequest request;
    private Usuario usuario;
    private boolean dialogo;
    ArrayList<String> provincias;
    ArrayList<String> ciudades;
    private Empleador empleador;
    private String fechaNacimientoString;
    private LinkedList<Empleador> empleadores;
    private String cedul;

    @Override
    public void prepare() throws Exception {
        this.usuario = (Usuario) this.sessionMap.get("usuario");
        empleadores = new LinkedList<>();
        empleadores = new EmpleadorBusiness().getEmpleadores();
        cargarProvincias();
        ciudades = new ArrayList<>();
        ciudades.add("No especificar");
    }

    @Override
    public String execute() throws Exception {
//        if (this.sessionMap.get("usuario") == null){
//            return LOGIN;
//        }
        dialogo = false;
        return INPUT;
    }

    public String modificarEmpleador() throws SQLException, ParseException {
//        if (this.sessionMap.get("usuario") == null){
//            return LOGIN;
//        }
        if (!empleador.getNombre().isEmpty()) {
            try {
                SimpleDateFormat formatFecha = new SimpleDateFormat("dd-MM-yyyy");
                Date fechaCreacion = formatFecha.parse(fechaNacimientoString);
                empleador.setFechaNacimiento(new java.sql.Date(fechaCreacion.getTime()));
                EmpleadorBusiness empBus = new EmpleadorBusiness();
                empBus.actualizarCuentaEmpleador(empleador);
                empleadores = new EmpleadorBusiness().getEmpleadores();
                return SUCCESS;
            } catch (SQLException ex) {
                return SUCCESS;
            }
        }
        return SUCCESS;
    }

    public String actualizarCamposEmpleador() throws Exception {
//        if (this.sessionMap.get("usuario") == null){
//            return LOGIN;
//        }
        empleador = new Empleador();
        if (!cedul.trim().equals("vacia")) {
            empleador = new EmpleadorBusiness().getEmpleador(cedul.trim());
            if (empleador.getFechaNacimiento() != null) {
                SimpleDateFormat fechaActualString = new SimpleDateFormat("dd-MM-yyyy");
                fechaNacimientoString = fechaActualString.format(empleador.getFechaNacimiento());
            }
            if (empleador.getProvincia() != null) {

                cargarCantones(empleador.getProvincia().trim());
            }
        }
        return SUCCESS;
    }

    public String borrarEmpleador() {
//        if (this.sessionMap.get("usuario") == null){
//            return LOGIN;
//        }
        try {
            EmpleadorBusiness empleadorBus = new EmpleadorBusiness();
            empleadorBus.borrarEmpleador(cedul);
            return SUCCESS;
        } catch (SQLException ex) {
            return ERROR;
        }
    }

    public LinkedList<Empleador> getEmpleadores() {
        return empleadores;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isDialogo() {
        return dialogo;
    }

    public void setDialogo(boolean dialogo) {
        this.dialogo = dialogo;
    }

    public void setEmpleadores(LinkedList<Empleador> empleadores) {
        this.empleadores = empleadores;
    }

    public String getCedul() {
        return cedul;
    }

    public void setCedul(String cedul) {
        this.cedul = cedul;
    }

    public ArrayList<String> getProvincias() {
        return provincias;
    }

    public void setProvincias(ArrayList<String> provincias) {
        this.provincias = provincias;
    }

    public ArrayList<String> getCiudades() {
        return ciudades;
    }

    public void setCiudades(ArrayList<String> ciudades) {
        this.ciudades = ciudades;
    }

    public Empleador getEmpleador() {
        return empleador;
    }

    public void setEmpleador(Empleador empleador) {
        this.empleador = empleador;
    }

    public String getFechaNacimientoString() {
        return fechaNacimientoString;
    }

    public void setFechaNacimientoString(String fechaNacimientoString) {
        this.fechaNacimientoString = fechaNacimientoString;
    }

    private void cargarProvincias() {
        provincias = new ArrayList<>();
        provincias.add("No especificar");
        provincias.add("San José");
        provincias.add("Alajuela");
        provincias.add("Cartago");
        provincias.add("Heredia");
        provincias.add("Limón");
        provincias.add("Guanacaste");
        provincias.add("Puntarenas");

    }

    private void cargarCantones(String provincia) {
        ciudades = new ArrayList<>();
        switch (provincia) {
            case "San José":
                ciudades.add("No especificar");
                ciudades.add("San José");
                ciudades.add("Escazú");
                ciudades.add("Desamparados");
                ciudades.add("Puriscal");
                ciudades.add("Tarrazú");
                ciudades.add("Aserrí");
                ciudades.add("Mora");
                ciudades.add("Goicoechea");
                ciudades.add("Santa Ana");
                ciudades.add("Alajuelita");
                ciudades.add("Vázquez de Coronado");
                ciudades.add("Acosta");
                ciudades.add("Tibás");
                ciudades.add("Moravia");
                ciudades.add("Montes de Oca");
                ciudades.add("Turrubares");
                ciudades.add("Dota");
                ciudades.add("Curridabat");
                ciudades.add("Pérez Zeledón");
                ciudades.add("León Cortés");
                break;
            case "Alajuela":
                ciudades.add("No especificar");
                ciudades.add("Alajuela");
                ciudades.add("San Ramón");
                ciudades.add("Grecia");
                ciudades.add("San Mateo");
                ciudades.add("Atenas");
                ciudades.add("Naranjo");
                ciudades.add("Palmares");
                ciudades.add("Poás");
                ciudades.add("Orotina");
                ciudades.add("San Carlos");
                ciudades.add("Zarcero");
                ciudades.add("Valverde Vega");
                ciudades.add("Upala");
                ciudades.add("Los Chiles");
                ciudades.add("Guatuso");
                break;
            case "Heredia":
                ciudades.add("No especificar");
                ciudades.add("Heredia");
                ciudades.add("Barva");
                ciudades.add("Santo Domingo");
                ciudades.add("Santa Barbara");
                ciudades.add("San Rafael");
                ciudades.add("San Isidro");
                ciudades.add("Belén");
                ciudades.add("Flores");
                ciudades.add("San Pablo");
                ciudades.add("Sarapiquí");
                break;
            case "Cartago":
                ciudades.add("No especificar");
                ciudades.add("Cartago");
                ciudades.add("Paraíso");
                ciudades.add("La Unión");
                ciudades.add("Jiménez");
                ciudades.add("Turrialba");
                ciudades.add("Alvarado");
                ciudades.add("Oreamuno");
                ciudades.add("El Guarco");
                break;
            case "Limón":
                ciudades.add("Limón");
                ciudades.add("Pococí");
                ciudades.add("Siquirres");
                ciudades.add("Talamanca");
                ciudades.add("Matina");
                ciudades.add("Guácimo");
                break;
            case "Puntarenas":
                ciudades.add("No especificar");
                ciudades.add("Puntarenas");
                ciudades.add("Esparza");
                ciudades.add("Buenos Aires");
                ciudades.add("Montes de Oro");
                ciudades.add("Osa");
                ciudades.add("Aguirre");
                ciudades.add("Golfito");
                ciudades.add("Coto Brus");
                ciudades.add("Parrita");
                ciudades.add("Corredores");
                ciudades.add("Garabito");
                break;
            case "No especificar":
                ciudades.add("No especificar");
                break;
        }
    }

    @Override
    public void validate() {
        if (this.empleador.getCedula().trim().isEmpty()) {
            addFieldError("empleador.cedula", "Por favor ingrese la cedula");
        }
        if (this.empleador.getNombre().trim().isEmpty()) {
            addFieldError("empleador.nombre", "Por favor ingrese su nombre");
        }
        if (this.empleador.getApellidos().trim().isEmpty()) {
            addFieldError("empleador.apellidos", "Por favor ingrese sus apellidos");
        }
        if (!this.empleador.getTelefonoCasa().trim().isEmpty()) {
            if (this.empleador.getTelefonoCasa().trim().length() < 8) {
                addFieldError("empleador.telefonoCasa", "El telefono debe tener al menos 8 dígitos");
            }
        }
        if (!this.empleador.getTelefonoCelular().trim().isEmpty()) {
            if (this.empleador.getTelefonoCelular().trim().length() < 8) {
                addFieldError("empleador.telefonoCelular", "El celular debe tener al menos 8 dígitos");
            }
        }
        if (!this.empleador.getFax().trim().isEmpty()) {
            if (this.empleador.getFax().trim().length() < 8) {
                addFieldError("empleador.fax", "El fax debe tener al menos 8 dígitos");
            }
        }

    }

    @Override
    public Empleador getModel() {
        return empleador;
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
