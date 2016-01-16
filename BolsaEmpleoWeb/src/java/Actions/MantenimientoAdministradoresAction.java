/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.AdministradorBusiness;
import Dominio.Administrador;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.util.LinkedList;

/**
 *
 * @author JonathanA
 */
public class MantenimientoAdministradoresAction extends ActionSupport implements Preparable {

    private LinkedList<Administrador> administradores;
    private AdministradorBusiness administradorBusiness;

    public MantenimientoAdministradoresAction() {
    }

    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        administradorBusiness = new AdministradorBusiness();
        administradores = administradorBusiness.buscarAdministradores();
    }

    public LinkedList<Administrador> getAdministradores() {
        return administradores;
    }

    public void setAdministradores(LinkedList<Administrador> administradores) {
        this.administradores = administradores;
    }

}
