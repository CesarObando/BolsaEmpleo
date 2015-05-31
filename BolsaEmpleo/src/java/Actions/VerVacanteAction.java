/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Actions;

import Business.OferenteBusiness;
import Business.VacanteBusiness;
import Dominio.Oferente;
import Dominio.Vacante;
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

/**
 *
 * @author ricardo
 */
public class VerVacanteAction extends ActionSupport implements Preparable, ModelDriven, ServletRequestAware, SessionAware {
    
    private Vacante vacante;
    private Oferente oferente;
    private SessionMap<String, Object> sessionMap;
    private HttpServletRequest request;
    private static int idPuesto;
    private boolean sol;
    
    @Override
    public void prepare() throws Exception {
        sol = false;
        if (this.request.getParameter("idPuesto") != null){
            idPuesto = Integer.parseInt(this.request.getParameter("idPuesto"));
            vacante = new VacanteBusiness().obtenerVacante(idPuesto);
            
            if (this.sessionMap.get("oferente") != null) { // Hay una sesion de empleado abierta
                oferente = (Oferente) this.sessionMap.get("oferente");
                oferente.setVacantes(new VacanteBusiness().obtenerVacantesOferente(oferente.getCedula()));
                for(Vacante vacanteActual : oferente.getVacantes()){
                    if(vacanteActual.getIdPuesto() == idPuesto){
                        sol = true;
                        request.setAttribute("sol", sol);
                    }
                }
            }
            
            
        }
            
    }
    
    @Override
    public String execute() throws Exception {
        return INPUT;
    }
    
    public String solicitarEntrevista(){
        if (this.sessionMap.get("oferente") == null)  // Hay una sesion de empleado abierta
            return LOGIN;
        try {
            oferente = (Oferente) this.sessionMap.get("oferente");
            new OferenteBusiness().ingresarOferenteVacante(this.oferente.getCedula(), idPuesto);
            this.addActionMessage("Se ha enviado la solicitud de entrevista correctamente");
            return SUCCESS;
        } catch (SQLException ex) {
            if(ex.getErrorCode() == 1062){
                this.addActionMessage("Ya habías solicitado una entrevista para esta vacante");
                return "error2";
            }
            
            Logger.getLogger(VerVacanteAction.class.getName()).log(Level.SEVERE, null, ex);
            this.addActionMessage("Ha ocurrido un error en la base de datos, por favor espere. O si el error persiste comuníquese con nosotros.\nGracias");
                return ERROR;
        }
    }

    @Override
    public Object getModel() {
        return this.vacante;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap<String, Object>) map;
    }

    public Vacante getVacante() {
        return vacante;
    }

    public void setVacante(Vacante vacante) {
        this.vacante = vacante;
    }

    public boolean isSol() {
        return sol;
    }

    public void setSol(boolean sol) {
        this.sol = sol;
    }

    public Oferente getOferente() {
        return oferente;
    }

    public void setOferente(Oferente oferente) {
        this.oferente = oferente;
    }
    
    
    
}
