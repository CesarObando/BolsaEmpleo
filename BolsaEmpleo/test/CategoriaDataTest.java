/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Data.CategoriaData;
import Dominio.Categoria;
import java.sql.SQLException;
import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cesar
 */
public class CategoriaDataTest {
    
    private Categoria categoria;
    
    public CategoriaDataTest() {
    }
    
    @Before
    public void setUp() {
        categoria = new Categoria("Turismo");
    }
    
    @After
    public void tearDown() {
    }

    
     @Test
     public void hello() throws SQLException {
     CategoriaData categoriaData = new CategoriaData();
//     categoriaData.insertarCategoria(categoria);
//     
//     categoria.setId(5);
//     categoriaData.editarCategoria(categoria);
     
//     categoriaData.eliminarCategoria(4);
     
//     LinkedList<Categoria> categorias = categoriaData.getCategorias();
//     int i = 0;
//     while(categorias.size()> i){
//         System.out.println(categorias.get(i).getNombre());
//         i++;
//     }
     }
}
