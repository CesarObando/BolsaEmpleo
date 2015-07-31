/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Data.EmpleadorData;
import Dominio.Empleador;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Tin em
 */
public class EmpleadorBusiness {
    
   private EmpleadorData empleadorData;

    public EmpleadorBusiness() {
        empleadorData=new EmpleadorData();
    }
   
    public Empleador insertarEmpleador(Empleador empleador) throws SQLException{
        
    return empleadorData.insertarEmpleador(empleador);
    
    }
    public void editarEmpleador(Empleador empleador) throws SQLException{
        empleadorData.editarEmpleador(empleador);
    }
    public void eliminarEmpleador(int id) throws SQLException{
       
        empleadorData.eliminarEmpleador(id);
    
    }
    public boolean inicioSesecion(String user,String pass) throws SQLException{
        return empleadorData.inicioSesion(user, pass);
    
    }
}
