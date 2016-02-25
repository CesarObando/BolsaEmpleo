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

    //Constructor vacio
    public ServicioData() {
    }

    public Servicio insertarServicio(Servicio servicio) throws SQLException, DataException {
        //Creacion de la conexion a la base de datos
        Connection conexion = super.getConnection();
        //Definicion del procedimiento almacenado
        String sqlInsertarServicio = "{CALL insertar_servicio(?,?,?,?,?,?,?,?)}";
        //Preparacion del procedimiento almacenado
        CallableStatement statement = conexion.prepareCall(sqlInsertarServicio);
        //Propiedad de la conexion para evitar finalizar la transaccion automaticamente
        conexion.setAutoCommit(false);
        try {
            //Definicion del parametro de salida
            statement.registerOutParameter(1, Types.INTEGER);
            //Definicion de los parametros que recibe el procedimiento almacenado
            statement.setString(2, servicio.getTitulo());
            statement.setString(3, servicio.getDescripcion());
            statement.setInt(4, servicio.getSolicitante().getId());
            statement.setInt(5, servicio.getCategoria().getId());
            statement.setString(6, servicio.getProvincia());
            statement.setString(7, servicio.getCanton());
            statement.setBytes(8, servicio.getFoto());
            //Ejecucion del procedimiento almacenado
            statement.executeUpdate();
            //Recuperacion del parametro de salida
            servicio.setId(statement.getInt(1));
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
        return servicio;
    }

    public void editarServicio(Servicio servicio) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlEditarServicio = "{CALL editar_servicio(?,?,?,?,?,?,?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        //Propiedad de la conexion para evitar finalizar la transaccion automaticamente
        conexion.setAutoCommit(false);
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlEditarServicio);
            //Definicion de los parametros que recibe el procedimiento almacenado
            statement.setInt(1, servicio.getId());
            statement.setString(2, servicio.getTitulo());
            statement.setString(3, servicio.getDescripcion());
            statement.setInt(4, servicio.getCategoria().getId());
            statement.setString(5, servicio.getProvincia());
            statement.setString(6, servicio.getCanton());
            statement.setBytes(7, servicio.getFoto());
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

    public void eliminarServicio(int idServicio) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlEliminarServicio = "{CALL eliminar_servicio(?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        //Propiedad de la conexion para evitar finalizar la transaccion automaticamente
        conexion.setAutoCommit(false);
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlEliminarServicio);
            //Definicion del parametro que recibe el procedimiento almacenado
            statement.setInt(1, idServicio);
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

    public LinkedList<Servicio> buscarServicios() throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlBuscarServicios = "buscar_servicios";
        //Creacion de la conexion a la base de datos
        Connection conexion = super.getConnection();
        //Definicion de la lista de objetos a consultar
        LinkedList<Servicio> servicios = new LinkedList<>();
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlBuscarServicios);
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Recorrido del resultado de busqueda
            while (resultSet.next()) {
                //Definicion de un objeto para asignarle cada atributo contenido en un resultado de busqueda
                Servicio servicioActual = new Servicio();
                //Asignacion de atributos al objeto
                servicioActual.setId(resultSet.getInt("id"));
                servicioActual.setTitulo(resultSet.getString("titulo"));
                servicioActual.setDescripcion(resultSet.getString("descripcion"));
                servicioActual.getSolicitante().setId(resultSet.getInt("solicitante"));
                servicioActual.getCategoria().setId(resultSet.getInt("categoria"));
                servicioActual.setProvincia(resultSet.getString("provincia"));
                servicioActual.setCanton(resultSet.getString("canton"));
                servicioActual.setFoto(resultSet.getBytes("foto"));
                //Inclusion del objeto a la lista
                servicios.add(servicioActual);
            }
        } catch (SQLException e) {
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno de la lista
        return servicios;
    }

    public LinkedList<Servicio> buscarServiciosFiltrados(int categoria, String titulo, String provincia, String canton) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlBuscarServiciosFiltrados = "{CALL buscar_servicios_filtrados(?,?,?,?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = super.getConnection();
        //Definicion de la lista de objetos a consultar
        LinkedList<Servicio> servicios = new LinkedList<>();
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlBuscarServiciosFiltrados);
            //Definicion de los parametros que recibe el procedimiento almacenado
            statement.setString(1, titulo);
            statement.setInt(2, categoria);
            statement.setString(3, provincia);
            statement.setString(4, canton);
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Recorrido del resultado de busqueda
            while (resultSet.next()) {
                //Definicion de un objeto para asignarle cada atributo contenido en un resultado de busqueda
                Servicio servicioActual = new Servicio();
                //Asignacion de atributos al objeto
                servicioActual.setId(resultSet.getInt("id"));
                servicioActual.setTitulo(resultSet.getString("titulo"));
                servicioActual.setDescripcion(resultSet.getString("descripcion"));
                servicioActual.getSolicitante().setId(resultSet.getInt("solicitante"));
                servicioActual.getCategoria().setId(resultSet.getInt("categoria"));
                servicioActual.setProvincia(resultSet.getString("provincia"));
                servicioActual.setCanton(resultSet.getString("canton"));
                servicioActual.setFoto(resultSet.getBytes("foto"));
                //Inclusion del objeto a la lista
                servicios.add(servicioActual);
            }
        } catch (SQLException e) {
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno de la lista
        return servicios;
    }

    public LinkedList<Servicio> buscarServiciosPorSolicitante(int categoria, String titulo, int solicitante, String provincia, String canton) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlBuscarServiciosPorSolicitante = "{CALL buscar_servicios_por_solicitante(?,?,?,?,?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = super.getConnection();
        //Definicion de la lista de objetos a consultar
        LinkedList<Servicio> servicios = new LinkedList<>();
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlBuscarServiciosPorSolicitante);
            //Definicion de los parametros que recibe el procedimiento almacenado
            statement.setString(1, titulo);
            statement.setInt(2, categoria);
            statement.setString(3, provincia);
            statement.setString(4, canton);
            statement.setInt(5, solicitante);
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Recorrido del resultado de busqueda
            while (resultSet.next()) {
                //Definicion de un objeto para asignarle cada atributo contenido en un resultado de busqueda
                Servicio servicioActual = new Servicio();
                //Asignacion de atributos al objeto
                servicioActual.setId(resultSet.getInt("id"));
                servicioActual.setTitulo(resultSet.getString("titulo"));
                servicioActual.setDescripcion(resultSet.getString("descripcion"));
                servicioActual.getSolicitante().setId(resultSet.getInt("solicitante"));
                servicioActual.getCategoria().setId(resultSet.getInt("categoria"));
                servicioActual.setProvincia(resultSet.getString("provincia"));
                servicioActual.setCanton(resultSet.getString("canton"));
                servicioActual.setFoto(resultSet.getBytes("foto"));
                //Inclusion del objeto a la lista
                servicios.add(servicioActual);
            }
        } catch (SQLException e) {
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno de la lista
        return servicios;
    }

    public Servicio buscarServicio(int idServicio) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlBuscarServicio = "{CALL buscar_servicio(?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        //Definicion del objeto a consultar
        Servicio servicio = new Servicio();
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlBuscarServicio);
            //Definicion del parametro que recibe el procedimiento almacenado
            statement.setInt(1, idServicio);
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Si hay resultado de busqueda
            if (resultSet.next()) {
                //Asignacion de atributos al objeto
                servicio.setId(resultSet.getInt("id"));
                servicio.setTitulo(resultSet.getString("titulo"));
                servicio.setDescripcion(resultSet.getString("descripcion"));
                servicio.getSolicitante().setId(resultSet.getInt("solicitante"));
                servicio.getCategoria().setId(resultSet.getInt("categoria"));
                servicio.setProvincia(resultSet.getString("provincia"));
                servicio.setCanton(resultSet.getString("canton"));
                servicio.setFoto(resultSet.getBytes("foto"));
            }
        } catch (SQLException e) {
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno del objeto
        return servicio;
    }

}
