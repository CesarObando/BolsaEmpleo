/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Dominio.Empleador;
import Dominio.Oferta;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

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
        //cargamos el statement con la informacion nueva
        try {
            statement.registerOutParameter(1, Types.INTEGER);//variable de salida
            statement.setString(2, empleador.getCedula());
            statement.setString(3, empleador.getNombre());
            statement.setString(4, empleador.getApellidos());
            statement.setString(5, empleador.getCorreo());
            statement.setString(6, empleador.getTelefonoFijo());
            statement.setString(7, empleador.getTelefonoMovil());
            statement.setString(8, empleador.getCedulaJuridica());
            statement.setString(9, empleador.getNombreEmpresa());
            statement.setString(10, empleador.getDireccion());
            statement.setString(11, empleador.getNombreUsuario());
            statement.setString(12, empleador.getPass());
            statement.executeUpdate();

            empleador.setId(statement.getInt(1));//solitamos el id generado

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
        String sqlInsert = "{CALL editar_empleador(?,?,?,?,?,?,?)}";
        CallableStatement statement = conexion.prepareCall(sqlInsert);

        conexion.setAutoCommit(false);
        //cargamos el statement con la informacion nueva
        try {
            statement.setInt(1, empleador.getId());
            statement.setString(2, empleador.getCorreo());
            statement.setString(3, empleador.getTelefonoFijo());
            statement.setString(4, empleador.getTelefonoMovil());
            statement.setString(5, empleador.getNombreEmpresa());
            statement.setString(6, empleador.getDireccion());
            statement.setString(7, empleador.getPass());
            statement.executeUpdate();

            conexion.commit();
        } catch (SQLException e) {
            conexion.rollback();
            throw e;
        }
        conexion.close();
    }

    //elimina el empleador indicado 

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

    //verifica que el usuario sea valido 
    public boolean inicioSecion(String user, String pass) throws SQLException {

        String sqlSelect = "CALL validacionEmpleador(?,?)";
        Connection conexion = super.getConnection();
        CallableStatement statement = conexion.prepareCall(sqlSelect);
        statement.setString(1, user);
        statement.setString(2, pass);
        ResultSet result = statement.executeQuery();

        return result.next();

    }
}
