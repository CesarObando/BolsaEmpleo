/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Actions;

import Business.AreaEspecialidadBusiness;
import Business.EspecialidadOferenteBusiness;
import Business.InstitucionEstudioBusiness;
import Business.NivelEstudioBusiness;
import Dominio.AreaEspecialidad;
import Dominio.EspecialidadOferente;
import Dominio.InstitucionEstudio;
import Dominio.NivelEstudio;
import Dominio.Oferente;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
public class EspecialidadesOferenteAction extends ActionSupport implements Preparable, ModelDriven, ServletRequestAware, SessionAware {
    
    private Oferente oferente;
    private EspecialidadOferente especialidadesOferente;
    private SessionMap<String, Object> sessionMap;
    private HttpServletRequest request;
    private LinkedList<AreaEspecialidad> areasEspecialidad;
    private LinkedList<NivelEstudio> nivelesEstudio;
    private LinkedList<InstitucionEstudio> institucionesEstudio;
    
    @Override
    public String execute() throws Exception {
        if (this.sessionMap.get("oferente") != null) { // Hay una sesion de empleado abierta
            oferente.setEspecialidades(new EspecialidadOferenteBusiness().getEspecialidadesOferente(oferente.getCedula()));
            return INPUT;
        } else {
            return LOGIN;
        }
    }

    @Override
    public void prepare() throws Exception {
        this.oferente = (Oferente) this.sessionMap.get("oferente");
        this.especialidadesOferente = new EspecialidadOferente();
        this.especialidadesOferente.setIdEspecialidadOferente(-1);
        this.areasEspecialidad = new AreaEspecialidadBusiness().obtenerAreasEspecialidad();
        this.nivelesEstudio = new NivelEstudioBusiness().obtenerNivelesEstudio();
        this.institucionesEstudio = new InstitucionEstudioBusiness().obtenerInstitucionesEstudio();
        areasEspecialidad.addLast(new AreaEspecialidad(-1, "--Nueva--"));
        nivelesEstudio.addLast(new NivelEstudio(-1, "--Nueva--"));
        institucionesEstudio.addLast(new InstitucionEstudio(-1, "--Nueva--"));
    }
    
    public String agregarEspecialidad(){
        recargarEspecialidad();
        try {
            new EspecialidadOferenteBusiness().ingresarEspecialidadOferente(especialidadesOferente, oferente.getCedula());
            oferente.getEspecialidades().add(especialidadesOferente);
        } catch (SQLException ex) {
            Logger.getLogger(EspecialidadesOferenteAction.class.getName()).log(Level.SEVERE, null, ex);
            this.addActionMessage("Ha ocurrido un error en la base de datos, por favor espere. O si el error persiste comuníquese con nosotros.\nGracias");
                return ERROR;
        }
        return INPUT;
    }
    
    @Override
    public void validate(){
        if (this.request.getParameter("nombreTitulo").trim().equals("")) {
            addFieldError("nombreTitulo", "Ingrese título");
        }
        if (this.request.getParameter("nivelEstudioV").equals("-1")) {
            addFieldError("nivelEstudioV", "*");
        }
        if (this.request.getParameter("institucionEstudioV").equals("-1")) {
            addFieldError("institucionEstudioV", "*");
        }
        if (this.request.getParameter("areaEspecialidadV").equals("-1")) {
            addFieldError("areaEspecialidadV", "*");
        }
        if (this.request.getParameter("fechaInicio").trim().equals("")) {
            addFieldError("fechaInicio", "Ingrese fecha de inicio");
        }
        else{
                
            try {
                Date fechaActual = new Date();
                Calendar c = Calendar.getInstance(); 
                c.setTime(fechaActual); 
                c.add(Calendar.DATE, 1);
                fechaActual = c.getTime();
                String fecha = this.request.getParameter("fechaInicio").toString().replace('/', '-');
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                Date fechaIngreso = format.parse(fecha);

                if (fechaActual.before(fechaIngreso)) {
                    addFieldError("fechaInicio", "Fecha de ingreso incorrecta");
                }
                
                String fechaFin = this.request.getParameter("fechaFin").toString().replace('/', '-');
                Date fechaTermina = format.parse(fechaFin);
                
                if (fechaIngreso.after(fechaTermina)) {
                    addFieldError("fechaFin", "Fecha fin incorrecta");
                }
        }   catch (ParseException ex) {      
                Logger.getLogger(EspecialidadesOferenteAction.class.getName()).log(Level.SEVERE, null, ex);
            }      
        }
        
    }
    
