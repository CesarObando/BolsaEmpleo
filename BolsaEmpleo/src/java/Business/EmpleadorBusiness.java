/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import Data.BaseDeDatos;
import Data.EmpleadorData;
import Dominio.Empleador;
import Dominio.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author ricardo
 */
public class EmpleadorBusiness {

    EmpleadorData empleadorData;
    
    public EmpleadorBusiness() {
        empleadorData=new EmpleadorData();
    }
    
    public void actualizarCuentaEmpleador(Empleador empleador) throws SQLException{
        empleadorData.actualizarCuentaEmpleador(empleador);
    }
    
    public void borrarEmpleador(String cedula) throws SQLException{
        empleadorData.borrarEmpleador(cedula);
    }
    
    public LinkedList<Empleador> getEmpleadores() throws SQLException{
        return empleadorData.getEmpleadores();
    }
    
    public Empleador getEmpleador(String cedula) throws SQLException{
        return empleadorData.getEmpleador(cedula);
    }
    
    public void agregarEmpleador(Empleador empleador) throws SQLException {
        empleadorData.agregarEmpleador(empleador);
    }
    
    
    public void cambiarUsuarioEmpleador(Usuario usuario) throws SQLException {
        empleadorData.cambiarUsuarioEmpleador(usuario);
    }
    
}
