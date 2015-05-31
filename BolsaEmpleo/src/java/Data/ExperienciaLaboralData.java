/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Dominio.ExperienciaLaboral;
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
public class ExperienciaLaboralData extends BaseDeDatos {

    public ExperienciaLaboralData() {
    }

    public void actualizarExperienciaOferente(ExperienciaLaboral experienciaLaboral) throws SQLException {
        Connection conexion = super.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_actualizar_experiencia_laboral(?,?,?,?,?,?)}");
        cs.setInt(1, experienciaLaboral.getIdExperiencia());
        cs.setString(2, experienciaLaboral.getEmpresa());
        cs.setString(3, experienciaLaboral.getPuesto());
        cs.setTimestamp(4, new Timestamp(experienciaLaboral.getFechaIngreso().getTime()));
        cs.setTimestamp(5, new Timestamp(experienciaLaboral.getFechaTermina().getTime()));
        cs.setString(6, experienciaLaboral.getDescripcionFunciones());
        cs.execute();
        conexion.close();
    }

    public void eliminarExperienciaOferente(int idExperienciaLaboral) throws SQLException {
        Connection conexion = super.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_eliminar_experiencia_laboral(?)}");
        cs.setInt(1, idExperienciaLaboral);
        cs.execute();
        conexion.close();
    }

    public void ingresarExperienciaOferente(ExperienciaLaboral experienciaLaboral, String cedula) throws SQLException {
        Connection conexion = super.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_ingresar_experiencia_laboral(?,?,?,?,?,?,?)}");

        cs.registerOutParameter(1, java.sql.Types.INTEGER);
        cs.setString(2, experienciaLaboral.getEmpresa());
        cs.setString(3, experienciaLaboral.getPuesto());
        cs.setTimestamp(4, new Timestamp(experienciaLaboral.getFechaIngreso().getTime()));
        cs.setTimestamp(5, new Timestamp(experienciaLaboral.getFechaTermina().getTime()));
        cs.setString(6, experienciaLaboral.getDescripcionFunciones());
        cs.setString(7, cedula);
        cs.execute();

        experienciaLaboral.setIdExperiencia(cs.getInt(1));
        conexion.close();
    }
    
    public LinkedList<ExperienciaLaboral> getExperienciasOferente(String cedula) throws SQLException{
        Connection con = BaseDeDatos.getConexion();
        LinkedList<ExperienciaLaboral> experienciasOferente=new LinkedList<>();
        try {
            CallableStatement cs = con.prepareCall("{call sp_obtener_experiencia_oferente(?)}");
            cs.setString(1, cedula);
            cs.execute();
            ResultSet rs = cs.getResultSet();
            while (rs.next() == true){
                ExperienciaLaboral expLaboral=new ExperienciaLaboral();
                expLaboral.setIdExperiencia(rs.getInt(1));
                expLaboral.setEmpresa(rs.getString(2));
                expLaboral.setPuesto(rs.getString(3));
                expLaboral.setFechaIngreso(rs.getDate(4));
                expLaboral.setFechaTermina(rs.getDate(5));
                expLaboral.setDescripcionFunciones(rs.getString(6));
                experienciasOferente.add(expLaboral);
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
        return experienciasOferente;
     }
}
