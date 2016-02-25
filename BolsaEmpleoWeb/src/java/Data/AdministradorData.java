package Data;

import Dominio.Administrador;
import Exception.DataException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;

public class AdministradorData extends BaseData {

    //Constructor vacio
    public AdministradorData() {
    }

    public Administrador insertarAdministrador(Administrador administrador) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlInsertarAdministrador = "{CALL insertar_administrador(?,?,?,?,?,?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        //Preparacion del procedimiento almacenado
        CallableStatement statement = conexion.prepareCall(sqlInsertarAdministrador);
        //Propiedad de la conexion para evitar finalizar la transaccion automaticamente
        conexion.setAutoCommit(false);
        try {
            //Definicion del parametro de salida
            statement.registerOutParameter(1, Types.INTEGER);
            //Definicion de los parametros que recibe el procedimiento almacenado
            statement.setString(2, administrador.getCedula());
            statement.setString(3, administrador.getNombre());
            statement.setString(4, administrador.getApellidos());
            statement.setString(5, administrador.getUsername());
            statement.setString(6, administrador.getPassword());
            //Ejecucion del procedimiento almacenado
            statement.executeUpdate();
            //Recuperacion del parametro de salida
            administrador.setId(statement.getInt(1));
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
        return administrador;
    }

    public void editarAdministrador(Administrador administrador) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlEditarAdministrador = "{CALL editar_administrador(?,?,?,?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        //Propiedad de la conexion para evitar finalizar la transaccion automaticamente
        conexion.setAutoCommit(false);
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlEditarAdministrador);
            //Definicion de los parametros que recibe el procedimiento almacenado
            statement.setInt(1, administrador.getId());
            statement.setString(2, administrador.getNombre());
            statement.setString(3, administrador.getApellidos());
            statement.setString(4, administrador.getPassword());
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

    public void eliminarAdministrador(int idAdministrador) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlEliminarAdministrador = "{CALL eliminar_administrador(?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        //Propiedad de la conexion para evitar finalizar la transaccion automaticamente
        conexion.setAutoCommit(false);
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlEliminarAdministrador);
            //Definicion del parametro que recibe el procedimiento almacenado
            statement.setInt(1, idAdministrador);
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

    public LinkedList<Administrador> buscarAdministradores() throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlBuscarAdministradores = "{CALL buscar_administradores}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        //Definicion de la lista de objetos a consultar
        LinkedList<Administrador> administradores = new LinkedList<>();
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlBuscarAdministradores);
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Recorrido del resultado de busqueda
            while (resultSet.next()) {
                //Definicion de un objeto para asignarle cada atributo contenido en un resultado de busqueda
                Administrador administradorActual = new Administrador();
                //Asignacion de atributos al objeto
                administradorActual.setId(resultSet.getInt("id"));
                administradorActual.setCedula(resultSet.getString("cedula"));
                administradorActual.setNombre(resultSet.getString("nombre"));
                administradorActual.setApellidos(resultSet.getString("apellidos"));
                administradorActual.setUsername(resultSet.getString("username"));
                administradorActual.setPassword(resultSet.getString("passwd"));
                //Inclusion del objeto a la lista
                administradores.add(administradorActual);
            }
        } catch (SQLException e) {
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno de la lista
        return administradores;
    }

    public LinkedList<Administrador> buscarAdministradoresFiltrados(String cedula, String nombre, String apellidos) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlBuscarAdministradoresFiltrados = "{CALL buscar_administradores_filtrados(?,?,?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        //Definicion de la lista de objetos a consultar
        LinkedList<Administrador> administradores = new LinkedList<>();
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlBuscarAdministradoresFiltrados);
            //Definicion de los parametros que recibe el procedimiento almacenado
            statement.setString(1, cedula);
            statement.setString(2, nombre);
            statement.setString(3, apellidos);
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Recorrido del resultado de busqueda
            while (resultSet.next()) {
                //Definicion de un objeto para asignarle cada atributo contenido en un resultado de busqueda
                Administrador administradorActual = new Administrador();
                //Asignacion de atributos al objeto
                administradorActual.setId(resultSet.getInt("id"));
                administradorActual.setCedula(resultSet.getString("cedula"));
                administradorActual.setNombre(resultSet.getString("nombre"));
                administradorActual.setApellidos(resultSet.getString("apellidos"));
                administradorActual.setUsername(resultSet.getString("username"));
                administradorActual.setPassword(resultSet.getString("passwd"));
                //Inclusion del objeto a la lista
                administradores.add(administradorActual);
            }
        } catch (SQLException e) {
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno de la lista
        return administradores;
    }

    public Administrador buscarAdministrador(int idAdministrador) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlBuscarAdministrador = "{CALL buscar_administrador(?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        //Definicion del objeto a consultar
        Administrador administrador = new Administrador();
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlBuscarAdministrador);
            //Definicion del parametro que recibe el procedimiento almacenado
            statement.setInt(1, idAdministrador);
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Si hay resultado de busqueda
            if (resultSet.next()) {
                //Asignacion de atributos al objeto
                administrador.setId(resultSet.getInt("id"));
                administrador.setCedula(resultSet.getString("cedula"));
                administrador.setNombre(resultSet.getString("nombre"));
                administrador.setApellidos(resultSet.getString("apellidos"));
                administrador.setUsername(resultSet.getString("username"));
                administrador.setPassword(resultSet.getString("passwd"));
            }
        } catch (SQLException e) {
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno del objeto
        return administrador;
    }

    public Administrador iniciarSesion(String username, String password) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlIniciarSesion = "{CALL iniciar_sesion_administrador(?,?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        //Definicion del objeto a iniciar sesion
        Administrador administrador = new Administrador();
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlIniciarSesion);
            //Definicion de los parametros que recibe el procedimiento almacenado
            statement.setString(1, username);
            statement.setString(2, password);
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Si hay resultado de busqueda
            if (resultSet.next()) {
                //Asignacion de atributos al objeto
                administrador.setId(resultSet.getInt("id"));
                administrador.setCedula(resultSet.getString("cedula"));
                administrador.setNombre(resultSet.getString("nombre"));
                administrador.setApellidos(resultSet.getString("apellidos"));
                administrador.setUsername(resultSet.getString("username"));
                administrador.setPassword(resultSet.getString("passwd"));
            }
        } catch (SQLException e) {
            //Si no se recupera un resultado
            if (e.getMessage().equalsIgnoreCase("The statement did not return a result set.")) {
                //Retorno del objeto
                return administrador;
            }
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno del objeto
        return administrador;
    }

    public int getNumeroVisitas() throws SQLException {
        //Definicion del procedimiento almacenado
        String sqlNumeroVisitas = "{CALL getNumeroVisitas}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        //Definicion de una variable para asignarle el resultado
        int numeroVisitas = 0;
        //Preparacion del procedimiento almacenado
        CallableStatement statement = conexion.prepareCall(sqlNumeroVisitas);
        //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
        ResultSet resultSet = statement.executeQuery();
        //Si hay resultado de busqueda
        if (resultSet.next()) {
            //Asignacion del resultado a la variable
            numeroVisitas = resultSet.getInt(1);
        }
        //Retorno de la variable
        return numeroVisitas;
    }

}
