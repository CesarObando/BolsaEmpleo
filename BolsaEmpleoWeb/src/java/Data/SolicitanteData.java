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
import java.util.LinkedList;

/**
 *
 * @author Cesar
 */
public class SolicitanteData extends BaseData {

    public SolicitanteData() {
    }

    //Insertar Solicitante
    public Solicitante insertarSolicitante(Solicitante solicitanteAInsertar) throws SQLException, DataException {
        Connection conexion = super.getConnection();
        String sqlInsert = "{CALL insertar_solicitante (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            CallableStatement statement = conexion.prepareCall(sqlInsert);
            statement.setString(1, solicitanteAInsertar.getCedula());
            statement.setString(2, solicitanteAInsertar.getNombre());
            statement.setString(3, solicitanteAInsertar.getApellidos());
            statement.setString(4, solicitanteAInsertar.getUsername());
            statement.setString(5, solicitanteAInsertar.getPassword());
            statement.setBytes(6, solicitanteAInsertar.getFoto());
            statement.setInt(7, solicitanteAInsertar.getEdad());
            statement.setString(8, solicitanteAInsertar.getSexo());
            statement.setString(9, solicitanteAInsertar.getEscolaridad());
            statement.setString(10, solicitanteAInsertar.getTitulos());
            statement.setInt(11, solicitanteAInsertar.getExperienciaLaboral());
            statement.setString(12, solicitanteAInsertar.getDetalleExperienciaLaboral());
            statement.setString(13, solicitanteAInsertar.getTelefonoFijo());
            statement.setString(14, solicitanteAInsertar.getTelefonoMovil());
            statement.setString(15, solicitanteAInsertar.getCorreo());
            statement.setString(16, solicitanteAInsertar.getIdiomas());
            statement.executeUpdate();
            conexion.commit();
        } catch (Exception e) {
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
        return solicitanteAInsertar;
    }

    public Solicitante editarSolicitante(Solicitante solicitanteAEditar) throws SQLException, DataException {
        Connection conexion = super.getConnection();
        String sqlInsert = "{CALL editar_solicitante (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try {
            CallableStatement statement = conexion.prepareCall(sqlInsert);
            statement.setString(1, solicitanteAEditar.getCedula());
            statement.setString(2, solicitanteAEditar.getPassword());
            statement.setBytes(3, solicitanteAEditar.getFoto());
            statement.setString(4, solicitanteAEditar.getEscolaridad());
            statement.setString(5, solicitanteAEditar.getTitulos());
            statement.setInt(6, solicitanteAEditar.getExperienciaLaboral());
            statement.setString(7, solicitanteAEditar.getDetalleExperienciaLaboral());
            statement.setString(8, solicitanteAEditar.getTelefonoFijo());
            statement.setString(9, solicitanteAEditar.getTelefonoMovil());
            statement.setString(10, solicitanteAEditar.getCorreo());
            statement.setString(11, solicitanteAEditar.getIdiomas());
            statement.executeUpdate();
            conexion.commit();
        } catch (Exception e) {
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
        return solicitanteAEditar;
    }

    public Solicitante iniciarSesion(String nombreUsuario, String password) throws SQLException, DataException {
        Solicitante solicitante = new Solicitante();
        Connection conexion = super.getConnection();
        String sqlInicio = "{CALL iniciar_sesion_solicitantes (?, ?)}";
        try {
            CallableStatement statement = conexion.prepareCall(sqlInicio);
            statement.setString(1, nombreUsuario);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
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
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
        return solicitante;
    }

    public LinkedList<Solicitante> buscarSolicitantes(String cedula, String nombre, String apellidos) throws SQLException, DataException {
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
                solicitante.setCedula(rs.getString("cedula"));
                solicitante.setNombre(rs.getString("nombre"));
                solicitante.setApellidos(rs.getString("apellidos"));
                solicitante.setUsername(rs.getString("nombre_usuario"));
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
                solicitantes.add(solicitante);
            }
        } catch (Exception e) {
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
        return solicitantes;
    }

    public void eliminarSolicitante(String cedula) throws SQLException, DataException {
        Connection conexion = super.getConnection();
        String sqlEliminar = "{CALL eliminar_solicitante (?)}";
        try {
            CallableStatement statement = conexion.prepareCall(sqlEliminar);
            statement.setString(1, cedula);
            statement.executeUpdate();
            conexion.commit();
        } catch (Exception e) {
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
    }
}
