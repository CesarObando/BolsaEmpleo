package Data;

import Dominio.Solicitud;
import Exception.DataException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;

public class SolicitudData extends BaseData {

    //Constructor vacio
    public SolicitudData() {
    }

    public Solicitud insertarSolicitud(Solicitud solicitud) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlInsertarSolicitud = "{CALL insertar_solicitud(?,?,?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        //Propiedad de la conexion para evitar finalizar la transaccion automaticamente
        conexion.setAutoCommit(false);
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlInsertarSolicitud);
            //Definicion del parametro de salida
            statement.registerOutParameter(1, Types.INTEGER);
            //Definicion de los parametros que recibe el procedimiento almacenado
            statement.setInt(2, solicitud.getSolicitante().getId());
            statement.setInt(3, solicitud.getOferta().getId());
            //Ejecucion del procedimiento almacenado
            statement.executeUpdate();
            //Recuperacion del parametro de salida
            solicitud.setId(statement.getInt(1));
            //Finaliza la transaccion
            conexion.commit();
        } catch (SQLException e) {
            //Reversa la transaccion
            conexion.rollback();
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno del objeto
        return solicitud;
    }

    public Solicitud editarSolicitud(Solicitud solicitudAEditar) throws SQLException, DataException {
        //Creacion de la conexion a la base de datos
        Connection conexion = super.getConnection();
        //Definicion del procedimiento almacenado
        String sqlEditarSolicitud = "{Call editar_solicitud (?,?)}";
        //Propiedad de la conexion para evitar finalizar la transaccion automaticamente
        conexion.setAutoCommit(false);
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlEditarSolicitud);
            //Definicion de los parametros que recibe el procedimiento almacenado
            statement.setInt(1, solicitudAEditar.getId());
            statement.setBoolean(2, solicitudAEditar.isFavorito());
            //Ejecucion del procedimiento almacenado
            statement.executeUpdate();
            //Finaliza la transaccion
            conexion.commit();
        } catch (SQLException e) {
            //Reversa la transaccion
            conexion.rollback();
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
        return solicitudAEditar;
    }

    public void eliminarSolicitud(int idSolicitud) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlEliminarSolicitud = "{CALL eliminar_solicitud(?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        //Propiedad de la conexion para evitar finalizar la transaccion automaticamente
        conexion.setAutoCommit(false);
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlEliminarSolicitud);
            //Definicion del parametro que recibe el procedimiento almacenado
            statement.setInt(1, idSolicitud);
            //Ejecucion del procedimiento almacenado
            statement.executeUpdate();
            //Finaliza la transaccion
            conexion.commit();
        } catch (SQLException e) {
            //Reversa la transaccion
            conexion.rollback();
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
    }

    public LinkedList<Solicitud> buscarSolicitudes() throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlBuscarSolicitudes = "{CALL buscar_solicitudes}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        //Preparacion del procedimiento almacenado
        //Definicion de la lista de objetos a consultar
        LinkedList<Solicitud> solicitudes = new LinkedList<>();
        try {
            CallableStatement statement = conexion.prepareCall(sqlBuscarSolicitudes);
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Recorrido del resultado de busqueda
            while (resultSet.next()) {
                //Definicion de un objeto para asignarle cada atributo contenido en un resultado de busqueda
                Solicitud solicitudActual = new Solicitud();
                //Asignacion de atributos al objeto
                solicitudActual.setId(resultSet.getInt("id"));
                solicitudActual.getSolicitante().setId(resultSet.getInt("solicitante"));
                solicitudActual.getOferta().setId(resultSet.getInt("oferta"));
                solicitudActual.setFavorito(resultSet.getBoolean("favorito"));
                //Inclusion del objeto a la lista
                solicitudes.add(solicitudActual);
            }
        } catch (SQLException e) {
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno de la lista
        return solicitudes;
    }

    public LinkedList<Solicitud> buscarSolicitudesFavoritas(int idOferta) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlBuscarSolicitudesFavoritas = "{CALL buscar_solicitudes_favoritas(?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        //Definicion de la lista de objetos a consultar
        LinkedList<Solicitud> solicitudes = new LinkedList<>();
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlBuscarSolicitudesFavoritas);
            //Definicion del parametro que recibe el procedimiento almacenado
            statement.setInt(1, idOferta);
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Recorrido del resultado de busqueda
            while (resultSet.next()) {
                //Definicion de un objeto para asignarle cada atributo contenido en un resultado de busqueda
                Solicitud solicitudActual = new Solicitud();
                //Asignacion de atributos al objeto
                solicitudActual.setId(resultSet.getInt("id"));
                solicitudActual.getSolicitante().setId(resultSet.getInt("solicitante"));
                solicitudActual.getOferta().setId(resultSet.getInt("oferta"));
                solicitudActual.setFavorito(resultSet.getBoolean("favorito"));
                //Inclusion del objeto a la lista
                solicitudes.add(solicitudActual);
            }
        } catch (SQLException e) {
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno de la lista
        return solicitudes;
    }

    public LinkedList<Solicitud> buscarSolicitudesFiltradas(int idSolicitante, int idOferta) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlBuscarSolicitudesFiltradas = "{CALL buscar_solicitudes_filtradas(?,?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        //Definicion de la lista de objetos a consultar
        LinkedList<Solicitud> solicitudes = new LinkedList<>();
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlBuscarSolicitudesFiltradas);
            //Definicion de los parametros que recibe el procedimiento almacenado
            statement.setInt(1, idSolicitante);
            statement.setInt(2, idOferta);
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Recorrido del resultado de busqueda
            while (resultSet.next()) {
                //Definicion de un objeto para asignarle cada atributo contenido en un resultado de busqueda
                Solicitud solicitudActual = new Solicitud();
                //Asignacion de atributos al objeto
                solicitudActual.setId(resultSet.getInt("id"));
                solicitudActual.getSolicitante().setId(resultSet.getInt("solicitante"));
                solicitudActual.getOferta().setId(resultSet.getInt("oferta"));
                solicitudActual.setFavorito(resultSet.getBoolean("favorito"));
                //Inclusion del objeto a la lista
                solicitudes.add(solicitudActual);
            }
        } catch (SQLException e) {
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno de la lista
        return solicitudes;
    }

    public Solicitud buscarSolicitud(int idSolicitud) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlBuscarSolicitud = "{CALL buscar_solicitud(?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        //Definicion del objeto a consultar
        Solicitud solicitud = new Solicitud();
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlBuscarSolicitud);
            //Definicion del parametro que recibe el procedimiento almacenado
            statement.setInt(1, idSolicitud);
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Si hay resultado de busqueda
            if (resultSet.next()) {
                //Asignacion de atributos al objeto
                solicitud.setId(resultSet.getInt("id"));
                solicitud.getSolicitante().setId(resultSet.getInt("solicitante"));
                solicitud.getOferta().setId(resultSet.getInt("oferta"));
                solicitud.setFavorito(resultSet.getBoolean("favorito"));
            }
        } catch (SQLException e) {
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno del objeto
        return solicitud;
    }

    public LinkedList<Solicitud> buscarSolicitudesPorCategoria(int categoria, String puesto) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlSelect = "{CALL buscar_solicitudes_categoria(?,?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = super.getConnection();
        //Definicion de la lista de objetos a consultar
        LinkedList<Solicitud> solicitudes = new LinkedList<>();
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlSelect);
            //Definicion de los parametros que recibe el procedimiento almacenado
            statement.setString(1, puesto);
            statement.setInt(2, categoria);
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Recorrido del resultado de busqueda
            while (resultSet.next()) {
                //Definicion de un objeto para asignarle cada atributo contenido en un resultado de busqueda
                Solicitud solicitudActual = new Solicitud();
                //Asignacion de atributos al objeto
                solicitudActual.setId(resultSet.getInt("id"));
                solicitudActual.getSolicitante().setId(resultSet.getInt("solicitante"));
                solicitudActual.getOferta().setId(resultSet.getInt("oferta"));
                solicitudActual.setFavorito(resultSet.getBoolean("favorito"));
                //Inclusion del objeto a la lista
                solicitudes.add(solicitudActual);
            }
        } catch (SQLException e) {
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno de la lista
        return solicitudes;
    }
    
}
