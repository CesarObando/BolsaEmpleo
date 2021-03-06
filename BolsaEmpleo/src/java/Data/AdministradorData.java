/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Dominio.Administrador;
import Exception.DataException;
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
public class AdministradorData extends BaseData {

    public AdministradorData() {
    }

    public Administrador insertarAdministrador(Administrador administrador) throws SQLException, DataException {
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
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
        return administrador;
    }

    public void editarAdministrador(Administrador administrador) throws SQLException, DataException {
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
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
    }

    public void eliminarAdministrador(String cedula) throws SQLException, DataException {
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
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
    }

    public LinkedList<Administrador> buscarAdministradores() throws SQLException, DataException {
        String sqlBuscarAdministradores = "{CALL buscar_administradores}";
        Connection conexion = this.getConnection();
        LinkedList<Administrador> administradores = new LinkedList<Administrador>();
        try {
            CallableStatement statement = conexion.prepareCall(sqlBuscarAdministradores);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Administrador administradorActual = new Administrador();
                administradorActual.setCedula(resultSet.getString("cedula"));
                administradorActual.setNombre(resultSet.getString("nombre"));
                administradorActual.setApellidos(resultSet.getString("apellidos"));
                administradorActual.setUsername(resultSet.getString("nombre_usuario"));
                administradorActual.setPassword(resultSet.getString("passwd"));
                administradores.add(administradorActual);
            }
        } catch (SQLException e) {
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        return administradores;
    }

    public LinkedList<Administrador> buscarAdministradoresFiltrados(String cedula, String nombre, String apellidos) throws SQLException, DataException {
        String sqlBuscarAdministradoresFiltrados = "{CALL buscar_administradores_filtrados(?,?,?)}";
        Connection conexion = this.getConnection();
        LinkedList<Administrador> administradores = new LinkedList<Administrador>();
        try {
            CallableStatement statement = conexion.prepareCall(sqlBuscarAdministradoresFiltrados);
            statement.setString(1, cedula);
            statement.setString(2, nombre);
            statement.setString(3, apellidos);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Administrador administradorActual = new Administrador();
                administradorActual.setCedula(resultSet.getString("cedula"));
                administradorActual.setNombre(resultSet.getString("nombre"));
                administradorActual.setApellidos(resultSet.getString("apellidos"));
                administradorActual.setUsername(resultSet.getString("nombre_usuario"));
                administradorActual.setPassword(resultSet.getString("passwd"));
                administradores.add(administradorActual);
            }
        } catch (SQLException e) {
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
        return administradores;
    }

    public Administrador buscarAdministrador(String cedula) throws SQLException, DataException{
        String sqlBuscarAdministrador = "{CALL buscar_administrador(?)}";
        Connection conexion = this.getConnection();
        Administrador administrador = new Administrador();
        try {
            CallableStatement statement = conexion.prepareCall(sqlBuscarAdministrador);
            statement.setString(1, cedula);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                administrador.setCedula(resultSet.getString("cedula"));
                administrador.setNombre(resultSet.getString("nombre"));
                administrador.setApellidos(resultSet.getString("apellidos"));
                administrador.setUsername(resultSet.getString("nombre_usuario"));
                administrador.setPassword(resultSet.getString("passwd"));
                return administrador;
            }
        } catch (SQLException e) {
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
        return null;
    }
    
    public Administrador iniciarSesion(String nombreUsuario, String password) throws SQLException, DataException {
        String sqlIniciarSesion = "{CALL iniciar_sesion_administradores(?,?)}";
        Connection conexion = this.getConnection();
        Administrador administrador = new Administrador();
        try {
            CallableStatement statement = conexion.prepareCall(sqlIniciarSesion);
            statement.setString(1, nombreUsuario);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                administrador.setCedula(resultSet.getString("cedula"));
                administrador.setNombre(resultSet.getString("nombre"));
                administrador.setApellidos(resultSet.getString("apellidos"));
                administrador.setUsername(resultSet.getString("nombre_usuario"));
                administrador.setPassword(resultSet.getString("passwd"));
                return administrador;
            }
        } catch (SQLException e) {
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
        return null;
    }
}
