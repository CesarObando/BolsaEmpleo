/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Dominio.EspecialidadOferente;
import Dominio.ExperienciaLaboral;
import Dominio.Oferente;
import Dominio.Usuario;
import com.sun.corba.se.impl.util.PackagePrefixChecker;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author Dalaia
 */
public class UsuarioData extends BaseDeDatos {

    public UsuarioData() {
    }
    
     public void ingresarUsuario(Usuario usuario) throws SQLException, FileNotFoundException {
        Connection conexion = super.getConexion();
        try {
            conexion.setAutoCommit(false);
            CallableStatement cs = conexion.prepareCall("{call sp_ingresar_usuario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, usuario.getCedula());
            cs.setString(2, usuario.getNombreUsuario());
            cs.setString(3, usuario.getClave());
            cs.setString(4, usuario.getNombre());
            cs.setString(5, usuario.getApellidos());
            cs.setString(6, usuario.getProvincia());
            cs.setString(7, usuario.getCiudad());
            cs.setString(8, usuario.getDireccion());
            cs.setInt(9, usuario.getCodigoPostal());
            cs.setString(10, usuario.getEmail());
            cs.setString(11, usuario.getTelefonoCasa());
            cs.setString(12, usuario.getTelefonoCelular());
            cs.setString(13, usuario.getFax());
            cs.setTimestamp(14, new Timestamp(usuario.getFechaNacimiento().getTime()));
            cs.setBoolean(15, usuario.isOferente());
            cs.setBoolean(16, usuario.isEmpleador());
            cs.setBoolean(17, usuario.isAdministrador());

            cs.execute();

            if (usuario.isOferente()) {//si es oferente lo que se está ingresando
                Oferente oferente = (Oferente) usuario;
                cs = conexion.prepareCall("{call sp_ingresar_oferente(?,?)}");
                InputStream entrada = new FileInputStream(oferente.getArchivoCurriculo());
                cs.setString(1, usuario.getCedula());
                cs.setBinaryStream(2, entrada, (int) oferente.getArchivoCurriculo().length());
                cs.execute();
            }

            conexion.commit();
        } catch (SQLException ex) {
            conexion.rollback();
            throw ex;
        }
        conexion.setAutoCommit(true);
        conexion.close();
    }
     
     public void actualizarUsuario(Usuario usuario) throws SQLException{
        Connection conexion = super.getConexion();

        CallableStatement cs = conexion.prepareCall("{call sp_actualizar_usuario(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        cs.setString(1, usuario.getCedula());
        cs.setString(2, usuario.getNombreUsuario());
        cs.setString(3, usuario.getNombre());
        cs.setString(4, usuario.getApellidos());
        cs.setString(5, usuario.getProvincia());
        cs.setString(6, usuario.getCiudad());
        cs.setString(7, usuario.getDireccion());
        cs.setInt(8, usuario.getCodigoPostal());
        cs.setString(9, usuario.getEmail());
        cs.setString(10, usuario.getTelefonoCasa());
        cs.setString(11, usuario.getTelefonoCelular());
        cs.setString(12, usuario.getFax());
        cs.setTimestamp(13, new Timestamp(usuario.getFechaNacimiento().getTime()));

        cs.execute();
        conexion.close();
    }
     
     public boolean iniciarSesion(Usuario usuario) throws SQLException{
         boolean correcto = true;
        Connection conexion = super.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_verificar_usuario(?,?)}");
        cs.setString(1, usuario.getNombreUsuario());
        cs.setString(2, usuario.getClave());

        cs.execute();
        ResultSet rs = cs.getResultSet();
        
        if(rs == null){
            return false;
        }
        while (rs.next()) {
            if(rs.getString(1) == null || rs.getString(1).equals(""))
                return false; 
            usuario.setCedula(rs.getString(1));
            usuario.setNombre(rs.getString(4));
            usuario.setApellidos(rs.getString(5));
            usuario.setProvincia(rs.getString(6));
            usuario.setCiudad(rs.getString(7));
            usuario.setDireccion(rs.getString(8));
            usuario.setCodigoPostal(rs.getInt(9));
            usuario.setEmail(rs.getString(10));
            usuario.setTelefonoCasa(rs.getString(11));
            usuario.setTelefonoCelular(rs.getString(12));
            usuario.setFax(rs.getString(13));
            usuario.setFechaNacimiento(rs.getDate(14));
            usuario.setOferente(rs.getBoolean(15));
            usuario.setEmpleador(rs.getBoolean(16));
            usuario.setAdministrador(rs.getBoolean(17));
               
        }
        conexion.close();
        return correcto;
     }

     public void actualizarContrasena(Usuario usuario) throws SQLException{
        Connection conexion = super.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_actualizar_contrasena(?,?)}");
        cs.setString(1, usuario.getCedula());
        cs.setString(2, usuario.getClave());
        cs.execute();
        conexion.close();
    }
     
     public void eliminarUsuario(Usuario usuario) throws SQLException{
        Connection conexion = super.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_borrar_usuario(?)}");
        cs.setString(1, usuario.getCedula());
        cs.execute();
        conexion.close();
    }
    
     
}
