/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Dominio.Administrador;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;

/**
 *
 * @author JonathanA
 */
public class AdministradorData extends BaseData{

    public AdministradorData() {
    }
    
    public Administrador insertarAdministrador(Administrador administrador) throws SQLException{
        String sqlInsertarAdministrador = "{CALL insertar_administrador(?,?,?,?,?)}";
        Connection conexion = this.getConnection();
        conexion.setAutoCommit(false);
        try {
            CallableStatement statement = conexion.prepareCall(sqlInsertarAdministrador);
            statement.setString(1, administrador.getCedula());
            statement.setString(2, administrador.getNombre());
            statement.setString(3, administrador.getApellidos());
            statement.setString(4, administrador.getUsername());
            statement.setString(5, administrador.getPassword());
            statement.executeUpdate();
            conexion.commit();
        } catch (SQLException e) {
            conexion.rollback();
            throw e;
        }
        conexion.close();
        return administrador;
    }
    
    public void editarAdministrador(Administrador administrador) throws SQLException{
        String sqlEditarAdministrador = "{CALL editar_administrador(?,?,?,?)}";
        Connection conexion = this.getConnection();
        conexion.setAutoCommit(false);
        try {
            CallableStatement statement = conexion.prepareCall(sqlEditarAdministrador);
            statement.setString(1, administrador.getCedula());
            statement.setString(2, administrador.getNombre());
            statement.setString(3, administrador.getApellidos());
            statement.setString(4, administrador.getPassword());
            statement.executeUpdate();
            conexion.commit();
        } catch (SQLException e) {
            conexion.rollback();
            throw e;
        }
        conexion.close();
    }
    
    public void eliminarAdministrador(String cedula) throws SQLException{
        String sqlEliminarAdministrador = "{CALL eliminar_administrador(?)}";
        Connection conexion = this.getConnection();
        conexion.setAutoCommit(false);
        try {
            CallableStatement statement = conexion.prepareCall(sqlEliminarAdministrador);
            statement.setString(1, cedula);
            statement.executeUpdate();
            conexion.commit();
        } catch (SQLException e) {
            conexion.rollback();
            throw e;
        }
        conexion.close();
    }
    
    public LinkedList<Administrador> buscarAdministradores() throws SQLException{
        String sqlBuscarAdministradores = "{CALL buscar_administradores}";
        Connection conexion = this.getConnection();
        CallableStatement statement = conexion.prepareCall(sqlBuscarAdministradores);
        ResultSet resultSet = statement.executeQuery();
        LinkedList<Administrador> administradores = new LinkedList<Administrador>();
        while(resultSet.next()) {
            Administrador administradorActual = new Administrador();
            administradorActual.setCedula(resultSet.getString("cedula"));
            administradorActual.setNombre(resultSet.getString("nombre"));
            administradorActual.setApellidos(resultSet.getString("apellidos"));
            administradorActual.setUsername(resultSet.getString("nombre_usuario"));
            administradorActual.setPassword(resultSet.getString("passwd"));
            administradores.add(administradorActual);
        }
        return administradores;
    }
    
    public LinkedList<Administrador> buscarAdministradoresFiltrados(String cedula, String nombre, String apellidos) throws SQLException{
        String sqlBuscarAdministradoresFiltrados = "{CALL buscar_administradores_filtrados(?,?,?)}";
        Connection conexion = this.getConnection();
        CallableStatement statement = conexion.prepareCall(sqlBuscarAdministradoresFiltrados);
        statement.setString(1, cedula);
        statement.setString(2, nombre);
        statement.setString(3, apellidos);
        ResultSet resultSet = statement.executeQuery();
        LinkedList<Administrador> administradores = new LinkedList<Administrador>();
        while(resultSet.next()) {
            Administrador administradorActual = new Administrador();
            administradorActual.setCedula(resultSet.getString("cedula"));
            administradorActual.setNombre(resultSet.getString("nombre"));
            administradorActual.setApellidos(resultSet.getString("apellidos"));
            administradorActual.setUsername(resultSet.getString("nombre_usuario"));
            administradorActual.setPassword(resultSet.getString("passwd"));
            administradores.add(administradorActual);
        }
        return administradores;
    }
}
