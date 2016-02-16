/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Dominio.Solicitante;
import Exception.DataException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;

/**
 *
 * @author Cesar
 */
public class SolicitanteData extends BaseData {

    public SolicitanteData() {
    }

    public Solicitante insertarSolicitante(Solicitante solicitanteAInsertar) throws SQLException, DataException {
        Connection conexion = super.getConnection();
        String sqlInsert = "{CALL insertar_solicitante (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        CallableStatement statement = conexion.prepareCall(sqlInsert);
        try {
            statement.registerOutParameter(1, Types.INTEGER);
            statement.setString(2, solicitanteAInsertar.getCedula());
            statement.setString(3, solicitanteAInsertar.getNombre());
            statement.setString(4, solicitanteAInsertar.getApellidos());
            statement.setString(5, solicitanteAInsertar.getUsername());
            statement.setString(6, solicitanteAInsertar.getPassword());
            statement.setBytes(7, solicitanteAInsertar.getFoto());
            statement.setInt(8, solicitanteAInsertar.getEdad());
            statement.setString(9, solicitanteAInsertar.getSexo());
            statement.setString(10, solicitanteAInsertar.getEscolaridad());
            statement.setString(11, solicitanteAInsertar.getTitulos());
            statement.setInt(12, solicitanteAInsertar.getExperienciaLaboral());
            statement.setString(13, solicitanteAInsertar.getDetalleExperienciaLaboral());
            statement.setString(14, solicitanteAInsertar.getTelefonoFijo());
            statement.setString(15, solicitanteAInsertar.getTelefonoMovil());
            statement.setString(16, solicitanteAInsertar.getCorreo());
            statement.setString(17, solicitanteAInsertar.getIdiomas());
            statement.executeUpdate();
            solicitanteAInsertar.setId(statement.getInt(1));
            conexion.commit();
        } catch (Exception e) {
            conexion.rollback();
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
        return solicitanteAInsertar;
    }

    public Solicitante editarSolicitante(Solicitante solicitanteAEditar) throws SQLException, DataException {
        Connection conexion = super.getConnection();
        String sqlInsert = "{CALL editar_solicitante (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try {
            CallableStatement statement = conexion.prepareCall(sqlInsert);
            statement.setInt(1, solicitanteAEditar.getId());
            statement.setString(2, solicitanteAEditar.getCedula());
            statement.setString(3, solicitanteAEditar.getPassword());
            statement.setBytes(4, solicitanteAEditar.getFoto());
            statement.setString(5, solicitanteAEditar.getEscolaridad());
            statement.setString(6, solicitanteAEditar.getTitulos());
            statement.setInt(7, solicitanteAEditar.getExperienciaLaboral());
            statement.setString(8, solicitanteAEditar.getDetalleExperienciaLaboral());
            statement.setString(9, solicitanteAEditar.getTelefonoFijo());
            statement.setString(10, solicitanteAEditar.getTelefonoMovil());
            statement.setString(11, solicitanteAEditar.getCorreo());
            statement.setString(12, solicitanteAEditar.getIdiomas());
            statement.setInt(13, solicitanteAEditar.getEdad());
            statement.setString(14, solicitanteAEditar.getNombre());
            statement.setString(15, solicitanteAEditar.getApellidos());
            statement.executeUpdate();
            conexion.commit();
        } catch (Exception e) {
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
        return solicitanteAEditar;
    }

    public void eliminarSolicitante(int id) throws SQLException, DataException {
        Connection conexion = super.getConnection();
        String sqlEliminar = "{CALL eliminar_solicitante (?)}";
        try {
            CallableStatement statement = conexion.prepareCall(sqlEliminar);
            statement.setInt(1, id);
            statement.executeUpdate();
            conexion.commit();
        } catch (Exception e) {
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
    }

    public LinkedList<Solicitante> buscarSolicitantes() throws SQLException, DataException {
        LinkedList<Solicitante> solicitantes = new LinkedList<Solicitante>();
        Connection conexion = super.getConnection();
        String sqlBuscar = "{CALL buscar_solicitantes}";
        try {
            CallableStatement statement = conexion.prepareCall(sqlBuscar);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Solicitante solicitante = new Solicitante();
                solicitante.setId(rs.getInt("id"));
                solicitante.setCedula(rs.getString("cedula"));
                solicitante.setNombre(rs.getString("nombre"));
                solicitante.setApellidos(rs.getString("apellidos"));
                solicitante.setUsername(rs.getString("username"));
                solicitante.setPassword(rs.getString("passwd"));
                solicitante.setEdad(rs.getInt("edad"));
                solicitante.setEscolaridad(rs.getString("escolaridad"));
                solicitante.setExperienciaLaboral(rs.getInt("años_experiencia_laboral"));
                solicitante.setFoto(rs.getBytes("foto"));
                solicitante.setIdiomas(rs.getString("idiomas"));
                solicitante.setTelefonoFijo(rs.getString("telefono_fijo"));
                solicitante.setTelefonoMovil(rs.getString("telefono_movil"));
                solicitante.setTitulos(rs.getString("titulos"));
                solicitante.setSexo(rs.getString("sexo"));
                solicitante.setCorreo(rs.getString("correo"));
                solicitante.setDetalleExperienciaLaboral(rs.getString("detalle_experiencia_laboral"));
                solicitante.setUltimaActualizacion(rs.getDate("ultima_actualizacion"));
                solicitantes.add(solicitante);
            }
        } catch (Exception e) {
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
        return solicitantes;
    }

    public LinkedList<Solicitante> buscarSolicitantesFiltrados(String cedula, String nombre, String apellidos) throws SQLException, DataException {
        LinkedList<Solicitante> solicitantes = new LinkedList<Solicitante>();
        Connection conexion = super.getConnection();
        String sqlBuscar = "{CALL buscar_solicitantes_filtrados (?, ?, ?)}";
        try {
            CallableStatement statement = conexion.prepareCall(sqlBuscar);
            statement.setString(1, cedula);
            statement.setString(2, nombre);
            statement.setString(3, apellidos);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Solicitante solicitante = new Solicitante();
                solicitante.setId(rs.getInt("id"));
                solicitante.setCedula(rs.getString("cedula"));
                solicitante.setNombre(rs.getString("nombre"));
                solicitante.setApellidos(rs.getString("apellidos"));
                solicitante.setUsername(rs.getString("username"));
                solicitante.setPassword(rs.getString("passwd"));
                solicitante.setEdad(rs.getInt("edad"));
                solicitante.setEscolaridad(rs.getString("escolaridad"));
                solicitante.setExperienciaLaboral(rs.getInt("años_experiencia_laboral"));
                solicitante.setFoto(rs.getBytes("foto"));
                solicitante.setIdiomas(rs.getString("idiomas"));
                solicitante.setTelefonoFijo(rs.getString("telefono_fijo"));
                solicitante.setTelefonoMovil(rs.getString("telefono_movil"));
                solicitante.setTitulos(rs.getString("titulos"));
                solicitante.setSexo(rs.getString("sexo"));
                solicitante.setCorreo(rs.getString("correo"));
                solicitante.setDetalleExperienciaLaboral(rs.getString("detalle_experiencia_laboral"));
                solicitante.setUltimaActualizacion(rs.getDate("ultima_actualizacion"));
                solicitantes.add(solicitante);
            }
        } catch (Exception e) {
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
        return solicitantes;
    }

    public Solicitante buscarSolicitante(int id) throws SQLException, DataException {
        Connection conexion = super.getConnection();
        String sqlBuscar = "{CALL buscar_solicitante (?)}";
        Solicitante solicitante = new Solicitante();
        try {
            CallableStatement statement = conexion.prepareCall(sqlBuscar);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                solicitante.setId(rs.getInt("id"));
                solicitante.setCedula(rs.getString("cedula"));
                solicitante.setNombre(rs.getString("nombre"));
                solicitante.setApellidos(rs.getString("apellidos"));
                solicitante.setUsername(rs.getString("username"));
                solicitante.setPassword(rs.getString("passwd"));
                solicitante.setEdad(rs.getInt("edad"));
                solicitante.setEscolaridad(rs.getString("escolaridad"));
                solicitante.setExperienciaLaboral(rs.getInt("años_experiencia_laboral"));
                solicitante.setFoto(rs.getBytes("foto"));
                solicitante.setIdiomas(rs.getString("idiomas"));
                solicitante.setTelefonoFijo(rs.getString("telefono_fijo"));
                solicitante.setTelefonoMovil(rs.getString("telefono_movil"));
                solicitante.setTitulos(rs.getString("titulos"));
                solicitante.setSexo(rs.getString("sexo"));
                solicitante.setCorreo(rs.getString("correo"));
                solicitante.setDetalleExperienciaLaboral(rs.getString("detalle_experiencia_laboral"));
                solicitante.setUltimaActualizacion(rs.getDate("ultima_actualizacion"));
            }
        } catch (Exception e) {
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
        return solicitante;
    }
    
     public Solicitante buscarSolicitantePorNombreUsuario(String username) throws SQLException, DataException {
        Connection conexion = super.getConnection();
        String sqlBuscar = "{CALL buscar_solicitante_por_nombre_usuario (?)}";
        Solicitante solicitante = new Solicitante();
        try {
            CallableStatement statement = conexion.prepareCall(sqlBuscar);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                solicitante.setId(rs.getInt("id"));
                solicitante.setCedula(rs.getString("cedula"));
                solicitante.setNombre(rs.getString("nombre"));
                solicitante.setApellidos(rs.getString("apellidos"));
                solicitante.setUsername(rs.getString("username"));
                solicitante.setPassword(rs.getString("passwd"));
                solicitante.setEdad(rs.getInt("edad"));
                solicitante.setEscolaridad(rs.getString("escolaridad"));
                solicitante.setExperienciaLaboral(rs.getInt("años_experiencia_laboral"));
                solicitante.setFoto(rs.getBytes("foto"));
                solicitante.setIdiomas(rs.getString("idiomas"));
                solicitante.setTelefonoFijo(rs.getString("telefono_fijo"));
                solicitante.setTelefonoMovil(rs.getString("telefono_movil"));
                solicitante.setTitulos(rs.getString("titulos"));
                solicitante.setSexo(rs.getString("sexo"));
                solicitante.setCorreo(rs.getString("correo"));
                solicitante.setDetalleExperienciaLaboral(rs.getString("detalle_experiencia_laboral"));
                solicitante.setUltimaActualizacion(rs.getDate("ultima_actualizacion"));
            }
        } catch (Exception e) {
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
        return solicitante;
    }

    public Solicitante iniciarSesion(String nombreUsuario, String password) throws SQLException, DataException {
        Solicitante solicitante = new Solicitante();
        Connection conexion = super.getConnection();
        String sqlInicio = "{CALL iniciar_sesion_solicitante (?, ?)}";
        try {
            CallableStatement statement = conexion.prepareCall(sqlInicio);
            statement.setString(1, nombreUsuario);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                solicitante.setId(rs.getInt("id"));
                solicitante.setCedula(rs.getString("cedula"));
                solicitante.setNombre(rs.getString("nombre"));
                solicitante.setApellidos(rs.getString("apellidos"));
                solicitante.setUsername(nombreUsuario);
                solicitante.setPassword(password);
                solicitante.setEdad(rs.getInt("edad"));
                solicitante.setEscolaridad(rs.getString("escolaridad"));
                solicitante.setExperienciaLaboral(rs.getInt("años_experiencia_laboral"));
                solicitante.setFoto(rs.getBytes("foto"));
                solicitante.setIdiomas(rs.getString("idiomas"));
                solicitante.setTelefonoFijo(rs.getString("telefono_fijo"));
                solicitante.setTelefonoMovil(rs.getString("telefono_movil"));
                solicitante.setTitulos(rs.getString("titulos"));
                solicitante.setSexo(rs.getString("sexo"));
                solicitante.setCorreo(rs.getString("correo"));
                solicitante.setDetalleExperienciaLaboral(rs.getString("detalle_experiencia_laboral"));
            }
        } catch (Exception e) {
            if (e.getMessage().equalsIgnoreCase("The statement did not return a result set.")) {
                return solicitante;
            }
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
        return solicitante;
    }
}
