/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Dominio.Empleador;
import Exception.DataException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;

/**
 *
 * @author Tin
 */
public class EmpleadorData extends BaseData {

    public Empleador insertarEmpleador(Empleador empleador) throws SQLException {
        Connection conexion = super.getConnection();
        String sqlInsert = "{CALL insertar_empleador(?,?,?,?,?,?,?,?,?,?,?,?)}";
        CallableStatement statement = conexion.prepareCall(sqlInsert);

        conexion.setAutoCommit(false);
        try {
            statement.registerOutParameter(1, Types.INTEGER);
            statement.setString(2, empleador.getCedula());
            statement.setString(3, empleador.getNombre());
            statement.setString(4, empleador.getApellidos());
            statement.setString(5, empleador.getCorreo());
            statement.setString(6, empleador.getTelefonoFijo());
            statement.setString(7, empleador.getTelefonoMovil());
            statement.setString(8, empleador.getCedulaJuridica());
            statement.setString(9, empleador.getNombreEmpresa());
            statement.setString(10, empleador.getDireccion());
            statement.setString(11, empleador.getUsername());
            statement.setString(12, empleador.getPass());
            statement.executeUpdate();
            empleador.setId(statement.getInt(1));
            conexion.commit();
        } catch (SQLException e) {
            conexion.rollback();
            throw e;
        }
        conexion.close();

        return empleador;
    }

    public void editarEmpleador(Empleador empleador) throws SQLException {
        Connection conexion = super.getConnection();
        String sqlInsert = "{CALL editar_empleador(?,?,?,?,?,?,?,?,?,?,?)}";
        CallableStatement statement = conexion.prepareCall(sqlInsert);
        conexion.setAutoCommit(false);
        try {
            statement.setInt(1, empleador.getId());
            statement.setString(2, empleador.getCedula());
            statement.setString(3, empleador.getCorreo());
            statement.setString(4, empleador.getNombre());
            statement.setString(5, empleador.getApellidos());
            statement.setString(6, empleador.getTelefonoFijo());
            statement.setString(7, empleador.getTelefonoMovil());
            statement.setString(8, empleador.getCedulaJuridica());
            statement.setString(9, empleador.getNombreEmpresa());
            statement.setString(10, empleador.getDireccion());
            statement.setString(11, empleador.getPass());
            statement.executeUpdate();
            conexion.commit();
        } catch (SQLException e) {
            conexion.rollback();
            throw e;
        }
        conexion.close();
    }

    public void eliminarEmpleador(int id) throws SQLException {
        String sqlEliminar = "{CALL eliminar_empleador(?)}";
        Connection conexion = this.getConnection();
        conexion.setAutoCommit(false);
        try {
            CallableStatement statement = conexion.prepareCall(sqlEliminar);
            statement.setInt(1, id);
            statement.executeUpdate();
            conexion.commit();
        } catch (SQLException e) {
            conexion.rollback();
            throw e;
        }
        conexion.close();
    }

    public LinkedList<Empleador> buscarEmpleadores() throws SQLException, DataException {
        String sqlBuscarEmpleadores = "{CALL buscar_empleadores}";
        Connection conexion = this.getConnection();
        LinkedList<Empleador> empleadores = new LinkedList<Empleador>();
        try {
            CallableStatement statement = conexion.prepareCall(sqlBuscarEmpleadores);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Empleador empleadorActual = new Empleador();
                empleadorActual.setId(resultSet.getInt("id"));
                empleadorActual.setCedula(resultSet.getString("cedula"));
                empleadorActual.setNombre(resultSet.getString("nombre"));
                empleadorActual.setApellidos(resultSet.getString("apellidos"));
                empleadorActual.setCorreo(resultSet.getString("correo"));
                empleadorActual.setTelefonoFijo(resultSet.getString("telefono_fijo"));
                empleadorActual.setTelefonoMovil(resultSet.getString("telefono_movil"));
                empleadorActual.setCedulaJuridica(resultSet.getString("cedula_juridica"));
                empleadorActual.setNombreEmpresa(resultSet.getString("nombre_empresa"));
                empleadorActual.setDireccion(resultSet.getString("direccion"));
                empleadorActual.setUsername(resultSet.getString("username"));
                empleadorActual.setPass(resultSet.getString("passwd"));
                empleadorActual.setUltimaActualizacion(resultSet.getDate("ultima_actualizacion"));
                empleadores.add(empleadorActual);
            }
        } catch (SQLException e) {
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        return empleadores;
    }

