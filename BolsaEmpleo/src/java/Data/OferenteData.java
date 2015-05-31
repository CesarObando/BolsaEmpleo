/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Data;

import Dominio.Oferente;
import Dominio.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author ricardo
 */
public class OferenteData {

    public OferenteData() {
    }
    
    public void ingresarOferenteVacante(String cedulaOferente, int idVacante) throws SQLException {
        Connection conexion = BaseDeDatos.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_insertar_oferente_vacante(?,?)}");
        cs.setString(1, cedulaOferente);
        cs.setInt(2, idVacante);
        cs.execute();
        conexion.close();
    }

    public void eliminarOferenteVacante(String cedulaOferente, int idVacante) throws SQLException {
        Connection conexion = BaseDeDatos.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_eliminar_oferente_vacante(?,?)}");
        cs.setString(1, cedulaOferente);
        cs.setInt(2, idVacante);
        cs.execute();
        conexion.close();
    }
    
    public void borrarPostulacion(String cedulaOferente, int idVacante) throws SQLException{
        Connection con = BaseDeDatos.getConexion();
        CallableStatement cs = null;
        try {
            cs = con.prepareCall("{call sp_borrar_postulacion(?,?)}");
            cs.setString(1, cedulaOferente);
            cs.setInt(2, idVacante);
            cs.execute();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        finally {
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
    
    public void borrarPostulante(String cedulaOferente) throws SQLException{
        Connection con = BaseDeDatos.getConexion();
        CallableStatement cs = null;
        try {
            cs = con.prepareCall("{call sp_borrar_postulante(?)}");
            cs.setString(1, cedulaOferente);
            cs.execute();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        finally {
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
    
     public LinkedList<Oferente> getPostulantesPorVacante(int idVacante) throws SQLException{
        Connection con = BaseDeDatos.getConexion();
        LinkedList<Oferente> oferentes=new LinkedList<>();
        try {
            CallableStatement cs = con.prepareCall("{call sp_obtener_oferentes_por_vacante(?)}");
            cs.setInt(1, idVacante);
            cs.execute();
            ResultSet rs = cs.getResultSet();
            while (rs.next() == true){
                Oferente oferente=new Oferente();
                oferente.setCedula(rs.getString(1));
                oferente.setNombre(rs.getString(2)+" "+rs.getString(3));
                oferente.setProvincia(rs.getString(4));
                oferente.setCiudad(rs.getString(5));
                oferente.setDireccion(rs.getString(6));
                oferente.setCodigoPostal(rs.getInt(7));
                oferente.setEmail(rs.getString(8));
                oferente.setTelefonoCasa(rs.getString(9));
                oferente.setTelefonoCelular(rs.getString(10));
                oferente.setFax(rs.getString(11));
                oferente.setFechaNacimiento(rs.getDate(12));
                oferentes.add(oferente);
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
        }
        return oferentes;
     } 
     
     public LinkedList<Oferente> getPostulantes() throws SQLException{
        Connection con = BaseDeDatos.getConexion();
        LinkedList<Oferente> oferentes=new LinkedList<>();
        try {
            CallableStatement cs = con.prepareCall("{call sp_obtener_oferentes()}");
            cs.execute();
            ResultSet rs = cs.getResultSet();
            while (rs.next() == true){
                Oferente oferente=new Oferente();
                oferente.setCedula(rs.getString(1));
                oferente.setNombre(rs.getString(2));
                oferente.setApellidos(rs.getString(3));
                oferente.setProvincia(rs.getString(4));
                oferente.setCiudad(rs.getString(5));
                oferente.setDireccion(rs.getString(6));
                oferente.setCodigoPostal(rs.getInt(7));
                oferente.setEmail(rs.getString(8));
                oferente.setTelefonoCasa(rs.getString(9));
                oferente.setTelefonoCelular(rs.getString(10));
                oferente.setFax(rs.getString(11));
                oferente.setFechaNacimiento(rs.getDate(12));
                oferentes.add(oferente);
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
        }
        return oferentes;
     }  
     
     public Oferente getPostulante(String cedula) throws SQLException{
        Connection con = BaseDeDatos.getConexion();
        Oferente oferente=new Oferente();
        try {
            CallableStatement cs = con.prepareCall("{call sp_obtener_oferente(?)}");
            cs.setString(1, cedula);
            cs.execute();
            ResultSet rs = cs.getResultSet();
            while (rs.next() == true){
                oferente.setCedula(rs.getString(1));
                oferente.setNombre(rs.getString(2));
                oferente.setApellidos(rs.getString(3));
                oferente.setProvincia(rs.getString(4));
                oferente.setCiudad(rs.getString(5));
                oferente.setDireccion(rs.getString(6));
                oferente.setCodigoPostal(rs.getInt(7));
                oferente.setEmail(rs.getString(8));
                oferente.setTelefonoCasa(rs.getString(9));
                oferente.setTelefonoCelular(rs.getString(10));
                oferente.setFax(rs.getString(11));
                oferente.setFechaNacimiento(rs.getDate(12));
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
        }
        return oferente;
     }  
    
    public LinkedList<String []> getNumeroOferentesPorEspecialidad() throws SQLException{
        LinkedList<String []> oferentesPorESpecialidad = new LinkedList<>();
        Connection con = null;
        CallableStatement cstmt = null;
        try {
            con = BaseDeDatos.getConexion(); 
            cstmt = con.prepareCall("{call sp_get_numero_oferentes_por_especialidad()}");
            ResultSet rs = cstmt.executeQuery();
            while (rs.next() == true){
                String [] vacante= new String[3];
                vacante[0]=Integer.toString(rs.getInt(1));
                vacante[1]=rs.getString(2);
                vacante[2]=Integer.toString(rs.getInt(3));
                oferentesPorESpecialidad.add(vacante);
            }
        }catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }finally {
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
        return oferentesPorESpecialidad;
    }
    
    public void cargarOferenete(Oferente oferente) throws SQLException{
        EspecialidadOferenteData especialidadOferenteData =  new EspecialidadOferenteData();
        ExperienciaLaboralData experienciaLaboralData = new ExperienciaLaboralData();
        oferente.setEspecialidades(especialidadOferenteData.getEspecialidadesOferente(oferente.getCedula()));
        oferente.setExperienciasLaborales(experienciaLaboralData.getExperienciasOferente(oferente.getCedula()));
        oferente.setVacantes(new VacanteData().obtenerVacantesOferente(oferente.getCedula()));
    }
    
    public void agregarOferente(Oferente oferente) throws SQLException, FileNotFoundException {
        Connection conexion = BaseDeDatos.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_ingresar_oferente(?,?)}");
        cs.setString(1, oferente.getCedula());
        if(oferente.getArchivoCurriculo() == null){
            cs.setBinaryStream(2, null);
        }else{
            InputStream entrada = new FileInputStream(oferente.getArchivoCurriculo());
            cs.setBinaryStream(2, entrada, (int) oferente.getArchivoCurriculo().length());
        }
        cs.execute();
        conexion.close();
    }
    
    
    public void cambiarUsuarioOferente(Usuario usuario) throws SQLException {
        Connection conexion = BaseDeDatos.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_actualizar_usuario_a_oferente(?,?)}");
        cs.setString(1, usuario.getCedula());
        cs.setBoolean(2, usuario.isOferente());
        cs.execute();
        conexion.close();
    }
    
    public void actualizarCurriculo(Usuario usuario, File archivoCurriculo) throws SQLException, FileNotFoundException {
        Connection conexion = BaseDeDatos.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_actualizar_curriculo(?,?)}");
        InputStream entrada = new FileInputStream(archivoCurriculo);
        cs.setString(1, usuario.getCedula());
        cs.setBinaryStream(2, entrada, (int) archivoCurriculo.length());
        cs.execute();
        conexion.close();
    }
    
    
    
    
    /*public File obtenerCurriculo(String cedula) throws SQLException{
        Connection conexion = BaseDeDatos.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_obtener_curriculo(?)}");
        cs.setString(1, cedula);

        cs.execute();
        ResultSet rs = cs.getResultSet();
        while (rs.next()) {
            InputStream inputStream = rs.getBinaryStream(1); // reading image as InputStream
            
            int read = 0;
		byte[] bytes = new byte[1024];
 
		while ((read = inputStream.read(bytes)) != -1) {
			outputStream.write(bytes, 0, read);
		}

        }
        conexion.close();
        
        return null;
    }*/
}
