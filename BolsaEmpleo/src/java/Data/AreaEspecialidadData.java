/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Data;

import Dominio.AreaEspecialidad;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;

/**
 *
 * @author Dalaia
 */
public class AreaEspecialidadData extends BaseDeDatos{

    public AreaEspecialidadData() {
    }
    
    public void agregarAreaEspecialidad(AreaEspecialidad areaEspecialidad) throws SQLException{
        Connection conexion = super.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_ingresar_area_especialidad(?,?)}"); 
        cs.registerOutParameter(1, java.sql.Types.INTEGER);
        cs.setString(2, areaEspecialidad.getDescripcion());
        cs.execute();
        areaEspecialidad.setIdAreaEspecialidad(cs.getInt(1));
        conexion.close();
    }
    
    public void eliminarAreaEspecialidad(int idAreaEspecialidad) throws SQLException{
        Connection conexion = super.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_eliminar_area_especialidad(?)}"); 
        cs.setInt(1, idAreaEspecialidad);
        cs.execute();
        conexion.close();
    }
    
    public LinkedList<AreaEspecialidad> obtenerAreasEspecialidad() throws SQLException{
        LinkedList<AreaEspecialidad> areasEspecialidad = new LinkedList<>();
        Connection conexion = super.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_obtener_areas_especialidad()}");

        cs.execute();
        ResultSet rs = cs.getResultSet();
        while (rs.next()) {
            AreaEspecialidad areaEspecialidad = new AreaEspecialidad();
            areaEspecialidad.setIdAreaEspecialidad(rs.getInt(1));
            areaEspecialidad.setDescripcion(rs.getString(2));
            areasEspecialidad.add(areaEspecialidad);
        }
        conexion.close();
        return areasEspecialidad;
    }
}
