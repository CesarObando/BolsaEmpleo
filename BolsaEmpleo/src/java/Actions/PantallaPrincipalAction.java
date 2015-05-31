/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.OferenteBusiness;
import Business.VacanteBusiness;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Richard
 */
public class PantallaPrincipalAction{

    LinkedList<String []> vacantesPorCategoria;
    LinkedList<String []> oferentesPorEspecialidad;
    VacanteBusiness vacanteBusiness;
    OferenteBusiness oferenteBusiness;

    public String execute() throws SQLException  {
        vacanteBusiness=new VacanteBusiness();
        oferenteBusiness=new OferenteBusiness();
        vacantesPorCategoria=vacanteBusiness.getNumeroVacantesPorCategoria();
        oferentesPorEspecialidad=oferenteBusiness.getNumeroOferentesPorEspecialidad();
        return "SUCCESS";
    }

    public LinkedList<String[]> getVacantesPorCategoria() {
        return vacantesPorCategoria;
    }

    public void setVacantesPorCategoria(LinkedList<String[]> vacantesPorCategoria) {
        this.vacantesPorCategoria = vacantesPorCategoria;
    }

    public LinkedList<String[]> getOferentesPorEspecialidad() {
        return oferentesPorEspecialidad;
    }

    public void setOferentesPorEspecialidad(LinkedList<String[]> oferentesPorEspecialidad) {
        this.oferentesPorEspecialidad = oferentesPorEspecialidad;
    }
    
    
    
}
