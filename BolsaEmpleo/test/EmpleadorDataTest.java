/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Data.EmpleadorData;
import Dominio.Empleador;
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
public class EmpleadorDataTest {
    private Empleador empleador;
    
    public EmpleadorDataTest() {
    }
    
   
    @Before
    public void setUp() {
        empleador=new Empleador(13, "3464868", "", "Camacho mora", "tinnit@hotmail.es", "25464653", "85341334", "", "", "San Isidro de Leon Cortes","123","tin");
    }
    
     @Test
    public void hello() {
        
        EmpleadorData empleadoData=new EmpleadorData();
        boolean secion=new Boolean(null);
        secion= empleadoData.inicioSecion("marvince", "marvince");
        if (secion=true){
            System.out.print("Bienvenido ");
            
        }else{
            System.out.print("usario y contraseña incorecta");
        }
        
    
    
    }
}
