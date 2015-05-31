/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Data;

import Dominio.NivelEstudio;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Dalaia
 */
public class NivelEstudioData extends BaseDeDatos{

    public NivelEstudioData() {
    }
    
    public void agregarNivelEstudio(NivelEstudio nivelEstudio) throws SQLException{
        Connection conexion = super.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_ingresar_nivel_estudio(?,?)}"); 
        cs.registerOutParameter(1, java.sql.Types.INTEGER);
        cs.setString(2, nivelEstudio.getDescripcion());
        cs.execute();
        nivelEstudio.setIdNivelEstudio(cs.getInt(1));
        conexion.close();
    }
    
    public void eliminarNivelEstudio(int idNivelEstudio) throws SQLException{
        Connection conexion = super.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_eliminar_nivel_estudio(?)}"); 
        cs.setInt(1, idNivelEstudio);
        cs.execute();
        conexion.close();
    }
    
    public LinkedList<NivelEstudio> obtenerNivelesEstudio() throws SQLException{
        LinkedList<NivelEstudio> nivelesEstudio = new LinkedList<>();
        Connection conexion = super.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_obtener_niveles_estudio()}");

        cs.execute();
        ResultSet rs = cs.getResultSet();
        while (rs.next()) {
            NivelEstudio nivelEstudio = new NivelEstudio();
            nivelEstudio.setIdNivelEstudio(rs.getInt(1));
            nivelEstudio.setDescripcion(rs.getString(2));
            nivelesEstudio.add(nivelEstudio);
        }
        conexion.close();
        return nivelesEstudio;
    }

    
}
