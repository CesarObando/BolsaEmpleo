/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Dominio.CategoriaVacante;
import Dominio.Vacante;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.LinkedList;

/**
 *
 * @author ricardo
 */
public class VacanteData {

    public VacanteData() {
    }

    public void crearVacante(Vacante vacante, String cedulaEmpleador) throws SQLException {
        Connection con = BaseDeDatos.getConexion();
        CallableStatement cs = null;
        int consecutivo;
        try {
            cs = con.prepareCall("{call sp_crear_vacante(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.registerOutParameter(1, Types.INTEGER);
            cs.setString(2, vacante.getDescripcion());
            cs.setString(3, vacante.getRequerimientos());
            cs.setInt(4, vacante.getNumeroVacantes());
            cs.setInt(5, vacante.getDiasLaborar());
            cs.setTimestamp(6, vacante.getHoraEntrada());
            cs.setTimestamp(7, vacante.getHoraSalida());
            cs.setDouble(8, vacante.getSueldo());
            cs.setString(9, vacante.getProvincia());
            cs.setString(10, vacante.getCiudad());
            cs.setDate(11, vacante.getFechaCreacion());
            cs.setString(12, cedulaEmpleador);
            cs.setInt(13, vacante.getCategoria().getIdCategoria());
            cs.execute();
            consecutivo = cs.getInt(1);
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        } finally {
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
        }
    }//Fin actualizarCuentaEmpleador

    public void actualizarVacante(Vacante vacante) throws SQLException {
        Connection con = BaseDeDatos.getConexion();
        CallableStatement cs = null;
        try {
            cs = con.prepareCall("{call sp_modificar_vacante(?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, vacante.getIdPuesto());
            cs.setString(2, vacante.getDescripcion());
            cs.setString(3, vacante.getRequerimientos());
            cs.setInt(4, vacante.getNumeroVacantes());
            cs.setInt(5, vacante.getDiasLaborar());
            cs.setTimestamp(6, vacante.getHoraEntrada());
            cs.setTimestamp(7, vacante.getHoraSalida());
            cs.setDouble(8, vacante.getSueldo());
            cs.setString(9, vacante.getProvincia());
            cs.setString(10, vacante.getCiudad());
            cs.setDate(11, vacante.getFechaCreacion());
            cs.setInt(12, vacante.getCategoria().getIdCategoria());
            cs.execute();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        } finally {
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
        }
    }//Fin actualizarCuentaEmpleador

    private boolean vacanteTienePostulantes(int idVacante, Connection con) throws SQLException {
        boolean tienePostulantes = false;
        CallableStatement csPost = null;
        ResultSet rsPost = null;
        try {
            csPost = con.prepareCall("{call sp_vacante_tiene_postulantes(?)}");
            csPost.setInt(1, idVacante);
            csPost.execute();
            rsPost = csPost.getResultSet();
            if (rsPost.next() == true) {
                if (rsPost.getInt(1) > 0) {
                    tienePostulantes = true;
                }
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        } finally {
            if (csPost != null) {
                try {
                    csPost.close();
                } catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
            if (rsPost != null) {
                try {
                    rsPost.close();
                } catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
        }
        return tienePostulantes;
    }

    public LinkedList<Vacante> getVacantesPorCategoria(int idCategoria) throws SQLException {
        Connection con = BaseDeDatos.getConexion();
        LinkedList<Vacante> vacantes = new LinkedList<>();
        try {
            CallableStatement cs = con.prepareCall("{call sp_obtener_vacantes_por_categoria(?)}");
            cs.setInt(1, idCategoria);
            cs.execute();
            ResultSet rs = cs.getResultSet();
            while (rs.next() == true) {
                Vacante vacante = new Vacante();
                vacante.setIdPuesto(rs.getInt(1));
                vacante.setDescripcion(rs.getString(2));
                vacante.setRequerimientos(rs.getString(3));
                vacante.setNumeroVacantes(rs.getInt(4));
                vacante.setDiasLaborar(rs.getInt(5));
                vacante.setHoraEntrada(rs.getTimestamp(6));
                vacante.setHoraSalida(rs.getTimestamp(7));
                vacante.setSueldo(rs.getFloat(8));
                vacante.setProvincia(rs.getString(9));
                vacante.setCiudad(rs.getString(10));
                vacante.setFechaCreacion(rs.getDate(11));
                vacante.getCategoria().setIdCategoria(rs.getInt(12));
                vacante.getCategoria().setNombre(rs.getString(13));
                vacante.setTienePostulantes(vacanteTienePostulantes(vacante.getIdPuesto(), con));
                vacantes.add(vacante);
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
        }
        return vacantes;
    }

    public void borrarVacante(int idVacante) throws SQLException {
        Connection con = BaseDeDatos.getConexion();
        CallableStatement cs = null;
        try {
            cs = con.prepareCall("{call sp_borrar_vacante(?)}");
            cs.setInt(1, idVacante);
            cs.execute();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        } finally {
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
        }
    }//Fin actualizarCuentaEmpleador

    public LinkedList<String[]> getNumeroVacantesPorCategoria() throws SQLException {
        LinkedList<String[]> vacantesPorCategoría = new LinkedList<>();
        Connection con = null;
        CallableStatement cstmt = null;
        try {
            con = BaseDeDatos.getConexion();
            cstmt = con.prepareCall("{call sp_get_numero_vacantes_por_categoria()}");
            ResultSet rs = cstmt.executeQuery();
            while (rs.next() == true) {
                String[] vacante = new String[3];
                vacante[0] = Integer.toString(rs.getInt(1));
                vacante[1] = rs.getString(2);
                vacante[2] = Integer.toString(rs.getInt(3));
                vacantesPorCategoría.add(vacante);
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        } finally {
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                    System.out.print(e.getMessage());
                }
            }
        }
        return vacantesPorCategoría;
    }

    public LinkedList<Vacante> obtenerVacantes() throws SQLException {
        Connection conexion = BaseDeDatos.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_obtener_vacantes()}");
        LinkedList<Vacante> vacantes = new LinkedList<>();

        cs.execute();
        ResultSet rs = cs.getResultSet();
        while (rs.next()) {
            Vacante vacante = new Vacante();
            vacante.setIdPuesto(rs.getInt(1));
            vacante.setDescripcion(rs.getString(2));
            vacante.setRequerimientos(rs.getString(3));
            vacante.setNumeroVacantes(rs.getInt(4));
            vacante.setDiasLaborar(rs.getInt(5));
            /* vacante.setHoraEntrada(rs.getDate(6));
             vacante.setHoraSalida(rs.getDate(7));*/
            vacante.setSueldo(rs.getFloat(8));
            vacante.setProvincia(rs.getString(9));
            vacante.setCiudad(rs.getString(10));
            vacante.setFechaCreacion(rs.getDate(11));

            CategoriaVacante categoriaVacante = new CategoriaVacante();
            categoriaVacante.setIdCategoria(rs.getInt(13));
            categoriaVacante.setNombre(rs.getString(14));

            vacante.setCategoria(categoriaVacante);
            vacantes.add(vacante);
        }
        conexion.close();
        return vacantes;
    }

    public LinkedList<Vacante> obtenerVacantesOferente(String cedula) throws SQLException {
        Connection conexion = BaseDeDatos.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_obtener_vacantes_usuario(?)}");
        cs.setString(1, cedula);
        LinkedList<Vacante> vacantes = new LinkedList<>();

        cs.execute();
        ResultSet rs = cs.getResultSet();
        while (rs.next()) {
            Vacante vacante = new Vacante();

            vacante.setIdPuesto(rs.getInt(1));
            vacante.setDescripcion(rs.getString(2));
            vacante.setRequerimientos(rs.getString(3));
            vacante.setNumeroVacantes(rs.getInt(4));
            vacante.setDiasLaborar(rs.getInt(5));
            /*  vacante.setHoraEntrada(rs.getDate(6));
             vacante.setHoraSalida(rs.getDate(7));*/
            vacante.setSueldo(rs.getFloat(8));
            vacante.setProvincia(rs.getString(9));
            vacante.setCiudad(rs.getString(10));
            vacante.setFechaCreacion(rs.getDate(11));

            CategoriaVacante categoriaVacante = new CategoriaVacante();
            categoriaVacante.setIdCategoria(rs.getInt(13));
            categoriaVacante.setNombre(rs.getString(14));

            vacante.setCategoria(categoriaVacante);
            vacantes.add(vacante);
        }
        conexion.close();
        return vacantes;
    }

    public LinkedList<Vacante> obtenerVacantesEmpleador(String cedula) throws SQLException {
        Connection conexion = BaseDeDatos.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_obtener_vacantes_empleador(?)}");
        cs.setString(1, cedula);
        LinkedList<Vacante> vacantes = new LinkedList<>();
        cs.execute();
        ResultSet rs = cs.getResultSet();
        while (rs.next()) {
            Vacante vacante = new Vacante();
            vacante.setIdPuesto(rs.getInt(1));
            vacante.setDescripcion(rs.getString(2));
            vacante.setRequerimientos(rs.getString(3));
            vacante.setNumeroVacantes(rs.getInt(4));
            vacante.setDiasLaborar(rs.getInt(5));
            vacante.setHoraEntrada(rs.getTimestamp(6));
            vacante.setHoraSalida(rs.getTimestamp(7));
            vacante.setSueldo(rs.getFloat(8));
            vacante.setProvincia(rs.getString(9));
            vacante.setCiudad(rs.getString(10));
            vacante.setFechaCreacion(rs.getDate(11));
            vacante.getCategoria().setIdCategoria(rs.getInt(12));
            vacante.getCategoria().setNombre(rs.getString(13));
            vacantes.add(vacante);
        }
        conexion.close();
        return vacantes;
    }

    public Vacante obtenerVacante(int idPuesto) throws SQLException {
        Connection conexion = BaseDeDatos.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_obtener_vacante(?)}");
        cs.setInt(1, idPuesto);
        Vacante vacante = new Vacante();
        cs.execute();
        ResultSet rs = cs.getResultSet();
        while (rs.next()) {
            vacante.setIdPuesto(rs.getInt(1));
            vacante.setDescripcion(rs.getString(2));
            vacante.setRequerimientos(rs.getString(3));
            vacante.setNumeroVacantes(rs.getInt(4));
            vacante.setDiasLaborar(rs.getInt(5));
            vacante.setHoraEntrada(rs.getTimestamp(6));
            vacante.setHoraSalida(rs.getTimestamp(7));
            vacante.setSueldo(rs.getFloat(8));
            vacante.setProvincia(rs.getString(9));
            vacante.setCiudad(rs.getString(10));
            vacante.setFechaCreacion(rs.getDate(11));
            vacante.getCategoria().setIdCategoria(rs.getInt(12));
            vacante.getCategoria().setNombre(rs.getString(13));
        }
        conexion.close();
        return vacante;
    }

    public LinkedList<Vacante> buscarVacantes(String nombreCategoria, String provincia) throws SQLException {
        Connection conexion = BaseDeDatos.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_buscar_vacantes(?,?)}");
        LinkedList<Vacante> vacantes = new LinkedList<>();

        cs.setString(1, nombreCategoria);
        cs.setString(2, provincia);

        cs.execute();
        ResultSet rs = cs.getResultSet();
        while (rs.next()) {
            Vacante vacante = new Vacante();
            vacante.setIdPuesto(rs.getInt(1));
            vacante.setDescripcion(rs.getString(2));
            vacante.setRequerimientos(rs.getString(3));
            vacante.setNumeroVacantes(rs.getInt(4));
            vacante.setDiasLaborar(rs.getInt(5));
            /*  vacante.setHoraEntrada(rs.getDate(6));
             vacante.setHoraSalida(rs.getDate(7));*/
            vacante.setSueldo(rs.getFloat(8));
            vacante.setProvincia(rs.getString(9));
            vacante.setCiudad(rs.getString(10));
            vacante.setFechaCreacion(rs.getDate(11));

            CategoriaVacante categoriaVacante = new CategoriaVacante();
            categoriaVacante.setIdCategoria(rs.getInt(13));
            categoriaVacante.setNombre(rs.getString(14));

            vacante.setCategoria(categoriaVacante);
            vacantes.add(vacante);
        }
        conexion.close();
        return vacantes;
    }
}
