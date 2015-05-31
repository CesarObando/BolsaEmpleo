/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import Data.InstitucionEstudioData;
import Dominio.InstitucionEstudio;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Dalaia
 */
public class InstitucionEstudioBusiness {
    
    private final InstitucionEstudioData institucionEstudioData;

    public InstitucionEstudioBusiness() {
        institucionEstudioData = new InstitucionEstudioData();
    }
    
    public void agregarInstitucionEstudio(InstitucionEstudio institucionEstudio) throws SQLException{
        institucionEstudioData.agregarInstitucionEstudio(institucionEstudio);
    }
    
    public void eliminarInstitucionEstudio(int idInstitucion) throws SQLException{
        institucionEstudioData.eliminarInstitucionEstudio(idInstitucion);
    }
    
    public LinkedList<InstitucionEstudio> obtenerInstitucionesEstudio() throws SQLException{
        return institucionEstudioData.obtenerInstitucionesEstudio();
    }
    
    
}
