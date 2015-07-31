/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.AdministradorBusiness;
import Business.EmpleadorBusiness;
import Dominio.Administrador;
import Dominio.Empleador;
import Exception.DataException;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Tin
 */
public class InsertarEmpleadoresAction extends ActionSupport implements Preparable, ModelDriven<Empleador>, ServletRequestAware{
    
    private Empleador empleadorInsetar;
    private String mensaje;
    private HttpServletRequest request;

    

    public InsertarEmpleadoresAction() {
    }
     public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
      empleadorInsetar=new Empleador();
    }

   @Override
    public Empleador getModel() {
        return this.empleadorInsetar;
    }
    // @Override TO DO
//    public void validate(){
//        if(empleadorInsetar.getCedula().length()!=9 || empleadorInsetar.getCedula().equals(null)){
//            addFieldError("cedula", "Debe ingresar un número de identificación válido. Formato de 9 dígitos. Ej.: 000000000");
//        }
//        if(empleadorInsetar.getNombre().length()==0 || empleadorInsetar.getNombre().equals(null)){
//            addFieldError("nombre", "Debe ingresar su nombre.");
//        }
//        if(administradorInsertar.getApellidos().length()==0 || administradorInsertar.getApellidos().equals(null)){
//            addFieldError("apellidos", "Debe ingresar sus apellidos.");
//        }
//        if(administradorInsertar.getUsername().length()==0 || administradorInsertar.getUsername().equals(null)){
//            addFieldError("username", "Debe ingresar un nombre de usuario.");
//        }
//        if(administradorInsertar.getPassword().length()==0 || administradorInsertar.getPassword().equals(null)){
//            addFieldError("password", "Debe ingresar una contraseña.");
//        } 
//    }
    
      public String insertar() {
          EmpleadorBusiness empleadorBusiness = new EmpleadorBusiness();
        boolean insertado = true;
        try {
            empleadorBusiness.insertarEmpleador(empleadorInsetar);
        } catch (SQLException e) {
            insertado=false;
            mensaje="Ocurrió un error con la base de datos.Inténtelo nuevamente. Si persiste comuníquese con el administrador del sistema.";
        }
        if(insertado==true){
            this.mensaje = "El administrador fue insertado correctamente";
            return SUCCESS;
        }else{
            return ERROR;
        }
    }


    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }
    
    
    public Empleador getEmpleadorInsetar() {
        return empleadorInsetar;
    }

    public void setEmpleadorInsetar(Empleador empleadorInsetar) {
        this.empleadorInsetar = empleadorInsetar;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

   
    
}
