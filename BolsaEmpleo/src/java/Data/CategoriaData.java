/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Dominio.CategoriaVacante;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ricardo
 */
public class CategoriaData {

    public CategoriaData() {
    }

    public ArrayList<CategoriaVacante> getCategorias() throws SQLException {
        ArrayList<CategoriaVacante> categorias = null;
        Connection con = null;
        try {
            con = BaseDeDatos.getConexion();
            categorias = new ArrayList<>();
            Statement stmt = con.createStatement();
            String slctVacantesPorCategoria = "select id_categoria_vacante, nombre from t_categoria_vacante";
            ResultSet rs = stmt.executeQuery(slctVacantesPorCategoria);
            while (rs.next() == true) {
                CategoriaVacante categoría = new CategoriaVacante();
                categoría.setIdCategoria(rs.getInt(1));
                categoría.setNombre(rs.getString(2));
                categorias.add(categoría);
            }

        }catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
        }
        return categorias;
    }
}
