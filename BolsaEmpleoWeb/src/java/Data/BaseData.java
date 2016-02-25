package Data;

import java.sql.*;

public class BaseData {

    //Constructor vacio
    public BaseData() {
    }

    public Connection getConnection() throws SQLException {
        String userName = "sqlserver";
        String password = "saucr.12";
        String className = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://163.178.107.130:1433;databaseName=BolsaEmpleoParaiso;user=" + userName + ";password=" + password + ";";
        try {
            Class.forName(className);
        } catch (ClassNotFoundException ex) {
            System.out.print(ex.getMessage());
        }
        return DriverManager.getConnection(url);
    }

}
