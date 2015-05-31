/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Actions;

import Business.ExperienciaLaboralBusiness;
import Dominio.ExperienciaLaboral;
import Dominio.Oferente;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
public class ExperienciasOferenteAction extends ActionSupport implements Preparable, ModelDriven, ServletRequestAware, SessionAware{
    
    //Acordarse hacer tipo tab pero al costado izq, para cambiar entre experiencias y especialidades y usuario
    private Oferente oferente;
    private ExperienciaLaboral experienciaLaboral;
    private SessionMap<String, Object> sessionMap;
    private HttpServletRequest request;
    
    @Override
    public void prepare() throws Exception {
        this.oferente = (Oferente) this.sessionMap.get("oferente");
        experienciaLaboral = new ExperienciaLaboral();
    }
    
    @Override
    public String execute() throws Exception {
        if (this.sessionMap.get("oferente") != null) { // Hay una sesion de empleado abierta
            oferente.setExperienciasLaborales(new ExperienciaLaboralBusiness().getExperienciasOferente(oferente.getCedula()));
            return INPUT;
        } else {
            return LOGIN;
        }
    }

    
    public String agregarExperiencia(){
        try {
            new ExperienciaLaboralBusiness().ingresarEspecialidadOferente(experienciaLaboral, oferente.getCedula());
            oferente.getExperienciasLaborales().add(experienciaLaboral);
            experienciaLaboral= new ExperienciaLaboral();
        } catch (SQLException ex) {
            Logger.getLogger(ExperienciasOferenteAction.class.getName()).log(Level.SEVERE, null, ex);
            this.addActionMessage("Ha ocurrido un error en la base de datos, por favor espere. O si el error persiste comuníquese con nosotros.\nGracias");
                return ERROR;
        }
        return INPUT;
    }
    
    @SkipValidation   
    public String quitarExperiencia(){
        int value = Integer.parseInt(this.request.getParameter("value"));
        try{
            for(ExperienciaLaboral a : oferente.getExperienciasLaborales()){
                if(a.getIdExperiencia()== value){
                    new ExperienciaLaboralBusiness().eliminarEspecialidadOferente(value);
                    if(oferente.getExperienciasLaborales().getFirst() == a){
                        oferente.getExperienciasLaborales().removeFirst();
                    }
                    else{
                        oferente.getExperienciasLaborales().remove(a);
                    }
                }
            }
        }catch(Exception ex){
            this.addActionMessage("Ha ocurrido un error en la base de datos, por favor espere. O si el error persiste comuníquese con nosotros.\nGracias");
                return ERROR;
        }
        return INPUT;
    }
    

    @Override
    public void validate(){
        if (this.request.getParameter("empresa").trim().equals("")) {
            addFieldError("empresa", "Ingrese nombre de empresa");
        }
        if (this.request.getParameter("puesto").trim().equals("")) {
            addFieldError("puesto", "Ingrese puesto");
        }
        if (this.request.getParameter("descripcionFunciones").trim().equals("")) {
            addFieldError("descripcionFunciones", "Ingrese Funciones");
        }
        if (this.request.getParameter("fechaIngreso").trim().equals("")) {
            addFieldError("fechaIngreso", "Ingrese fecha de ingreso");
        }
        else{

            try {
                Date fechaActual = new Date();
                Calendar c = Calendar.getInstance(); 
                c.setTime(fechaActual); 
                c.add(Calendar.DATE, 1);
                fechaActual = c.getTime();
                String fecha = this.request.getParameter("fechaIngreso").toString().replace('/', '-');
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                Date fechaIngreso = format.parse(fecha);

                if (fechaActual.before(fechaIngreso)) {
                    addFieldError("fechaIngreso", "Fecha de ingreso incorrecta");
                }
                
                String fechaFin = this.request.getParameter("fechaTermina").toString().replace('/', '-');
                Date fechaTermina = format.parse(fechaFin);
                
                if (fechaIngreso.after(fechaTermina)) {
                    addFieldError("fechaTermina", "Fecha fin incorrecta");
                }
                

        }   catch (ParseException ex) {
                Logger.getLogger(ExperienciasOferenteAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public Object getModel() {
        return this.experienciaLaboral;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

    public Oferente getOferente() {
        return oferente;
    }

    public void setOferente(Oferente oferente) {
        this.oferente = oferente;
    }

    public ExperienciaLaboral getExperienciaLaboral() {
        return experienciaLaboral;
    }

    public void setExperienciaLaboral(ExperienciaLaboral experienciaLaboral) {
        this.experienciaLaboral = experienciaLaboral;
    }
    
    
}
