/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import Data.AdministradorData;
import Dominio.Usuario;
import java.sql.SQLException;

/**
 *
 * @author ricardo
 */
public class AdministradorBusiness {
    
    AdministradorData administradorData;
    
    public AdministradorBusiness() {
        administradorData=new AdministradorData();
    }
    
    public void cambiarUsuarioAdministrador(Usuario usuario, boolean valor) throws SQLException {
        administradorData.cambiarUsuarioAdministrador(usuario, valor);
    }
    
}
    
