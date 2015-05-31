/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import Data.ContactoEmpleadorData;
import Dominio.ContactoEmpleador;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author ricardo
 */
public class ContactoEmpleadorBusiness {
    
    ContactoEmpleadorData contactoEmpData;

    public ContactoEmpleadorBusiness() {
        contactoEmpData=new ContactoEmpleadorData();
    }
    
    public void insertarEmpresa(ContactoEmpleador empresa, String cedulaEmpleador) throws SQLException{
        contactoEmpData.insertarEmpresa(empresa, cedulaEmpleador);
    }
    
    public void modificarEmpresa(ContactoEmpleador empresa) throws SQLException{
        contactoEmpData.modificarEmpresa(empresa);
    }
    
    public ContactoEmpleador getContactoEmpleador(String cedula) throws SQLException{
        return contactoEmpData.getContactoEmpleador(cedula);
    }
    
    public LinkedList<ContactoEmpleador> getContactosEmpleadores() throws SQLException{
        return contactoEmpData.getContactosEmpleadores();
    }
    
    public void borrarContactoEmpleador(String cedulaJuridica) throws SQLException{
        contactoEmpData.borrarContactoEmpleador(cedulaJuridica);
    }
    
}
