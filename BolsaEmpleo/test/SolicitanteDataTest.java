/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Data.SolicitanteData;
import Dominio.Solicitante;
import java.sql.SQLException;
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
public class SolicitanteDataTest {
    
    public SolicitanteDataTest() {
    }
    
    
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void hello() throws SQLException {
     Solicitante solicitante = new Solicitante("304590654", "Cesar", "Obando Solano", "cesar.b.c", "cesar", null, 23, 'M', "Secundaria Concluida", "Secundaria", 2, "Estudiante UCR", "87940363", "25918942", "cesar.b.c@hotmail.com", "Espanol");
     SolicitanteData solicitanteData = new SolicitanteData();
     solicitanteData.insertarSolicitante(solicitante);
     
     }
}
