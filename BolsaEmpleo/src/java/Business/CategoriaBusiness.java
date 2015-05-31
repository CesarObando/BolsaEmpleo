/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import Data.CategoriaData;
import Dominio.CategoriaVacante;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ricardo
 */
public class CategoriaBusiness {
    
    CategoriaData categoriaData=new CategoriaData();

    public CategoriaBusiness() {
        categoriaData=new CategoriaData();
    }
 
    public ArrayList<CategoriaVacante> getCategorias() throws SQLException {
        return categoriaData.getCategorias();
    }
    
}
