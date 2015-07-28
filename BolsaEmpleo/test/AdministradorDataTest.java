/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Data.AdministradorData;
import Dominio.Administrador;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JonathanA
 */
public class AdministradorDataTest {
    
    AdministradorData administradorData;
    Administrador administrador;
    
    public AdministradorDataTest() {
    }
    
    @Before
    public void setUp() {
        administradorData = new AdministradorData();
        administrador = new Administrador();
    }
    
    @Test
    public void insertarAdministrador() throws SQLException{
        administrador.setCedula("3-0135-0345");
        administrador.setNombre("Eduardo");
        administrador.setApellidos("Castillo Araya");
        administrador.setUsername("eduardoca");
        administrador.setPassword("eduardoca");
        administradorData.insertarAdministrador(administrador);
    }
    
    @After
    public void tearDown() {
    }
    
}
