package Business;

import Data.AdministradorData;
import Dominio.Administrador;
import Exception.DataException;
import java.sql.SQLException;
import java.util.LinkedList;

public class AdministradorBusiness {

    private final AdministradorData administradorData;

    public AdministradorBusiness() {
        administradorData = new AdministradorData();
    }

    public Administrador insertarAdministrador(Administrador administrador) throws SQLException, DataException {
        return administradorData.insertarAdministrador(administrador);
    }

    public void editarAdministrador(Administrador administrador) throws SQLException, DataException {
        administradorData.editarAdministrador(administrador);
    }

    public void eliminarAdministrador(int idAdministrador) throws SQLException, DataException {
        administradorData.eliminarAdministrador(idAdministrador);
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

    public Administrador iniciarSesion(String username, String password) throws SQLException, DataException {
        return administradorData.iniciarSesion(username, password);
    }
    
    public int getNumeroVisitas() throws SQLException{
        return administradorData.getNumeroVisitas();
    }
}
