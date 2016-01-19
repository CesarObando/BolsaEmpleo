/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Dominio.Solicitud;
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
public class SolicitudData extends BaseData {

    public SolicitudData() {
    }

    public Solicitud insertarSolicitud(Solicitud solicitud) throws SQLException {
        String sqlInsertarSolicitud = "{CALL insertar_solicitud(?,?,?)}";
        Connection conexion = this.getConnection();
        conexion.setAutoCommit(false);
        try {
            CallableStatement statement = conexion.prepareCall(sqlInsertarSolicitud);
            statement.registerOutParameter(1, Types.INTEGER);
            statement.setInt(2, solicitud.getSolicitante().getId());
            statement.setInt(3, solicitud.getOferta().getId());
            statement.executeUpdate();
            solicitud.setId(statement.getInt(1));
            conexion.commit();
        } catch (SQLException e) {
            conexion.rollback();
            throw e;
        }
        conexion.close();
        return solicitud;
    }
    
    public Solicitud editarSolicitud(Solicitud solicitudAEditar) throws SQLException, DataException {
        Connection conexion = super.getConnection();
        String sqlEditarSolicitud = "{Call editar_solicitud (?,?)}";
        try {
            CallableStatement statement = conexion.prepareCall(sqlEditarSolicitud);
            statement.setInt(1, solicitudAEditar.getId());
            statement.setBoolean(2, solicitudAEditar.isFavorito());
            statement.executeUpdate();
            conexion.commit();
        } catch (Exception e) {
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
        return solicitudAEditar;
    }


    public void eliminarSolicitud(int id) throws SQLException {
        String sqlEliminarSolicitud = "{CALL eliminar_solicitud(?)}";
        Connection conexion = this.getConnection();
        conexion.setAutoCommit(false);
        try {
            CallableStatement statement = conexion.prepareCall(sqlEliminarSolicitud);
            statement.setInt(1, id);
            statement.executeUpdate();
            conexion.commit();
        } catch (SQLException e) {
            conexion.rollback();
            throw e;
        }
        conexion.close();
    }

    public LinkedList<Solicitud> buscarSolicitudes() throws SQLException {
        String sqlBuscarSolicitudes = "{CALL buscar_solicitudes}";
        Connection conexion = this.getConnection();
        CallableStatement statement = conexion.prepareCall(sqlBuscarSolicitudes);
        ResultSet resultSet = statement.executeQuery();
        LinkedList<Solicitud> solicitudes = new LinkedList<Solicitud>();
        while (resultSet.next()) {
            Solicitud solicitudActual = new Solicitud();
            solicitudActual.setId(resultSet.getInt("id"));
            solicitudActual.getSolicitante().setId(resultSet.getInt("solicitante"));
            solicitudActual.getOferta().setId(resultSet.getInt("oferta"));
            solicitudes.add(solicitudActual);
        }
        return solicitudes;
    }

    public LinkedList<Solicitud> buscarSolicitudesFiltradas(int solicitante, int oferta) throws SQLException {
        String sqlBuscarSolicitudesFiltradas = "{CALL buscar_solicitudes_filtradas(?,?)}";
        Connection conexion = this.getConnection();
        CallableStatement statement = conexion.prepareCall(sqlBuscarSolicitudesFiltradas);
        statement.setInt(1, solicitante);
        statement.setInt(2, oferta);
        ResultSet resultSet = statement.executeQuery();
        LinkedList<Solicitud> solicitudes = new LinkedList<Solicitud>();
        while (resultSet.next()) {
            Solicitud solicitudActual = new Solicitud();
            solicitudActual.setId(resultSet.getInt("id"));
            solicitudActual.getSolicitante().setId(resultSet.getInt("solicitante"));
            solicitudActual.getOferta().setId(resultSet.getInt("oferta"));
            solicitudes.add(solicitudActual);
        }
        return solicitudes;
    }

    public Solicitud buscarSolicitud(int id) throws SQLException, DataException {
        String sqlBuscarSolicitud = "{CALL buscar_solicitud(?)}";
        Connection conexion = this.getConnection();
        Solicitud solicitud = new Solicitud();
        try {
            CallableStatement statement = conexion.prepareCall(sqlBuscarSolicitud);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                solicitud.setId(resultSet.getInt("id"));
                solicitud.getSolicitante().setId(resultSet.getInt("solicitante"));
                solicitud.getOferta().setId(resultSet.getInt("oferta"));
                return solicitud;
            }
        } catch (SQLException e) {
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
        return null;
    }
}
