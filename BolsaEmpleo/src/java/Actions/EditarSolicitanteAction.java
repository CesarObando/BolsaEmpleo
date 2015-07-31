/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.SolicitanteBusiness;
import Dominio.Solicitante;
import Exception.DataException;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Cesar
 */
public class EditarSolicitanteAction extends ActionSupport implements Preparable, ModelDriven<Solicitante>, ServletRequestAware{
    
    private Solicitante solicitanteAEditar;
    private String mensaje;
    private HttpServletRequest request;

    public EditarSolicitanteAction() {
    }

    @Override
    public String execute() throws Exception {
        return INPUT;
    }
    
    @Override
    public void prepare() throws Exception {
        solicitanteAEditar = new Solicitante();
        mensaje = "";
    }

    @Override
    public Solicitante getModel() {
        return this.solicitanteAEditar;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }
    
    @Override
    public void validate(){
        if(solicitanteAEditar.getCedula().length()!=9 || solicitanteAEditar.getCedula().equals(null)){
            addFieldError("cedula", "Debe ingresar un número de identificación válido. Formato de 9 dígitos. Ej.: 000000000");
        }
        if(solicitanteAEditar.getNombre().length()==0 || solicitanteAEditar.getNombre().equals(null)){
            addFieldError("nombre", "Debe ingresar su nombre.");
        }
        if(solicitanteAEditar.getApellidos().length()==0 || solicitanteAEditar.getApellidos().equals(null)){
            addFieldError("apellidos", "Debe ingresar sus apellidos.");
        }
        if(solicitanteAEditar.getUsername().length()==0 || solicitanteAEditar.getUsername().equals(null)){
            addFieldError("username", "Debe ingresar un nombre de usuario.");
        }
        if(solicitanteAEditar.getPassword().length()==0 || solicitanteAEditar.getPassword().equals(null)){
            addFieldError("password", "Debe ingresar una contraseña.");
        } 
    }
    
    public String editar() throws DataException{
        SolicitanteBusiness solicitanteBusiness = new SolicitanteBusiness();
        boolean insertado = true;
        try {
            solicitanteBusiness.editarSolicitante(solicitanteAEditar);
        } catch (SQLException e) {
            insertado=false;
            mensaje="Ocurrió un error con la base de datos.Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
        }
        if(insertado==true){
            this.mensaje = "El solicitante fue editado correctamente";
            return SUCCESS;
        }else{
            return ERROR;
        }
    }

    public Solicitante getSolicitanteAEditar() {
        return solicitanteAEditar;
    }

    public void setSolicitanteAEditar(Solicitante solicitanteAEditar) {
        this.solicitanteAEditar = solicitanteAEditar;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
    
}
