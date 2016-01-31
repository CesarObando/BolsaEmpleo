/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.OfertaBusiness;
import Dominio.Oferta;
import static com.opensymphony.xwork2.Action.INPUT;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.util.LinkedList;

/**
 *
 * @author Tin
 */
public class MantenimientoOfertaAction extends ActionSupport implements Preparable {

    private LinkedList<Oferta> ofertas;
    private OfertaBusiness ofertaBusiness;

    public MantenimientoOfertaAction() {
    }

    @Override
    public String execute() throws Exception {
        return INPUT;
    }

    @Override
    public void prepare() throws Exception {
        ofertaBusiness = new OfertaBusiness();
        ofertas = ofertaBusiness.getOfertas();
    }

    public LinkedList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(LinkedList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

}
