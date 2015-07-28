/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Data.OfertaData;
import Dominio.Categoria;
import Dominio.Empleador;
import Dominio.Oferta;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tin
 */
public class TestOferta {
    private Oferta oferta;
    public TestOferta() {
    }
  
    @Before
    public void setUp() {
        oferta=new Oferta();
    }
   
     @Test
    public void hello() {
        try {
            //prueba de insertarOferta
            Empleador empleador=new Empleador();
            empleador.setId(4);
            Categoria categoria=new Categoria();
            categoria.setId(1);
            
            oferta.setCantidadVacantes(10);
            oferta.setPuesto("Cajero");
            oferta.setRequerimientos("Conocimientos matematicos");
            oferta.setSalario(200000);
            oferta.setCategoria(categoria);
            oferta.setEmpleador(empleador);
            
            OfertaData oferdata=new OfertaData();
            
            oferdata.insertarOferta(oferta);
        } catch (SQLException ex) {
            Logger.getLogger(TestOferta.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