    public LinkedList<Empleador> buscarEmpleadoresFiltrados(String cedula, String nombre, String apellidos) throws SQLException, DataException {
        String sqlBuscarEmpleadoresFiltrados = "{CALL buscar_empleadores_filtrados (?,?,?)}";
        Connection conexion = this.getConnection();
        LinkedList<Empleador> empleadores = new LinkedList<Empleador>();
        try {
            CallableStatement statement = conexion.prepareCall(sqlBuscarEmpleadoresFiltrados);
            statement.setString(1, cedula);
            statement.setString(2, nombre);
            statement.setString(3, apellidos);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Empleador empleadorActual = new Empleador();
                empleadorActual.setId(resultSet.getInt("id"));
                empleadorActual.setCedula(resultSet.getString("cedula"));
                empleadorActual.setNombre(resultSet.getString("nombre"));
                empleadorActual.setApellidos(resultSet.getString("apellidos"));
                empleadorActual.setCorreo(resultSet.getString("correo"));
                empleadorActual.setTelefonoFijo(resultSet.getString("telefono_fijo"));
                empleadorActual.setTelefonoMovil(resultSet.getString("telefono_movil"));
                empleadorActual.setCedulaJuridica(resultSet.getString("cedula_juridica"));
                empleadorActual.setNombreEmpresa(resultSet.getString("nombre_empresa"));
                empleadorActual.setDireccion(resultSet.getString("direccion"));
                empleadorActual.setUsername(resultSet.getString("username"));
                empleadorActual.setPass(resultSet.getString("passwd"));
                empleadorActual.setUltimaActualizacion(resultSet.getDate("ultima_actualizacion"));
                empleadores.add(empleadorActual);
            }
        } catch (SQLException e) {
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        return empleadores;
    }

    public Empleador buscarEmpleador(int id) throws SQLException, DataException {
        String sqlBuscarEmpleador = "{CALL buscar_empleador (?)}";
        Connection conexion = this.getConnection();
        Empleador empleador = new Empleador();
        try {
            CallableStatement statement = conexion.prepareCall(sqlBuscarEmpleador);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                empleador.setId(resultSet.getInt("id"));
                empleador.setCedula(resultSet.getString("cedula"));
                empleador.setNombre(resultSet.getString("nombre"));
                empleador.setApellidos(resultSet.getString("apellidos"));
                empleador.setCorreo(resultSet.getString("correo"));
                empleador.setTelefonoFijo(resultSet.getString("telefono_fijo"));
                empleador.setTelefonoMovil(resultSet.getString("telefono_movil"));
                empleador.setCedulaJuridica(resultSet.getString("cedula_juridica"));
                empleador.setNombreEmpresa(resultSet.getString("nombre_empresa"));
                empleador.setDireccion(resultSet.getString("direccion"));
                empleador.setUsername(resultSet.getString("username"));
                empleador.setPass(resultSet.getString("passwd"));
                empleador.setUltimaActualizacion(resultSet.getDate("ultima_actualizacion"));
            }
        } catch (SQLException e) {
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
        return empleador;
    }
    
     public Empleador buscarEmpleadorPorNombreUsuario(String nombreUsuario) throws SQLException, DataException {
        String sqlBuscarEmpleador = "{CALL buscar_empleador_por_nombre_usuario (?)}";
        Connection conexion = this.getConnection();
        Empleador empleador = new Empleador();
        try {
            CallableStatement statement = conexion.prepareCall(sqlBuscarEmpleador);
            statement.setString(1, nombreUsuario);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                empleador.setId(resultSet.getInt("id"));
                empleador.setCedula(resultSet.getString("cedula"));
                empleador.setNombre(resultSet.getString("nombre"));
                empleador.setApellidos(resultSet.getString("apellidos"));
                empleador.setCorreo(resultSet.getString("correo"));
                empleador.setTelefonoFijo(resultSet.getString("telefono_fijo"));
                empleador.setTelefonoMovil(resultSet.getString("telefono_movil"));
                empleador.setCedulaJuridica(resultSet.getString("cedula_juridica"));
                empleador.setNombreEmpresa(resultSet.getString("nombre_empresa"));
                empleador.setDireccion(resultSet.getString("direccion"));
                empleador.setUsername(resultSet.getString("username"));
                empleador.setPass(resultSet.getString("passwd"));
                empleador.setUltimaActualizacion(resultSet.getDate("ultima_actualizacion"));
            }
        } catch (SQLException e) {
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
        return empleador;
    }

    public Empleador inicioSesion(String user, String pass) throws SQLException, DataException {
        String sqlSelect = "{CALL iniciar_sesion_empleador(?,?)}";
        Connection conexion = super.getConnection();
        Empleador empleador = new Empleador();
        try {
            CallableStatement statement = conexion.prepareCall(sqlSelect);
            statement.setString(1, user);
            statement.setString(2, pass);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                empleador.setId(resultSet.getInt("id"));
                empleador.setCedula(resultSet.getString("cedula"));
                empleador.setNombre(resultSet.getString("nombre"));
                empleador.setApellidos(resultSet.getString("apellidos"));
                empleador.setCorreo(resultSet.getString("correo"));
                empleador.setTelefonoFijo(resultSet.getString("telefono_fijo"));
                empleador.setTelefonoMovil(resultSet.getString("telefono_movil"));
                empleador.setCedulaJuridica(resultSet.getString("cedula_juridica"));
                empleador.setNombreEmpresa(resultSet.getString("nombre_empresa"));
                empleador.setDireccion(resultSet.getString("direccion"));
                empleador.setUsername(resultSet.getString("username"));
                empleador.setPass(resultSet.getString("passwd"));
            }
        } catch (Exception e) {
            if (e.getMessage().equalsIgnoreCase("The statement did not return a result set.")) {
                return empleador;
            }
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
        return empleador;
    }
}
