/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import Data.EspecialidadOferenteData;
import Dominio.EspecialidadOferente;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Dalaia
 */
public class EspecialidadOferenteBusiness {
    
    private final EspecialidadOferenteData especialidadOferenteData;

    public EspecialidadOferenteBusiness() {
        especialidadOferenteData = new EspecialidadOferenteData();
    }
    
    public void ingresarEspecialidadOferente(EspecialidadOferente especialidadOferente, String cedula) throws SQLException {
        especialidadOferenteData.ingresarEspecialidadOferente(especialidadOferente, cedula);
    }
    
    public void eliminarEspecialidadOferente(int idEspecialidadOferente) throws SQLException {
        especialidadOferenteData.eliminarEspecialidadOferente(idEspecialidadOferente);
    }
   
    public void actualizarEspecialidadOferente(EspecialidadOferente especialidadOferente) throws SQLException {
        especialidadOferenteData.actualizarEspecialidadOferente(especialidadOferente);
    }
    
    public LinkedList<EspecialidadOferente> getEspecialidadesOferente(String cedula) throws SQLException{
        return especialidadOferenteData.getEspecialidadesOferente(cedula);       
    }
    
}