    @SkipValidation
    public String quitarEspecialidad(){
        int value = Integer.parseInt(this.request.getParameter("value"));
        try{
            for(EspecialidadOferente a : oferente.getEspecialidades()){
                if(a.getIdEspecialidadOferente() == value){
                    new EspecialidadOferenteBusiness().eliminarEspecialidadOferente(value);
                    if(oferente.getEspecialidades().getFirst() == a){
                        oferente.getEspecialidades().removeFirst();
                    }
                    else{
                        oferente.getEspecialidades().remove(a);
                    }
                }
            }
        }catch(Exception ex){
            Logger.getLogger(EspecialidadesOferenteAction.class.getName()).log(Level.SEVERE, null, ex);
            this.addActionMessage("Ha ocurrido un error en la base de datos, por favor espere. O si el error persiste comuníquese con nosotros.\nGracias");
                return ERROR;
        }
        return INPUT;
    }
    
    @SkipValidation
    public String cambiarAExperiencias(){
        return INPUT;
    }
    
    @SkipValidation
    public String insertarNivelEstudio(){
        String descripcion = this.request.getParameter("nuevoNivelEstudio");
        if(descripcion == null || descripcion.equals("")){
            addFieldError("nuevoNivelEstudio", "No puede estar vacío");
            return INPUT;
        }
        NivelEstudio nivelEstudio = new NivelEstudio();
        nivelEstudio.setDescripcion(this.request.getParameter("nuevoNivelEstudio"));
        try {
            new NivelEstudioBusiness().agregarNivelEstudio(nivelEstudio);
            nivelesEstudio.add(nivelEstudio);
        } catch (SQLException ex) {
            String errorMensaje = "Ha ocurrido un error en la base de datos, por favor espere. O si el error persiste comuníquese con nosotros.\nGracias";
            if (ex.getErrorCode() == 1644){
                errorMensaje = ex.getMessage();
            }
            Logger.getLogger(EspecialidadesOferenteAction.class.getName()).log(Level.SEVERE, null, ex);
            this.addActionMessage(errorMensaje);
            return ERROR;
        }
        return INPUT;
    }
    
    @SkipValidation
    public String insertarInstitucionEstudio(){
        String descripcion = this.request.getParameter("nuevoInstitucionEstudio");
        if(descripcion == null || descripcion.equals("")){
            addFieldError("nuevoInstitucionEstudio", "No puede estar vacío");
            return INPUT;
        }
        InstitucionEstudio institucionEstudio = new InstitucionEstudio();
        institucionEstudio.setNombre(this.request.getParameter("nuevoInstitucionEstudio"));
        try {
            new InstitucionEstudioBusiness().agregarInstitucionEstudio(institucionEstudio);
            institucionesEstudio.add(institucionEstudio);
        } catch (SQLException ex) {
            String errorMensaje = "Ha ocurrido un error en la base de datos, por favor espere. O si el error persiste comuníquese con nosotros.\nGracias";
            if (ex.getErrorCode() == 1644){
                errorMensaje = ex.getMessage();
            }
            Logger.getLogger(EspecialidadesOferenteAction.class.getName()).log(Level.SEVERE, null, ex);
            this.addActionMessage(errorMensaje);
            return ERROR;
        }
        return INPUT;
    }
    
