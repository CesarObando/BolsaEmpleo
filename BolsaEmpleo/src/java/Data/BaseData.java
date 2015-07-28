/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *
 */
public class BaseData {

    public BaseData() {
    }

  public Connection getConnection() throws SQLException{
        Connection conexion;
        String userName="sqlserver";
        String password="saucr.12";
        String className="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url="jdbc:sqlserver://163.178.107.130:1433;databaseName=BolsaEmpleoParaiso;user="+userName+";password="+password+";";
        
        try {
            Class.forName(className);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BaseData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DriverManager.getConnection(url);
    }

}