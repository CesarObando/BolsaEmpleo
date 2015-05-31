/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.CategoriaBusiness;
import Business.VacanteBusiness;
import Dominio.CategoriaVacante;
import Dominio.Usuario;
import Dominio.Vacante;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class VacanteAction extends ActionSupport implements Preparable, ModelDriven, ServletRequestAware, SessionAware {

    private SessionMap<String, Object> sessionMap;
    private HttpServletRequest request;
    private Usuario usuario;
    private boolean dialogo;
    ArrayList<CategoriaVacante> categoriasVacante;
    ArrayList<Integer> numeros;
    ArrayList<String> provincias;
    ArrayList<String> ciudades;
    LinkedList<Vacante> vacantesEmpleador;
    private Vacante vacante;
    private String horaEntradaString;
    private String horaSalidaString;
    private String fechaCreacionString;

    @Override
    public String execute() throws Exception {
        //        if (this.sessionMap.get("usuario") == null){
//            return LOGIN;
//        }
        dialogo = false;
        vacante = new VacanteBusiness().obtenerVacante(vacante.getIdPuesto());
        if (vacante.getFechaCreacion() != null) {
            SimpleDateFormat fechaActualString = new SimpleDateFormat("dd-MM-yyyy");
            fechaCreacionString = fechaActualString.format(vacante.getFechaCreacion());
        }
        if (vacante.getHoraEntrada() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            horaEntradaString = sdf.format(vacante.getHoraEntrada());
        }
        if (vacante.getHoraSalida() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            horaSalidaString = sdf.format(vacante.getHoraSalida());
        }
        if (vacante.getProvincia() != null) {
            cargarCantones(vacante.getProvincia().trim());
        }
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        this.usuario = (Usuario) this.sessionMap.get("usuario");
        vacante = new Vacante();
        categoriasVacante = new CategoriaBusiness().getCategorias();
        vacantesEmpleador = new VacanteBusiness().obtenerVacantesEmpleador("004");
        cargarProvincias();
        ciudades = new ArrayList<>();
        numeros = new ArrayList<>();
        ciudades.add("No especificar");
    }

    public String getFechaCreacionString() {
        return fechaCreacionString;
    }

    public void setFechaCreacionString(String fechaCreacionString) {
        this.fechaCreacionString = fechaCreacionString;
    }

    public String getHoraEntradaString() {
        return horaEntradaString;
    }

    public void setHoraEntradaString(String horaEntradaString) {
        this.horaEntradaString = horaEntradaString;
    }

    public String getHoraSalidaString() {
        return horaSalidaString;
    }

    public void setHoraSalidaString(String horaSalidaString) {
        this.horaSalidaString = horaSalidaString;
    }

    public Vacante getVacante() {
        return vacante;
    }

    public void setVacante(Vacante vacante) {
        this.vacante = vacante;
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

    public ArrayList<Integer> getNumeros() {
        return numeros;
    }

    public void setNumeros(ArrayList<Integer> numeros) {
        this.numeros = numeros;
    }

    public ArrayList<CategoriaVacante> getCategoriasVacante() {
        return categoriasVacante;
    }

    public void setCategoriasVacante(ArrayList<CategoriaVacante> categoriasVacante) {
        this.categoriasVacante = categoriasVacante;
    }

    public LinkedList<Vacante> getVacantesEmpleador() {
        return vacantesEmpleador;
    }

    public void setVacantesEmpleador(LinkedList<Vacante> vacantesEmpleador) {
        this.vacantesEmpleador = vacantesEmpleador;
    }

    public boolean isDialogo() {
        return dialogo;
    }

    public void setDialogo(boolean dialogo) {
        this.dialogo = dialogo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String crearVacante() throws SQLException, ParseException {
        //        if (this.sessionMap.get("usuario") == null){
//            return LOGIN;
//        }
        if (!vacante.getDescripcion().isEmpty() && vacante.getNumeroVacantes() > 0) {
            try {
                SimpleDateFormat formatHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (!horaEntradaString.trim().isEmpty()) {
                    Date fechaHoraEntrada = formatHora.parse("1111-11-11 " + horaEntradaString + ":00.10000");
                    vacante.setHoraEntrada(new Timestamp(fechaHoraEntrada.getTime()));
                }
                if (!horaSalidaString.trim().isEmpty()) {
                    Date fechaHoraSalida = formatHora.parse("1111-11-11 " + horaSalidaString + ":00.10000");
                    vacante.setHoraSalida(new Timestamp(fechaHoraSalida.getTime()));
                }
                SimpleDateFormat formatFecha = new SimpleDateFormat("dd-MM-yyyy");
                Date fechaCreacion = formatFecha.parse(fechaCreacionString);
                VacanteBusiness vacateBusiness = new VacanteBusiness();
                vacante.setFechaCreacion(new java.sql.Date(fechaCreacion.getTime()));
                vacateBusiness.crearVacante(vacante, "004");
                vacantesEmpleador = new VacanteBusiness().obtenerVacantesEmpleador("004");
                return SUCCESS;
            } catch (SQLException ex) {
                throw ex;
            }
        }
        return INPUT;
    }

    public String modificarVacante() throws SQLException, ParseException {
        //        if (this.sessionMap.get("usuario") == null){
//            return LOGIN;
//        }
        if (!vacante.getDescripcion().isEmpty() && vacante.getNumeroVacantes() > 0) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat formatFecha = new SimpleDateFormat("dd-MM-yyyy");
                if (horaEntradaString != null) {
                    if (!horaEntradaString.isEmpty()) {
                        Date fechaHoraEntrada = format.parse("1111-11-11 " + horaEntradaString + ":00.10000");
                        vacante.setHoraEntrada(new Timestamp(fechaHoraEntrada.getTime()));
                    }
                }
                if (horaSalidaString != null) {
                    if (!horaSalidaString.isEmpty()) {
                        Date fechaHoraSalida = format.parse("1111-11-11 " + horaSalidaString + ":00.10000");
                        vacante.setHoraSalida(new Timestamp(fechaHoraSalida.getTime()));
                    }
                }
                if (fechaCreacionString != null) {
                    if (!fechaCreacionString.isEmpty()) {
                        SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
                        Date fechaCreacion = formatFecha.parse(fechaCreacionString);
                        vacante.setFechaCreacion(new java.sql.Date(fechaCreacion.getTime()));
                    }
                }
                VacanteBusiness vacateBusiness = new VacanteBusiness();
                vacateBusiness.actualizarVacante(vacante);
                return SUCCESS;
            } catch (SQLException ex) {
                throw ex;
            }
        }
        return INPUT;
    }

    public String eliminarVacante() throws SQLException, ParseException {
        //        if (this.sessionMap.get("usuario") == null){
//            return LOGIN;
//        }
        try {
            VacanteBusiness vacateBusiness = new VacanteBusiness();
            vacateBusiness.borrarVacante(vacante.getIdPuesto());
            return SUCCESS;
        } catch (SQLException ex) {
            return INPUT;
        }
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
        if (this.vacante.getDescripcion().trim().isEmpty()) {
            addFieldError("vacante.descripcion", "Por favor ingrese una descripción");
        }
        if (this.vacante.getNumeroVacantes() <= 0) {
            addFieldError("vacante.numeroVacantes", "Debe ingresar al menos una vacante");
        }
        if (this.fechaCreacionString.trim().isEmpty()) {
            addFieldError("fechaCreacionString", "Debe ingresar la fecha");
        }
    }

    @Override
    public Vacante getModel() {
        return vacante;
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