    @SkipValidation
    public String insertarAreaEspecialidad(){
        String descripcion = this.request.getParameter("nuevoAreaEspecialidad");
        if(descripcion == null || descripcion.equals("")){
            addFieldError("nuevoAreaEspecialidad", "No puede estar vacío");
            return INPUT;
        }
        AreaEspecialidad areaEspecialidad = new AreaEspecialidad();
        areaEspecialidad.setDescripcion(descripcion);
        try {
            new AreaEspecialidadBusiness().agregarAreaEspecialidad(areaEspecialidad);
            areasEspecialidad.add(areaEspecialidad);
        } catch (SQLException ex) {
            String errorMensaje = "Ha ocurrido un error en la base de datos, por favor espere. O si el error persiste comuníquese con nosotros.\nGracias";
            if (ex.getErrorCode() == 1644){
                errorMensaje = ex.getMessage();
            }
            Logger.getLogger(EspecialidadesOferenteAction.class.getName()).log(Level.SEVERE, null, ex);
            this.addActionMessage(errorMensaje);
            return ERROR;
        }
        return INPUT;
    }
    
    
    public void recargarEspecialidad(){
        especialidadesOferente.getAreaEspecialidad().setIdAreaEspecialidad(Integer.parseInt(this.request.getParameter("areaEspecialidadV")));
        especialidadesOferente.getNivelEstudio().setIdNivelEstudio(Integer.parseInt(this.request.getParameter("nivelEstudioV")));
        especialidadesOferente.getInstitucionEstudio().setIdInstitucion(Integer.parseInt(this.request.getParameter("institucionEstudioV")));
        for(AreaEspecialidad a : areasEspecialidad){
            if(a.getIdAreaEspecialidad() == especialidadesOferente.getAreaEspecialidad().getIdAreaEspecialidad()){
                especialidadesOferente.getAreaEspecialidad().setDescripcion(a.getDescripcion());
            }
        }
        for(NivelEstudio a : nivelesEstudio){
            if(a.getIdNivelEstudio()== especialidadesOferente.getNivelEstudio().getIdNivelEstudio()){
                especialidadesOferente.getNivelEstudio().setDescripcion(a.getDescripcion());
            }
        }
        for(InstitucionEstudio a : institucionesEstudio){
            if(a.getIdInstitucion() == especialidadesOferente.getInstitucionEstudio().getIdInstitucion()){
                especialidadesOferente.getInstitucionEstudio().setNombre(a.getNombre());
            }
        }
    }
    
    

    public Oferente getOferente() {
        return oferente;
    }

    public void setOferente(Oferente oferente) {
        this.oferente = oferente;
    }

    public EspecialidadOferente getEspecialidadesOferente() {
        return especialidadesOferente;
    }

    public void setEspecialidadesOferente(EspecialidadOferente especialidadesOferente) {
        this.especialidadesOferente = especialidadesOferente;
    }
    
    

    public LinkedList<AreaEspecialidad> getAreasEspecialidad() {
        return areasEspecialidad;
    }

    public void setAreasEspecialidad(LinkedList<AreaEspecialidad> areasEspecialidad) {
        this.areasEspecialidad = areasEspecialidad;
    }

    public LinkedList<NivelEstudio> getNivelesEstudio() {
        return nivelesEstudio;
    }

    public void setNivelesEstudio(LinkedList<NivelEstudio> nivelesEstudio) {
        this.nivelesEstudio = nivelesEstudio;
    }

    public LinkedList<InstitucionEstudio> getInstitucionesEstudio() {
        return institucionesEstudio;
    }

    public void setInstitucionesEstudio(LinkedList<InstitucionEstudio> institucionesEstudio) {
        this.institucionesEstudio = institucionesEstudio;
    }
    
    @Override
    public Object getModel() {
        return this.especialidadesOferente;
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
