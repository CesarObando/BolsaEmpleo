/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.SolicitudBusiness;
import Dominio.Solicitud;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.util.LinkedList;

/**
 *
 * @author JonathanA
 */
public class MantenimientoSolicitudesAction extends ActionSupport implements Preparable{
    
    private LinkedList<Solicitud> solicitudes;
    private SolicitudBusiness solicitudBusiness;
    
    public MantenimientoSolicitudesAction() {
    }
    
    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        solicitudBusiness = new SolicitudBusiness();
        solicitudes = solicitudBusiness.buscarSolicitudes();
    }

    public LinkedList<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(LinkedList<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }
    
}
