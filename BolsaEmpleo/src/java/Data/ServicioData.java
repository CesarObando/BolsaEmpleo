/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Data;

import Dominio.Servicio;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author ricardo
 */
public class ServicioData {

    public ServicioData() {
    }
    
    public void ingresarServicio(Servicio servicio, String cedula) throws SQLException {
        Connection conexion = BaseDeDatos.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_ingresar_servicio(?,?,?,?,?,?,?,?,?)}");
        cs.registerOutParameter(1, java.sql.Types.INTEGER);
        cs.setString(2, servicio.getNombre());
        cs.setString(3, servicio.getDescripcion());
        cs.setString(4, servicio.getTelefono());
        cs.setString(5, servicio.getCorreo());
        cs.setString(6, servicio.getProvincia());
        cs.setString(7, servicio.getCiudad());
        cs.setString(8, servicio.getDireccion());
        cs.setString(9, cedula);

        cs.execute();
        servicio.setIdServicio(cs.getInt(1));
        conexion.close();
    }

    public LinkedList<Servicio> obtenerServicios() throws SQLException {
        Connection conexion = BaseDeDatos.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_obtener_servicios()}");
        LinkedList<Servicio> servicios = new LinkedList<>();

        cs.execute();
        ResultSet rs = cs.getResultSet();
        while (rs.next()) {
            Servicio servicio = new Servicio();
            servicio.setIdServicio(rs.getInt(1));
            servicio.setNombre(rs.getString(2));
            servicio.setDescripcion(rs.getString(3));
            servicio.setTelefono(rs.getString(4));
            servicio.setCorreo(rs.getString(5));
            servicio.setProvincia(rs.getString(6));
            servicio.setCiudad(rs.getString(7));
            servicio.setDireccion(rs.getString(8));
            servicios.add(servicio);
        }
        conexion.close();
        return servicios;
    }
            
    public LinkedList<Servicio> obtenerServicios(String cedula) throws SQLException {
        Connection conexion = BaseDeDatos.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_obtener_servicios_usuario(?)}");
        cs.setString(1, cedula);
        LinkedList<Servicio> servicios = new LinkedList<>();

        cs.execute();
        ResultSet rs = cs.getResultSet();
        while (rs.next()) {
            Servicio servicio = new Servicio();
            servicio.setIdServicio(rs.getInt(1));
            servicio.setNombre(rs.getString(2));
            servicio.setDescripcion(rs.getString(3));
            servicio.setTelefono(rs.getString(4));
            servicio.setCorreo(rs.getString(5));
            servicio.setProvincia(rs.getString(6));
            servicio.setCiudad(rs.getString(7));
            servicio.setDireccion(rs.getString(8));
            servicios.add(servicio);
        }
        conexion.close();
        return servicios;
    }
    
    public Servicio obtenerServicio(int idServicio) throws SQLException {
        Connection conexion = BaseDeDatos.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_obtener_servicio(?)}");
        cs.setInt(1, idServicio);
        Servicio servicio = new Servicio();

        cs.execute();
        ResultSet rs = cs.getResultSet();
        while (rs.next()) {
            servicio.setIdServicio(rs.getInt(1));
            servicio.setNombre(rs.getString(2));
            servicio.setDescripcion(rs.getString(3));
            servicio.setTelefono(rs.getString(4));
            servicio.setCorreo(rs.getString(5));
            servicio.setProvincia(rs.getString(6));
            servicio.setCiudad(rs.getString(7));
            servicio.setDireccion(rs.getString(8));
        }
        conexion.close();
        return servicio;
    }

    public void actualizarServicio(Servicio servicio) throws SQLException {
        Connection conexion = BaseDeDatos.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_actualizar_servicio(?,?,?,?,?,?,?,?)}");
        cs.setInt(1, servicio.getIdServicio());
        cs.setString(2, servicio.getNombre());
        cs.setString(3, servicio.getDescripcion());
        cs.setString(4, servicio.getTelefono());
        cs.setString(5, servicio.getCorreo());
        cs.setString(6, servicio.getProvincia());
        cs.setString(7, servicio.getCiudad());
        cs.setString(8, servicio.getDireccion());

        cs.executeUpdate();
        conexion.close();
    }
    
    public void eliminarServicio(int idServicio) throws SQLException{
        Connection con = BaseDeDatos.getConexion();
        CallableStatement cs = null;
        try {
            cs = con.prepareCall("{call sp_eliminar_servicio(?)}");
            cs.setInt(1, idServicio);
            cs.execute();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        finally {
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
        }
    }//Fin actualizarCuentaEmpleador
    
}
