/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Dominio.EspecialidadOferente;
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
public class EspecialidadOferenteData extends BaseDeDatos {

    public EspecialidadOferenteData() {
    }

    public void actualizarEspecialidadOferente(EspecialidadOferente especialidadOferente) throws SQLException {
        Connection conexion = super.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_actualizar_especialidad_oferente(?,?,?,?,?,?,?)}");
        cs.setInt(1, especialidadOferente.getIdEspecialidadOferente());
        cs.setTimestamp(2, new Timestamp(especialidadOferente.getFechaInicio().getTime()));
        cs.setTimestamp(3, new Timestamp(especialidadOferente.getFechaFin().getTime()));
        cs.setString(4, especialidadOferente.getNombreTitulo());
        cs.setInt(5, especialidadOferente.getInstitucionEstudio().getIdInstitucion());
        cs.setInt(6, especialidadOferente.getNivelEstudio().getIdNivelEstudio());
        cs.setInt(7, especialidadOferente.getAreaEspecialidad().getIdAreaEspecialidad());
        cs.execute();
        conexion.close();
    }

    public void eliminarEspecialidadOferente(int idEspecialidadOferente) throws SQLException {
        Connection conexion = super.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_eliminar_especialidad_oferente(?)}");
        cs.setInt(1, idEspecialidadOferente);
        cs.execute();
        conexion.close();
    }

    public void ingresarEspecialidadOferente(EspecialidadOferente especialidadOferente, String cedula) throws SQLException {
        Connection conexion = super.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_ingresar_especialidad_oferente(?,?,?,?,?,?,?,?)}");
        cs.registerOutParameter(1, java.sql.Types.INTEGER);
        cs.setTimestamp(2, new Timestamp(especialidadOferente.getFechaInicio().getTime()));
        cs.setTimestamp(3, new Timestamp(especialidadOferente.getFechaFin().getTime()));
        cs.setString(4, especialidadOferente.getNombreTitulo());
        cs.setInt(5, especialidadOferente.getInstitucionEstudio().getIdInstitucion());
        cs.setInt(6, especialidadOferente.getNivelEstudio().getIdNivelEstudio());
        cs.setInt(7, especialidadOferente.getAreaEspecialidad().getIdAreaEspecialidad());
        cs.setString(8, cedula);
        cs.execute();
        especialidadOferente.setIdEspecialidadOferente(cs.getInt(1));
        conexion.close();
    }
    
    public LinkedList<EspecialidadOferente> getEspecialidadesOferente(String cedula) throws SQLException{
        Connection con = BaseDeDatos.getConexion();
        LinkedList<EspecialidadOferente> especialidadesOferente=new LinkedList<>();
        try {
            CallableStatement cs = con.prepareCall("{call sp_obtener_especialidades_oferente(?)}");
            cs.setString(1, cedula);
            cs.execute();
            ResultSet rs = cs.getResultSet();
            while (rs.next() == true){
                EspecialidadOferente espOferente=new EspecialidadOferente();
                espOferente.setIdEspecialidadOferente(rs.getInt(1));
                espOferente.setFechaInicio(rs.getDate(2));
                espOferente.setFechaFin(rs.getDate(3));
                espOferente.setNombreTitulo(rs.getString(4));
                espOferente.getInstitucionEstudio().setIdInstitucion(rs.getInt(5));
                espOferente.getInstitucionEstudio().setNombre(rs.getString(6));
                espOferente.getNivelEstudio().setIdNivelEstudio(rs.getInt(7));
                espOferente.getNivelEstudio().setDescripcion(rs.getString(8));
                espOferente.getAreaEspecialidad().setIdAreaEspecialidad(rs.getInt(9));
                espOferente.getAreaEspecialidad().setDescripcion(rs.getString(10));
                especialidadesOferente.add(espOferente);
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
        }
        return especialidadesOferente;
     }  

}
