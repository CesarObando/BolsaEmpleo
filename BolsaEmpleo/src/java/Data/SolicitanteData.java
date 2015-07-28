/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Dominio.Solicitante;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Cesar
 */
public class SolicitanteData extends BaseData{

    public SolicitanteData() {
    }
    
    //Insertar Solicitante
    
    public Solicitante insertarSolicitante(Solicitante solicitanteAInsertar) throws SQLException{
        Connection conexion = super.getConnection();
        String sqlInsert = "{CALL insertar_solicitante ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?}";
        CallableStatement statement = conexion.prepareCall(sqlInsert);
        statement.setString(1, solicitanteAInsertar.getCedula());
        statement.setString(2, solicitanteAInsertar.getNombre());
        statement.setString(3, solicitanteAInsertar.getApellidos());
        statement.setString(4, solicitanteAInsertar.getUsername());
        statement.setString(5, solicitanteAInsertar.getPassword());
        statement.setBytes(6, solicitanteAInsertar.getFoto());
        statement.setInt(7, solicitanteAInsertar.getEdad());
        statement.setInt(8, solicitanteAInsertar.getSexo());
        statement.setString(9, solicitanteAInsertar.getEscolaridad());
        statement.setString(10, solicitanteAInsertar.getTitulos());
        statement.setInt(11, solicitanteAInsertar.getExperienciaLaboral());
        statement.setString(12, solicitanteAInsertar.getDetalleExperienciaLaboral());
        statement.setString(13, solicitanteAInsertar.getTelefonoFijo());
        statement.setString(14, solicitanteAInsertar.getTelefonoMovil());
        statement.setString(15, solicitanteAInsertar.getCorreo());
        statement.setString(16, solicitanteAInsertar.getIdomas());
        statement.executeUpdate();
        conexion.close();
        return solicitanteAInsertar;
    }
    
    
}
