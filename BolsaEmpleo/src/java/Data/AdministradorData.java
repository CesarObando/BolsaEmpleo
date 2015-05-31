/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Data;

import Dominio.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author ricardo
 */
public class AdministradorData extends BaseDeDatos{
    
    public void cambiarUsuarioAdministrador(Usuario usuario, boolean valor) throws SQLException {
        Connection conexion = BaseDeDatos.getConexion();
        CallableStatement cs = conexion.prepareCall("{call sp_actualizar_usuario_a_administrador(?,?)}");
        cs.setString(1, usuario.getCedula());
        cs.setBoolean(2, valor);
        cs.execute();
        conexion.close();
    }
    
}
