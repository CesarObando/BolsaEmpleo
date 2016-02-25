package Business;

import Data.EmpleadorData;
import Dominio.Empleador;
import Exception.DataException;
import java.sql.SQLException;
import java.util.LinkedList;

public class EmpleadorBusiness {

    private final EmpleadorData empleadorData;

    public EmpleadorBusiness() {
        empleadorData = new EmpleadorData();
    }

    public Empleador insertarEmpleador(Empleador empleador) throws SQLException, DataException {
        return empleadorData.insertarEmpleador(empleador);
    }

    public void editarEmpleador(Empleador empleador) throws SQLException, DataException {
        empleadorData.editarEmpleador(empleador);
    }

    public void eliminarEmpleador(int idEmpleador) throws SQLException, DataException {
        empleadorData.eliminarEmpleador(idEmpleador);
    }

    public Empleador iniciarSesion(String username, String password) throws SQLException, DataException {
        return empleadorData.iniciarSesion(username, password);
    }

    public LinkedList<Empleador> buscarEmpleadores() throws SQLException, DataException {
        return empleadorData.buscarEmpleadores();
    }

    public LinkedList<Empleador> buscarEmpleadoresFiltrados(String cedula, String nombre, String apellidos) throws SQLException, DataException {
        return empleadorData.buscarEmpleadoresFiltrados(cedula, nombre, apellidos);
    }

    public Empleador buscarEmpleador(int idEmpleador) throws SQLException, DataException {
        return empleadorData.buscarEmpleador(idEmpleador);
        
    }
    
    public Empleador buscarEmpleadorPorNombreUsuario(String username) throws SQLException, DataException{
        return empleadorData.buscarEmpleadorPorNombreUsuario(username);
    }
}
