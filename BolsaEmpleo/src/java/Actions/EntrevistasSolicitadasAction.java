/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Actions;

import Business.VacanteBusiness;
import Data.OferenteData;
import Dominio.Oferente;
import Dominio.Vacante;
import static com.opensymphony.xwork2.Action.ERROR;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;


public class EntrevistasSolicitadasAction extends ActionSupport implements Preparable, ModelDriven, ServletRequestAware, SessionAware {
    
    private Oferente oferente;
    private SessionMap<String, Object> sessionMap;
    private HttpServletRequest request;
    
    
    @Override
    public void prepare() throws Exception {
        this.oferente = (Oferente) this.sessionMap.get("oferente");
    }
    
    @Override
    public String execute() throws Exception {
        if (this.sessionMap.get("oferente") != null) { // Hay una sesion de empleado abierta
            oferente.setVacantes(new VacanteBusiness().obtenerVacantesOferente(oferente.getCedula()));
            return INPUT;
        } else {
            return LOGIN;
        }
    }
    
    public String cancelarEntrevista(){
        int value = Integer.parseInt(this.request.getParameter("idPuesto"));
        try{
            for(Vacante a : oferente.getVacantes()){
                if(a.getIdPuesto() == value){
                    new OferenteData().eliminarOferenteVacante(oferente.getCedula(), value);
                    
                    if(oferente.getVacantes().getFirst() == a){
                        oferente.getVacantes().removeFirst();
                    }
                    else{
                        oferente.getVacantes().remove(a);
                    }
                }
            }
        }catch(Exception ex){
            Logger.getLogger(EntrevistasSolicitadasAction.class.getName()).log(Level.SEVERE, null, ex);
            this.addActionMessage("Ha ocurrido un error en la base de datos, por favor espere. O si el error persiste comuníquese con nosotros.\nGracias");
                return ERROR;
        }
        return SUCCESS;
    }

    public Oferente getOferente() {
        return oferente;
    }

    public void setOferente(Oferente oferente) {
        this.oferente = oferente;
    }
    
    @Override
    public Object getModel() {
        return this.oferente;
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
