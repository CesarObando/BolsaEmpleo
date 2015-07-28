/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.*;

/**
 *
 * @author ricardo
 */
public class BaseData {

    public BaseData() {
    }

  public Connection getConnection() throws SQLException{
        Connection conexion;
        String url = "jdbc:odbc:ProyectoVentas";
        String userName = "";
        String pass = "";
   
        conexion = DriverManager.getConnection(url);        
        return conexion;
    }

}