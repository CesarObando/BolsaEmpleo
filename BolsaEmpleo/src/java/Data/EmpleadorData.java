/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Data;

import Dominio.Empleador;
import Dominio.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author ricardo
 */
public class EmpleadorData {

    public EmpleadorData() {
    }
    
    public void actualizarCuentaEmpleador(Empleador empleador) throws SQLException{
        Connection con = BaseDeDatos.getConexion();
        CallableStatement cs = null;
        try {
            cs = con.prepareCall("{call sp_modificar_datos_empleador(?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, empleador.getCedula());
            cs.setString(2, empleador.getNombre());
            cs.setString(3, empleador.getApellidos());
//            cs.setString(4, empleador.getProvincia());
//            cs.setString(5, empleador.getCiudad());
//            cs.setString(6, empleador.getDireccion());
//            cs.setInt(7, empleador.getCodigoPostal());
//            cs.setString(8, empleador.getEmail());
//            cs.setString(9, empleador.getTelefonoCasa());
//            cs.setString(10, empleador.getTelefonoCelular());
//            cs.setString(11, empleador.getFax());
//            cs.setDate(12, empleador.getFechaNacimiento());
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
    
    public void borrarEmpleador(String cedula) throws SQLException{
        Connection con = BaseDeDatos.getConexion();
        CallableStatement cs = null;
        try {
            cs = con.prepareCall("{call sp_borrar_empleador(?)}");
            cs.setString(1, cedula);
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
    
    public LinkedList<Empleador> getEmpleadores() throws SQLException{
        Connection con = BaseDeDatos.getConexion();
        LinkedList<Empleador> empleadores=new LinkedList<>();
        try {
            CallableStatement cs = con.prepareCall("{call sp_obtener_empleadores()}");
            cs.execute();
            ResultSet rs = cs.getResultSet();
            while (rs.next() == true){
                Empleador empleador=new Empleador();
                empleador.setCedula(rs.getString(1));
                empleador.setNombre(rs.getString(2));
                empleador.setApellidos(rs.getString(3));
//                empleador.setProvincia(rs.getString(4));
//                empleador.setCiudad(rs.getString(5));
//                empleador.setDireccion(rs.getString(6));
//                empleador.setCodigoPostal(rs.getInt(7));
//                empleador.setEmail(rs.getString(8));
//                empleador.setTelefonoCasa(rs.getString(9));
//                empleador.setTelefonoCelular(rs.getString(10));
//                empleador.setFax(rs.getString(11));
//                empleador.setFechaNacimiento(rs.getDate(12));
//                empleador.getContactoEmpleador().setCedulaJuridica(rs.getString(13));
                empleadores.add(empleador);
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
        return empleadores;
     }  
     
     public Empleador getEmpleador(String cedula) throws SQLException{
        Connection con = BaseDeDatos.getConexion();
        Empleador empleador=new Empleador();
        try {
            CallableStatement cs = con.prepareCall("{call sp_obtener_empleador(?)}");
            cs.setString(1, cedula);
            cs.execute();
            ResultSet rs = cs.getResultSet();
            while (rs.next() == true){
                empleador.setCedula(rs.getString(1));
                empleador.setNombre(rs.getString(2));
                empleador.setApellidos(rs.getString(3));
//                empleador.setProvincia(rs.getString(4));
//                empleador.setCiudad(rs.getString(5));
//                empleador.setDireccion(rs.getString(6));
//                empleador.setCodigoPostal(rs.getInt(7));
//                empleador.setEmail(rs.getString(8));
//                empleador.setTelefonoCasa(rs.getString(9));
//                empleador.setTelefonoCelular(rs.getString(10));
//                empleador.setFax(rs.getString(11));
//                empleador.setFechaNacimiento(rs.getDate(12));
//                empleador.getContactoEmpleador().setCedulaJuridica(rs.getString(13));
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
        return empleador;
     }  
     
     public void agregarEmpleador(Empleador empleador) throws SQLException {
        Connection conexion = BaseDeDatos.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_ingresar_empleador(?,?)}");
        cs.setString(1, empleador.getCedula());
        cs.setString(2, null);
        cs.execute();
        conexion.close();
    }
    
    
    public void cambiarUsuarioEmpleador(Usuario usuario) throws SQLException {
        Connection conexion = BaseDeDatos.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_actualizar_usuario_a_empleador(?,?)}");
        cs.setString(1, usuario.getCedula());
        cs.setBoolean(2, usuario.isEmpleador());
        cs.execute();
        conexion.close();
    }
    
}
