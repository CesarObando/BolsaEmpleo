/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Dominio.Oferta;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author Tin
 */
public class OfertaData extends BaseData{

    public OfertaData() {
    }
    //inserta una nueva oferta 
    public Oferta insertarOferta(Oferta newOferta) throws SQLException{
        Connection conexion = super.getConnection();
        String sqlInsert = "{CALL insertar_oferta(?,?,?,?,?,?,?)}";
        CallableStatement statement = conexion.prepareCall(sqlInsert);
        //cargamos el statement con la informacion nueva
        statement.registerOutParameter(1, Types.INTEGER);//variable de salida
        statement.setString(2, newOferta.getPuesto());
        statement.setInt(3, newOferta.getEmpleador().getId());
        statement.setFloat(4, newOferta.getSalario());
        statement.setInt(5, newOferta.getCantidadVacantes());
        statement.setString(6,newOferta.getRequerimientos());
        statement.setInt(7, newOferta.getCategoria().getId());
        statement.executeUpdate();
        
        newOferta.setId(statement.getInt(1));//solitamos el id generado
        conexion.close();
        
        return newOferta;
    }
  
    
}
