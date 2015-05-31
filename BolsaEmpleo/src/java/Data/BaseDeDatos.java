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
public class BaseDeDatos {

    private static Connection conexion;

    public BaseDeDatos() {
    }

    public static Connection getConexion() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://163.178.107.130:3306/bolsa_empleo", "adm", "saucr.092");
        } catch (SQLException | ClassNotFoundException ex) {
        }
        return conexion;
    }

}