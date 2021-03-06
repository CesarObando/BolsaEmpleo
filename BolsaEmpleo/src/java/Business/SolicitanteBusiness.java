/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Data.SolicitanteData;
import Dominio.Solicitante;
import Exception.DataException;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Cesar
 */
public class SolicitanteBusiness {
    
    private SolicitanteData solicitanteData;
    
    public SolicitanteBusiness() {
        solicitanteData = new SolicitanteData();
    }
    
    public Solicitante insertarSolicitante(Solicitante solicitanteAInsertar) throws SQLException, DataException {
        return solicitanteData.insertarSolicitante(solicitanteAInsertar);
    }
    
    public Solicitante editarSolicitante(Solicitante solicitanteAEditar) throws SQLException, DataException {
        return solicitanteData.editarSolicitante(solicitanteAEditar);
    }
    
    public Solicitante iniciarSesion(String nombreUsuario, String password) throws SQLException, DataException {
        return solicitanteData.iniciarSesion(nombreUsuario, password);
    }
    
    public LinkedList<Solicitante> buscarSolicitantes(String cedula, String nombre, String apellidos) throws SQLException, DataException {
        return solicitanteData.buscarSolicitantes(cedula, nombre, apellidos);
    }
    
    public void eliminarSolicitante(String cedula) throws SQLException, DataException {
        solicitanteData.eliminarSolicitante(cedula);
    }
    
}
