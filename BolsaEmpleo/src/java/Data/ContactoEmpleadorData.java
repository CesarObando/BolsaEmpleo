/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Data;

import Dominio.ContactoEmpleador;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author ricardo
 */
public class ContactoEmpleadorData {

    public ContactoEmpleadorData() {
    }
    
    public void insertarEmpresa(ContactoEmpleador empresa, String cedulaEmpleador) throws SQLException{
        Connection con = BaseDeDatos.getConexion();
        CallableStatement cs = null;
        try {
            cs = con.prepareCall("{call sp_ingresar_empresa(?,?,?,?,?,?,?,?)}");
            cs.setString(1, cedulaEmpleador);
            cs.setString(2, empresa.getCedulaJuridica());
            cs.setString(3, empresa.getEmpresa());
            cs.setString(4, empresa.getDesingnacion());
            cs.setString(5, empresa.getTelefono());
            cs.setInt(6, empresa.getExtension());
            cs.setString(7, empresa.getFax());
            cs.setString(8, empresa.getEmail());
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
    
    public void modificarEmpresa(ContactoEmpleador empresa) throws SQLException{
        Connection con = BaseDeDatos.getConexion();
        CallableStatement cs = null;
        try {
            cs = con.prepareCall("{call sp_modificar_empresa(?,?,?,?,?,?,?)}");
            cs.setString(1, empresa.getCedulaJuridica());
            cs.setString(2, empresa.getEmpresa());
            cs.setString(3, empresa.getDesingnacion());
            cs.setString(4, empresa.getTelefono());
            cs.setInt(5, empresa.getExtension());
            cs.setString(6, empresa.getFax());
            cs.setString(7, empresa.getEmail());
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
    
    public ContactoEmpleador getContactoEmpleador(String cedula) throws SQLException{
        Connection con = BaseDeDatos.getConexion();
        ContactoEmpleador contactoEmp=new ContactoEmpleador();
        try {
            CallableStatement cs = con.prepareCall("{call sp_obtener_contacto_empleador(?)}");
            cs.setString(1, cedula);
            cs.execute();
            ResultSet rs = cs.getResultSet();
            while (rs.next() == true){
                contactoEmp.setCedulaJuridica(rs.getString(1));
                contactoEmp.setEmpresa(rs.getString(2));
                contactoEmp.setDesingnacion(rs.getString(3));
                contactoEmp.setTelefono(rs.getString(4));
                contactoEmp.setExtension(rs.getInt(5));
                contactoEmp.setFax(rs.getString(6));
                contactoEmp.setEmail(rs.getString(7));
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
        return contactoEmp;
     } 
    
    public LinkedList<ContactoEmpleador> getContactosEmpleadores() throws SQLException{
        Connection con = BaseDeDatos.getConexion();
        LinkedList<ContactoEmpleador> contactos=new LinkedList<>();
        try {
            CallableStatement cs = con.prepareCall("{call sp_obtener_empresas()}");
            cs.execute();
            ResultSet rs = cs.getResultSet();
            while (rs.next() == true){
                ContactoEmpleador contactoEmp=new ContactoEmpleador();
                contactoEmp.setCedulaJuridica(rs.getString(1));
                contactoEmp.setEmpresa(rs.getString(2));
                contactoEmp.setDesingnacion(rs.getString(3));
                contactoEmp.setTelefono(rs.getString(4));
                contactoEmp.setExtension(rs.getInt(5));
                contactoEmp.setFax(rs.getString(6));
                contactoEmp.setEmail(rs.getString(7));
                contactos.add(contactoEmp);
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
        return contactos;
     } 
    
    public void borrarContactoEmpleador(String cedulaJuridica) throws SQLException{
        Connection con = BaseDeDatos.getConexion();
        CallableStatement cs = null;
        try {
            cs = con.prepareCall("{call sp_borrar_empresa(?)}");
            cs.setString(1, cedulaJuridica);
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
