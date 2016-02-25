package Business;

import Data.SolicitanteData;
import Dominio.Solicitante;
import Exception.DataException;
import java.sql.SQLException;
import java.util.LinkedList;

public class SolicitanteBusiness {

    private final SolicitanteData solicitanteData;

    public SolicitanteBusiness() {
        solicitanteData = new SolicitanteData();
    }

    public Solicitante insertarSolicitante(Solicitante solicitanteAInsertar) throws SQLException, DataException {
        return solicitanteData.insertarSolicitante(solicitanteAInsertar);
    }

    public Solicitante editarSolicitante(Solicitante solicitanteAEditar) throws SQLException, DataException {
        return solicitanteData.editarSolicitante(solicitanteAEditar);
    }

    public Solicitante iniciarSesion(String username, String password) throws SQLException, DataException {
        return solicitanteData.iniciarSesion(username, password);
    }

    public LinkedList<Solicitante> buscarSolicitantes() throws SQLException, DataException {
        return solicitanteData.buscarSolicitantes();
    }

    public LinkedList<Solicitante> buscarSolicitantesFiltrados(String cedula, String nombre, String apellidos) throws SQLException, DataException {
        return solicitanteData.buscarSolicitantesFiltrados(cedula, nombre, apellidos);
    }
    
    public Solicitante buscarSolicitantePorNombreUsuario(String username) throws SQLException, DataException{
        return solicitanteData.buscarSolicitantePorNombreUsuario(username);
    }

    public Solicitante buscarSolicitante(int idSolicitante) throws SQLException, DataException {
        return solicitanteData.buscarSolicitante(idSolicitante);
    }

    public void eliminarSolicitante(int idSolicitante) throws SQLException, DataException {
        solicitanteData.eliminarSolicitante(idSolicitante);
    }

}
