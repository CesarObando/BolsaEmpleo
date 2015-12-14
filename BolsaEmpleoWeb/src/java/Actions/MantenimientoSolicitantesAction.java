/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.SolicitanteBusiness;
import Dominio.Solicitante;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.util.LinkedList;

/**
 *
 * @author Cesar
 */
public class MantenimientoSolicitantesAction  extends ActionSupport implements Preparable{
    
    private LinkedList<Solicitante> solicitantes;

    public MantenimientoSolicitantesAction() {
    }

    @Override
    public String execute() throws Exception {
        return INPUT;
    }
    
    @Override
    public void prepare() throws Exception {
        solicitantes = new SolicitanteBusiness().buscarSolicitantes();
    }

    public LinkedList<Solicitante> getSolicitantes() {
        return solicitantes;
    }

    public void setSolicitantes(LinkedList<Solicitante> solicitantes) {
        this.solicitantes = solicitantes;
    }
    
}
