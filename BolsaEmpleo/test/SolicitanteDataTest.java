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
    
    private Solicitante solicitante;
    
    public SolicitanteDataTest() {
    }
    
    
    
    @Before
    public void setUp() {
        solicitante = new Solicitante("304590654", "Mauricio", "Obando Solano", "cesar.b.c", "cesar", null, 23, null, "Universidad Concluida", "Secundaria", 2, "Estudiante UCR", "87940363", "25918942", "cesar.b.c@hotmail.com", "Espanol");

    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void hello() throws SQLException {
     SolicitanteData solicitanteData = new SolicitanteData();
     //solicitanteData.insertarSolicitante(solicitante);
     //solicitante = solicitanteData.iniciarSesion("cesarb.c", "cesar");
     //System.out.println(solicitante.getCedula());
     
     solicitanteData.editarSolicitante(solicitante);
     }
}
