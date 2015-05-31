/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import Data.ExperienciaLaboralData;
import Dominio.ExperienciaLaboral;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Dalaia
 */
public class ExperienciaLaboralBusiness {
    
    private final ExperienciaLaboralData experienciaLaboralData;

    public ExperienciaLaboralBusiness() {
        experienciaLaboralData = new ExperienciaLaboralData();
    }
    
    public void actualizarEspecialidadOferente(ExperienciaLaboral experienciaLaboral) throws SQLException {
        experienciaLaboralData.actualizarExperienciaOferente(experienciaLaboral);
    }
    
    public void eliminarEspecialidadOferente(int idExperienciaLaboral) throws SQLException {
        experienciaLaboralData.eliminarExperienciaOferente(idExperienciaLaboral);
    }
    
    public void ingresarEspecialidadOferente(ExperienciaLaboral experienciaLaboral, String cedula) throws SQLException {
        experienciaLaboralData.ingresarExperienciaOferente(experienciaLaboral, cedula);
    }
    
    public LinkedList<ExperienciaLaboral> getExperienciasOferente(String cedula) throws SQLException{
        return experienciaLaboralData.getExperienciasOferente(cedula);
    }
    
    
}
