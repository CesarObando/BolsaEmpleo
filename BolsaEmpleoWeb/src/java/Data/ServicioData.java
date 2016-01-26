/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Dominio.Servicio;
import Exception.DataException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;

public class ServicioData extends BaseData {

    public ServicioData() {
    }

    public Servicio insertarServicio(Servicio servicio) throws SQLException {
        Connection conexion = super.getConnection();
        String sqlInsert = "{CALL insertar_servicio(?,?,?,?,?)}";
        CallableStatement statement = conexion.prepareCall(sqlInsert);
        conexion.setAutoCommit(false);
        //cargamos el statement con la informacion nueva
        try {
            statement.registerOutParameter(1, Types.INTEGER);
            statement.setString(2, servicio.getTitulo());
            statement.setString(3, servicio.getDescripcion());
            statement.setInt(4, servicio.getSolicitante().getId());
            statement.setInt(5, servicio.getCategoria().getId());
            statement.executeUpdate();
            servicio.setId(statement.getInt(1));
            conexion.commit();
        } catch (SQLException e) {
            conexion.rollback();
            throw e;
        }
        conexion.close();
        return servicio;
    }

    public void editarServicio(Servicio servicio) throws SQLException {
        String sqlEditar = "{CALL editar_servicio(?,?,?,?)}";
        Connection conexion = this.getConnection();
        conexion.setAutoCommit(false);
        try {
            CallableStatement statement = conexion.prepareCall(sqlEditar);
            statement.setInt(1, servicio.getId());
            statement.setString(2, servicio.getTitulo());
            statement.setString(3, servicio.getDescripcion());
            statement.setInt(4, servicio.getCategoria().getId());
            statement.executeUpdate();
            conexion.commit();
        } catch (SQLException e) {
            conexion.rollback();
            throw e;
        }
        conexion.close();
    }

    public void eliminarServicio(int id) throws SQLException {
        String sqlEliminar = "{CALL eliminar_servicio(?)}";
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

    //retorna todas las ofertas existentes
    public LinkedList<Servicio> buscarServicios() throws SQLException {

        String sqlSelect = "buscar_servicios";
        Connection conexion = super.getConnection();
        CallableStatement statement = conexion.prepareCall(sqlSelect);
        ResultSet resultSet = statement.executeQuery();
        LinkedList<Servicio> servicios = new LinkedList<Servicio>();
        while (resultSet.next()) {
            Servicio servicio = new Servicio();
            servicio.setId(resultSet.getInt("id"));
            servicio.setTitulo(resultSet.getString("titulo"));
            servicio.setDescripcion(resultSet.getString("descripcion"));
            servicio.getSolicitante().setId(resultSet.getInt("solicitante"));
            servicio.getCategoria().setId(resultSet.getInt("categoria"));
            servicios.add(servicio);
        }
        return servicios;
    }

    public LinkedList<Servicio> buscarServiciosFiltrados(int categoria, String titulo) throws SQLException {

        String sqlSelect = "{CALL buscar_servicios_filtrados(?,?)}";
        Connection conexion = super.getConnection();
        CallableStatement statement = conexion.prepareCall(sqlSelect);
        statement.setString(1, titulo);
        statement.setInt(2, categoria);
        ResultSet resultSet = statement.executeQuery();
        LinkedList<Servicio> servicios = new LinkedList<Servicio>();
        while (resultSet.next()) {
            Servicio servicio = new Servicio();
            servicio.setId(resultSet.getInt("id"));
            servicio.setTitulo(resultSet.getString("titulo"));
            servicio.setDescripcion(resultSet.getString("descripcion"));
            servicio.getSolicitante().setId(resultSet.getInt("solicitante"));
            servicio.getCategoria().setId(resultSet.getInt("categoria"));
            servicios.add(servicio);
        }
        return servicios;
    }

    public LinkedList<Servicio> buscarServiciosPorSolicitante(int categoria, String titulo, int solicitante) throws SQLException {
        String sqlSelect = "{CALL buscar_servicios_por_solicitante(?,?,?)}";
        Connection conexion = super.getConnection();
        CallableStatement statement = conexion.prepareCall(sqlSelect);
        statement.setString(1, titulo);
        statement.setInt(2, categoria);
        statement.setInt(3, solicitante);
        ResultSet resultSet = statement.executeQuery();
        LinkedList<Servicio> servicios = new LinkedList<Servicio>();
        while (resultSet.next()) {
            Servicio servicio = new Servicio();
            servicio.setId(resultSet.getInt("id"));
            servicio.setTitulo(resultSet.getString("titulo"));
            servicio.setDescripcion(resultSet.getString("descripcion"));
            servicio.getSolicitante().setId(resultSet.getInt("solicitante"));
            servicio.getCategoria().setId(resultSet.getInt("categoria"));
            servicios.add(servicio);
        }
        return servicios;
    }

    public Servicio buscarServicio(int id) throws SQLException, DataException {
        String sqlSelect = "{CALL buscar_servicio(?)}";
        Connection conexion = this.getConnection();
        Servicio servicio = new Servicio();
        try {
            CallableStatement statement = conexion.prepareCall(sqlSelect);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                servicio.setId(resultSet.getInt("id"));
                servicio.setTitulo(resultSet.getString("titulo"));
                servicio.setDescripcion(resultSet.getString("descripcion"));
                servicio.getSolicitante().setId(resultSet.getInt("solicitante"));
                servicio.getCategoria().setId(resultSet.getInt("categoria"));
            }
        } catch (SQLException e) {
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
        return servicio;
    }
}
