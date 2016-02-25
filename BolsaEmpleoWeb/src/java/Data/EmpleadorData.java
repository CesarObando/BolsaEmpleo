package Data;

import Dominio.Empleador;
import Exception.DataException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;

public class EmpleadorData extends BaseData {

    //Constructor vacio
    public EmpleadorData() {
    }
    
    public Empleador insertarEmpleador(Empleador empleador) throws SQLException, DataException {
        //Creacion de la conexion a la base de datos
        Connection conexion = super.getConnection();
        //Definicion del procedimiento almacenado
        String sqlInsertarEmpleador = "{CALL insertar_empleador(?,?,?,?,?,?,?,?,?,?,?,?)}";
        //Preparacion del procedimiento almacenado
        CallableStatement statement = conexion.prepareCall(sqlInsertarEmpleador);
        //Propiedad de la conexion para evitar finalizar la transaccion automaticamente
        conexion.setAutoCommit(false);
        try {
            //Definicion del parametro de salida
            statement.registerOutParameter(1, Types.INTEGER);
            //Definicion de los parametros que recibe el procedimiento almacenado
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
            statement.setString(12, empleador.getPassword());
            //Ejecucion del procedimiento almacenado
            statement.executeUpdate();
            //Recuperacion del parametro de salida
            empleador.setId(statement.getInt(1));
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
        return empleador;
    }

    public void editarEmpleador(Empleador empleador) throws SQLException, DataException {
        //Creacion de la conexion a la base de datos
        Connection conexion = super.getConnection();
        //Definicion del procedimiento almacenado
        String sqlEditarEmpleador = "{CALL editar_empleador(?,?,?,?,?,?,?,?,?,?,?)}";
        //Preparacion del procedimiento almacenado
        CallableStatement statement = conexion.prepareCall(sqlEditarEmpleador);
        //Propiedad de la conexion para evitar finalizar la transaccion automaticamente
        conexion.setAutoCommit(false);
        try {
            //Definicion de los parametros que recibe el procedimiento almacenado
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
            statement.setString(11, empleador.getPassword());
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

    public void eliminarEmpleador(int idEmpleador) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlEliminarEmpleador = "{CALL eliminar_empleador(?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        //Propiedad de la conexion para evitar finalizar la transaccion automaticamente
        conexion.setAutoCommit(false);
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlEliminarEmpleador);
            //Definicion del parametro que recibe el procedimiento almacenado
            statement.setInt(1, idEmpleador);
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

    public LinkedList<Empleador> buscarEmpleadores() throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlBuscarEmpleadores = "{CALL buscar_empleadores}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        //Definicion de la lista de objetos a consultar
        LinkedList<Empleador> empleadores = new LinkedList<>();
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlBuscarEmpleadores);
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Recorrido del resultado de busqueda
            while (resultSet.next()) {
                //Definicion de un objeto para asignarle cada atributo contenido en un resultado de busqueda
                Empleador empleadorActual = new Empleador();
                //Asignacion de atributos al objeto
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
                empleadorActual.setPassword(resultSet.getString("passwd"));
                empleadorActual.setUltimaActualizacion(resultSet.getDate("ultima_actualizacion"));
                //Inclusion del objeto a la lista
                empleadores.add(empleadorActual);
            }
        } catch (SQLException e) {
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno de la lista
        return empleadores;
    }

    public LinkedList<Empleador> buscarEmpleadoresFiltrados(String cedula, String nombre, String apellidos) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlBuscarEmpleadoresFiltrados = "{CALL buscar_empleadores_filtrados (?,?,?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        //Definicion de la lista de objetos a consultar
        LinkedList<Empleador> empleadores = new LinkedList<>();
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlBuscarEmpleadoresFiltrados);
            //Definicion de los parametros que recibe el procedimiento almacenado
            statement.setString(1, cedula);
            statement.setString(2, nombre);
            statement.setString(3, apellidos);
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Recorrido del resultado de busqueda
            while (resultSet.next()) {
                //Definicion de un objeto para asignarle cada atributo contenido en un resultado de busqueda
                Empleador empleadorActual = new Empleador();
                //Asignacion de atributos al objeto
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
                empleadorActual.setPassword(resultSet.getString("passwd"));
                empleadorActual.setUltimaActualizacion(resultSet.getDate("ultima_actualizacion"));
                //Inclusion del objeto a la lista
                empleadores.add(empleadorActual);
            }
        } catch (SQLException e) {
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno de la lista
        return empleadores;
    }

    public Empleador buscarEmpleador(int idEmpleador) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlBuscarEmpleador = "{CALL buscar_empleador (?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        //Definicion del objeto a consultar
        Empleador empleador = new Empleador();
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlBuscarEmpleador);
            //Definicion del parametro que recibe el procedimiento almacenado
            statement.setInt(1, idEmpleador);
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Si hay resultado de busqueda
            if (resultSet.next()) {
                //Asignacion de atributos al objeto
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
                empleador.setPassword(resultSet.getString("passwd"));
                empleador.setUltimaActualizacion(resultSet.getDate("ultima_actualizacion"));
            }
        } catch (SQLException e) {
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno del objeto
        return empleador;
    }

    public Empleador buscarEmpleadorPorNombreUsuario(String username) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlBuscarEmpleadorPorNombreUsuario = "{CALL buscar_empleador_por_nombre_usuario (?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        //Definicion del objeto a consultar
        Empleador empleador = new Empleador();
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlBuscarEmpleadorPorNombreUsuario);
            //Definicion del parametro que recibe el procedimiento almacenado
            statement.setString(1, username);
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Si hay resultado de busqueda
            if (resultSet.next()) {
                //Asignacion de atributos al objeto
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
                empleador.setPassword(resultSet.getString("passwd"));
                empleador.setUltimaActualizacion(resultSet.getDate("ultima_actualizacion"));
            }
        } catch (SQLException e) {
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno del objeto
        return empleador;
    }

    public Empleador iniciarSesion(String username, String password) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlIniciarSesion = "{CALL iniciar_sesion_empleador(?,?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = super.getConnection();
        //Definicion del objeto a iniciar sesion
        Empleador empleador = new Empleador();
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
                empleador.setPassword(resultSet.getString("passwd"));
            }
        } catch (SQLException e) {
            //Si no se recupera un resultado
            if (e.getMessage().equalsIgnoreCase("The statement did not return a result set.")) {
                //Retorno del objeto
                return empleador;
            }
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno del objeto
        return empleador;
    }
    
}
