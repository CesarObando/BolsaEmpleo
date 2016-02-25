package Data;

import Dominio.Solicitante;
import Exception.DataException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;

public class SolicitanteData extends BaseData {

    //Constructor vacio
    public SolicitanteData() {
    }

    public Solicitante insertarSolicitante(Solicitante solicitante) throws SQLException, DataException {
        //Creacion de la conexion a la base de datos
        Connection conexion = super.getConnection();
        //Definicion del procedimiento almacenado
        String sqlInsertarSolicitante = "{CALL insertar_solicitante (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        //Preparacion del procedimiento almacenado
        CallableStatement statement = conexion.prepareCall(sqlInsertarSolicitante);
        //Propiedad de la conexion para evitar finalizar la transaccion automaticamente
        conexion.setAutoCommit(false);
        try {
            //Definicion del parametro de salida
            statement.registerOutParameter(1, Types.INTEGER);
            //Definicion de los parametros que recibe el procedimiento almacenado
            statement.setString(2, solicitante.getCedula());
            statement.setString(3, solicitante.getNombre());
            statement.setString(4, solicitante.getApellidos());
            statement.setString(5, solicitante.getUsername());
            statement.setString(6, solicitante.getPassword());
            statement.setBytes(7, solicitante.getFoto());
            statement.setInt(8, solicitante.getEdad());
            statement.setString(9, solicitante.getSexo());
            statement.setString(10, solicitante.getEscolaridad());
            statement.setString(11, solicitante.getTitulos());
            statement.setInt(12, solicitante.getExperienciaLaboral());
            statement.setString(13, solicitante.getDetalleExperienciaLaboral());
            statement.setString(14, solicitante.getTelefonoFijo());
            statement.setString(15, solicitante.getTelefonoMovil());
            statement.setString(16, solicitante.getCorreo());
            statement.setString(17, solicitante.getIdiomas());
            //Ejecucion del procedimiento almacenado
            statement.executeUpdate();
            //Recuperacion del parametro de salida
            solicitante.setId(statement.getInt(1));
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
        return solicitante;
    }

    public Solicitante editarSolicitante(Solicitante solicitante) throws SQLException, DataException {
        //Creacion de la conexion a la base de datos
        Connection conexion = super.getConnection();
        //Definicion del procedimiento almacenado
        String sqlEditarSolicitante = "{CALL editar_solicitante (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        //Propiedad de la conexion para evitar finalizar la transaccion automaticamente
        conexion.setAutoCommit(false);
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlEditarSolicitante);
            //Definicion de los parametros que recibe el procedimiento almacenado
            statement.setInt(1, solicitante.getId());
            statement.setString(2, solicitante.getCedula());
            statement.setString(3, solicitante.getPassword());
            statement.setBytes(4, solicitante.getFoto());
            statement.setString(5, solicitante.getEscolaridad());
            statement.setString(6, solicitante.getTitulos());
            statement.setInt(7, solicitante.getExperienciaLaboral());
            statement.setString(8, solicitante.getDetalleExperienciaLaboral());
            statement.setString(9, solicitante.getTelefonoFijo());
            statement.setString(10, solicitante.getTelefonoMovil());
            statement.setString(11, solicitante.getCorreo());
            statement.setString(12, solicitante.getIdiomas());
            statement.setInt(13, solicitante.getEdad());
            statement.setString(14, solicitante.getNombre());
            statement.setString(15, solicitante.getApellidos());
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
        //Retorno del objeto
        return solicitante;
    }

    public void eliminarSolicitante(int idSolicitante) throws SQLException, DataException {
        //Creacion de la conexion a la base de datos
        Connection conexion = super.getConnection();
        //Definicion del procedimiento almacenado
        String sqlEliminarSolicitante = "{CALL eliminar_solicitante (?)}";
        //Propiedad de la conexion para evitar finalizar la transaccion automaticamente
        conexion.setAutoCommit(false);
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlEliminarSolicitante);
            //Definicion del parametro que recibe el procedimiento almacenado
            statement.setInt(1, idSolicitante);
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

