/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.EmpleadorBusiness;
import Dominio.Empleador;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.util.LinkedList;

/**
 *
 * @author JonathanA
 */
public class MantenimientoEmpleadoresAction extends ActionSupport implements Preparable {

    private LinkedList<Empleador> empleadores;
    private EmpleadorBusiness empleadorBusiness;

    public MantenimientoEmpleadoresAction() {
    }

    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        empleadorBusiness = new EmpleadorBusiness();
        empleadores = empleadorBusiness.buscarEmpleadores();
    }

    public EmpleadorBusiness getEmpleadorBusiness() {
        return empleadorBusiness;
    }

    public void setEmpleadorBusiness(EmpleadorBusiness empleadorBusiness) {
        this.empleadorBusiness = empleadorBusiness;
    }

    

}
