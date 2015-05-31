/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Data;

import Dominio.AreaEspecialidad;
import Dominio.InstitucionEstudio;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Dalaia
 */
public class InstitucionEstudioData extends BaseDeDatos{

    public InstitucionEstudioData() {
    }
    
    public void agregarInstitucionEstudio(InstitucionEstudio institucionEstudio) throws SQLException{
        Connection conexion = super.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_ingresar_institucion_estudio(?,?)}"); 
        cs.registerOutParameter(1, java.sql.Types.INTEGER);
        cs.setString(2, institucionEstudio.getNombre());
        cs.execute();
        institucionEstudio.setIdInstitucion(cs.getInt(1));
        conexion.close();
    }
    
    public void eliminarInstitucionEstudio(int idInstitucion) throws SQLException{
        Connection conexion = super.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_eliminar_institucion_estudio(?)}"); 
        cs.setInt(1, idInstitucion);
        cs.execute();
        conexion.close();
    }
    
    public LinkedList<InstitucionEstudio> obtenerInstitucionesEstudio() throws SQLException{
        LinkedList<InstitucionEstudio> institucionesEstudio = new LinkedList<>();
        Connection conexion = super.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_obtener_insituciones_estudio()}");

        cs.execute();
        ResultSet rs = cs.getResultSet();
        while (rs.next()) {
            InstitucionEstudio institucionEstudio = new InstitucionEstudio();
            institucionEstudio.setIdInstitucion(rs.getInt(1));
            institucionEstudio.setNombre(rs.getString(2));
            institucionesEstudio.add(institucionEstudio);
        }
        conexion.close();
        return institucionesEstudio;
    }
    
    
}