    public LinkedList<Solicitante> buscarSolicitantes() throws SQLException, DataException {
        //Definicion de la lista de objetos a consultar
        LinkedList<Solicitante> solicitantes = new LinkedList<>();
        //Creacion de la conexion a la base de datos
        Connection conexion = super.getConnection();
        //Definicion del procedimiento almacenado
        String sqlBuscarSolicitantes = "{CALL buscar_solicitantes}";
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlBuscarSolicitantes);
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Recorrido del resultado de busqueda
            while (resultSet.next()) {
                //Definicion de un objeto para asignarle cada atributo contenido en un resultado de busqueda
                Solicitante solicitanteActual = new Solicitante();
                //Asignacion de atributos al objeto
                solicitanteActual.setId(resultSet.getInt("id"));
                solicitanteActual.setCedula(resultSet.getString("cedula"));
                solicitanteActual.setNombre(resultSet.getString("nombre"));
                solicitanteActual.setApellidos(resultSet.getString("apellidos"));
                solicitanteActual.setUsername(resultSet.getString("username"));
                solicitanteActual.setPassword(resultSet.getString("passwd"));
                solicitanteActual.setEdad(resultSet.getInt("edad"));
                solicitanteActual.setEscolaridad(resultSet.getString("escolaridad"));
                solicitanteActual.setExperienciaLaboral(resultSet.getInt("años_experiencia_laboral"));
                solicitanteActual.setFoto(resultSet.getBytes("foto"));
                solicitanteActual.setIdiomas(resultSet.getString("idiomas"));
                solicitanteActual.setTelefonoFijo(resultSet.getString("telefono_fijo"));
                solicitanteActual.setTelefonoMovil(resultSet.getString("telefono_movil"));
                solicitanteActual.setTitulos(resultSet.getString("titulos"));
                solicitanteActual.setSexo(resultSet.getString("sexo"));
                solicitanteActual.setCorreo(resultSet.getString("correo"));
                solicitanteActual.setDetalleExperienciaLaboral(resultSet.getString("detalle_experiencia_laboral"));
                solicitanteActual.setUltimaActualizacion(resultSet.getDate("ultima_actualizacion"));
                //Inclusion del objeto a la lista
                solicitantes.add(solicitanteActual);
            }
        } catch (SQLException e) {
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno de la lista
        return solicitantes;
    }

    public LinkedList<Solicitante> buscarSolicitantesFiltrados(String cedula, String nombre, String apellidos) throws SQLException, DataException {
        //Definicion de la lista de objetos a consultar
        LinkedList<Solicitante> solicitantes = new LinkedList<>();
        //Creacion de la conexion a la base de datos
        Connection conexion = super.getConnection();
        //Definicion del procedimiento almacenado
        String sqlBuscarSolicitantesFiltrados = "{CALL buscar_solicitantes_filtrados (?, ?, ?)}";
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlBuscarSolicitantesFiltrados);
            //Definicion de los parametros que recibe el procedimiento almacenado
            statement.setString(1, cedula);
            statement.setString(2, nombre);
            statement.setString(3, apellidos);
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Recorrido del resultado de busqueda
            while (resultSet.next()) {
                //Definicion de un objeto para asignarle cada atributo contenido en un resultado de busqueda
                Solicitante solicitante = new Solicitante();
                //Asignacion de atributos al objeto
                solicitante.setId(resultSet.getInt("id"));
                solicitante.setCedula(resultSet.getString("cedula"));
                solicitante.setNombre(resultSet.getString("nombre"));
                solicitante.setApellidos(resultSet.getString("apellidos"));
                solicitante.setUsername(resultSet.getString("username"));
                solicitante.setPassword(resultSet.getString("passwd"));
                solicitante.setEdad(resultSet.getInt("edad"));
                solicitante.setEscolaridad(resultSet.getString("escolaridad"));
                solicitante.setExperienciaLaboral(resultSet.getInt("años_experiencia_laboral"));
                solicitante.setFoto(resultSet.getBytes("foto"));
                solicitante.setIdiomas(resultSet.getString("idiomas"));
                solicitante.setTelefonoFijo(resultSet.getString("telefono_fijo"));
                solicitante.setTelefonoMovil(resultSet.getString("telefono_movil"));
                solicitante.setTitulos(resultSet.getString("titulos"));
                solicitante.setSexo(resultSet.getString("sexo"));
                solicitante.setCorreo(resultSet.getString("correo"));
                solicitante.setDetalleExperienciaLaboral(resultSet.getString("detalle_experiencia_laboral"));
                solicitante.setUltimaActualizacion(resultSet.getDate("ultima_actualizacion"));
                //Inclusion del objeto a la lista
                solicitantes.add(solicitante);
            }
        } catch (SQLException e) {
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno de la lista
        return solicitantes;
    }

    public Solicitante buscarSolicitante(int idSolicitante) throws SQLException, DataException {
        //Creacion de la conexion a la base de datos
        Connection conexion = super.getConnection();
        //Definicion del procedimiento almacenado
        String sqlBuscarSolicitante = "{CALL buscar_solicitante (?)}";
        //Definicion del objeto a consultar
        Solicitante solicitante = new Solicitante();
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlBuscarSolicitante);
            //Definicion del parametro que recibe el procedimiento almacenado
            statement.setInt(1, idSolicitante);
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Si hay resultado de busqueda
            if (resultSet.next()) {
                //Asignacion de atributos al objeto
                solicitante.setId(resultSet.getInt("id"));
                solicitante.setCedula(resultSet.getString("cedula"));
                solicitante.setNombre(resultSet.getString("nombre"));
                solicitante.setApellidos(resultSet.getString("apellidos"));
                solicitante.setUsername(resultSet.getString("username"));
                solicitante.setPassword(resultSet.getString("passwd"));
                solicitante.setEdad(resultSet.getInt("edad"));
                solicitante.setEscolaridad(resultSet.getString("escolaridad"));
                solicitante.setExperienciaLaboral(resultSet.getInt("años_experiencia_laboral"));
                solicitante.setFoto(resultSet.getBytes("foto"));
                solicitante.setIdiomas(resultSet.getString("idiomas"));
                solicitante.setTelefonoFijo(resultSet.getString("telefono_fijo"));
                solicitante.setTelefonoMovil(resultSet.getString("telefono_movil"));
                solicitante.setTitulos(resultSet.getString("titulos"));
                solicitante.setSexo(resultSet.getString("sexo"));
                solicitante.setCorreo(resultSet.getString("correo"));
                solicitante.setDetalleExperienciaLaboral(resultSet.getString("detalle_experiencia_laboral"));
                solicitante.setUltimaActualizacion(resultSet.getDate("ultima_actualizacion"));
            }
        } catch (SQLException e) {
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno del objeto
        return solicitante;
    }

    public Solicitante buscarSolicitantePorNombreUsuario(String username) throws SQLException, DataException {
        //Creacion de la conexion a la base de datos
        Connection conexion = super.getConnection();
        //Definicion del procedimiento almacenado
        String sqlBuscarSolicitantePorNombreUsuario = "{CALL buscar_solicitante_por_nombre_usuario (?)}";
        //Definicion del objeto a consultar
        Solicitante solicitante = new Solicitante();
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlBuscarSolicitantePorNombreUsuario);
            //Definicion del parametro que recibe el procedimiento almacenado
            statement.setString(1, username);
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Si hay resultado de busqueda
            if (resultSet.next()) {
                //Asignacion de atributos al objeto
                solicitante.setId(resultSet.getInt("id"));
                solicitante.setCedula(resultSet.getString("cedula"));
                solicitante.setNombre(resultSet.getString("nombre"));
                solicitante.setApellidos(resultSet.getString("apellidos"));
                solicitante.setUsername(resultSet.getString("username"));
                solicitante.setPassword(resultSet.getString("passwd"));
                solicitante.setEdad(resultSet.getInt("edad"));
                solicitante.setEscolaridad(resultSet.getString("escolaridad"));
                solicitante.setExperienciaLaboral(resultSet.getInt("años_experiencia_laboral"));
                solicitante.setFoto(resultSet.getBytes("foto"));
                solicitante.setIdiomas(resultSet.getString("idiomas"));
                solicitante.setTelefonoFijo(resultSet.getString("telefono_fijo"));
                solicitante.setTelefonoMovil(resultSet.getString("telefono_movil"));
                solicitante.setTitulos(resultSet.getString("titulos"));
                solicitante.setSexo(resultSet.getString("sexo"));
                solicitante.setCorreo(resultSet.getString("correo"));
                solicitante.setDetalleExperienciaLaboral(resultSet.getString("detalle_experiencia_laboral"));
                solicitante.setUltimaActualizacion(resultSet.getDate("ultima_actualizacion"));
            }
        } catch (SQLException e) {
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno del objeto
        return solicitante;
    }

    public Solicitante iniciarSesion(String username, String password) throws SQLException, DataException {
        //Definicion del objeto a iniciar sesion
        Solicitante solicitante = new Solicitante();
        //Creacion de la conexion a la base de datos
        Connection conexion = super.getConnection();
        //Definicion del procedimiento almacenado
        String sqlIniciarSesion = "{CALL iniciar_sesion_solicitante (?, ?)}";
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
                solicitante.setId(resultSet.getInt("id"));
                solicitante.setCedula(resultSet.getString("cedula"));
                solicitante.setNombre(resultSet.getString("nombre"));
                solicitante.setApellidos(resultSet.getString("apellidos"));
                solicitante.setUsername(username);
                solicitante.setPassword(password);
                solicitante.setEdad(resultSet.getInt("edad"));
                solicitante.setEscolaridad(resultSet.getString("escolaridad"));
                solicitante.setExperienciaLaboral(resultSet.getInt("años_experiencia_laboral"));
                solicitante.setFoto(resultSet.getBytes("foto"));
                solicitante.setIdiomas(resultSet.getString("idiomas"));
                solicitante.setTelefonoFijo(resultSet.getString("telefono_fijo"));
                solicitante.setTelefonoMovil(resultSet.getString("telefono_movil"));
                solicitante.setTitulos(resultSet.getString("titulos"));
                solicitante.setSexo(resultSet.getString("sexo"));
                solicitante.setCorreo(resultSet.getString("correo"));
                solicitante.setDetalleExperienciaLaboral(resultSet.getString("detalle_experiencia_laboral"));
            }
        } catch (Exception e) {
            //Si no se recupera un resultado
            if (e.getMessage().equalsIgnoreCase("The statement did not return a result set.")) {
                //Retorno del objeto
                return solicitante;
            }
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno del objeto
        return solicitante;
    }
}
