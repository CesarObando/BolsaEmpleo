/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Data.AdministradorData;
import Dominio.Administrador;
import Exception.DataException;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author JonathanA
 */
public class AdministradorBusiness {

    private AdministradorData administradorData;

    public AdministradorBusiness() {
        administradorData = new AdministradorData();
    }

    public Administrador insertarAdministrador(Administrador administrador) throws SQLException, DataException {
        return administradorData.insertarAdministrador(administrador);
    }

    public void editarAdministrador(Administrador administrador) throws SQLException, DataException {
        administradorData.editarAdministrador(administrador);
    }

    public void eliminarAdministrador(int id) throws SQLException, DataException {
        administradorData.eliminarAdministrador(id);
    }

    public LinkedList<Administrador> buscarAdministradores() throws SQLException, DataException {
        return administradorData.buscarAdministradores();
    }

    public LinkedList<Administrador> buscarAdministradoresFiltrados(String cedula, String nombre, String apellidos) throws SQLException, DataException {
        return administradorData.buscarAdministradoresFiltrados(cedula, nombre, apellidos);
    }

    public Administrador buscarAdministrador(int id) throws SQLException, DataException {
        return administradorData.buscarAdministrador(id);
    }

    public Administrador iniciarSesion(String nombreUsuario, String password) throws SQLException, DataException {
        return administradorData.iniciarSesion(nombreUsuario, password);
    }
}
