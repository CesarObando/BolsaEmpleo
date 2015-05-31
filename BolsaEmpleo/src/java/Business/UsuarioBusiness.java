/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Data.UsuarioData;
import Dominio.Oferente;
import Dominio.Usuario;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;

/**
 *
 * @author Dalaia
 */
public class UsuarioBusiness {

    private final UsuarioData usuarioData;

    public UsuarioBusiness() {
        usuarioData = new UsuarioData();
    }

    public void ingresarUsuario(Usuario usuario) throws SQLException, FileNotFoundException {
        usuarioData.ingresarUsuario(usuario);     
    }
    
    public boolean iniciarSesion(Usuario usuario) throws SQLException{
        return usuarioData.iniciarSesion(usuario);
    }
    
    public void actualizarContrasena(Usuario usuario) throws SQLException{
        usuarioData.actualizarContrasena(usuario);
    }
    
    public void actualizarUsuario(Usuario usuario) throws SQLException{
        usuarioData.actualizarUsuario(usuario);
    }
    
    public void eliminarUsuario(Usuario usuario) throws SQLException{
        usuarioData.eliminarUsuario(usuario);
    }
    
    
}
