/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import Data.NivelEstudioData;
import Dominio.NivelEstudio;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Dalaia
 */
public class NivelEstudioBusiness {
    
    private final NivelEstudioData nivelEstudioData;

    public NivelEstudioBusiness() {
        nivelEstudioData = new NivelEstudioData();
    }
    
    public void agregarNivelEstudio(NivelEstudio nivelEstudio) throws SQLException{
        nivelEstudioData.agregarNivelEstudio(nivelEstudio);
    }
    
    public void eliminarNivelEstudio(int idNivelEstudio) throws SQLException{
        nivelEstudioData.eliminarNivelEstudio(idNivelEstudio);
    }
    
    public LinkedList<NivelEstudio> obtenerNivelesEstudio() throws SQLException{
        return nivelEstudioData.obtenerNivelesEstudio();
    }
    
    
}
