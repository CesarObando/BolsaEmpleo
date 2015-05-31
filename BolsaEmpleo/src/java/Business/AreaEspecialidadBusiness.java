/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import Data.AreaEspecialidadData;
import Dominio.AreaEspecialidad;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Dalaia
 */
public class AreaEspecialidadBusiness {
    
    private final AreaEspecialidadData areaEspecialidadData;

    public AreaEspecialidadBusiness() {
        areaEspecialidadData = new AreaEspecialidadData();
    }
    
    public void agregarAreaEspecialidad(AreaEspecialidad areaEspecialidad) throws SQLException{
        areaEspecialidadData.agregarAreaEspecialidad(areaEspecialidad);
    }
    
    public void eliminarAreaEspecialidad(int idAreaEspecialidad) throws SQLException{
        areaEspecialidadData.eliminarAreaEspecialidad(idAreaEspecialidad);
    }
    
    public LinkedList<AreaEspecialidad> obtenerAreasEspecialidad() throws SQLException{
        return areaEspecialidadData.obtenerAreasEspecialidad();
    }
}
