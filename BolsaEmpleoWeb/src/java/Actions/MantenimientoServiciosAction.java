/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.ServicioBusiness;
import Dominio.Servicio;
import static com.opensymphony.xwork2.Action.INPUT;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.util.LinkedList;

/**
 *
 * @author Tin
 */
public class MantenimientoServiciosAction extends ActionSupport implements Preparable {

    private LinkedList<Servicio> servicios;
    private ServicioBusiness servicioBusiness;

    public MantenimientoServiciosAction() {
    }

    @Override
    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        servicioBusiness = new ServicioBusiness();
        servicios = servicioBusiness.buscarServicios();
    }

    public LinkedList<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(LinkedList<Servicio> servicios) {
        this.servicios = servicios;
    }
}
