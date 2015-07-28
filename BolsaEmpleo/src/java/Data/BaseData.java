/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.*;

/**
 *
 *
 */
public class BaseData {

    public BaseData() {
    }

  public Connection getConnection() throws SQLException{
        Connection conexion;
        String url = "jdbc:odbc:ProyectoVentas";
        String userName = "sqlserver";
        String pass = "saucr.12";
   
        conexion = DriverManager.getConnection(url,userName,pass);        
        return conexion;
    }

}