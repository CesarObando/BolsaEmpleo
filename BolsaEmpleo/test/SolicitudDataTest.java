/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Data.SolicitudData;
import Dominio.Solicitud;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JonathanA
 */
public class SolicitudDataTest {
    
    SolicitudData solicitudData;
    Solicitud solicitud;
    
    public SolicitudDataTest() {
    }
    
    @Before
    public void setUp() {
        solicitudData = new SolicitudData();
        solicitud = new Solicitud();
    }
    
    @Test
    public void solicitud() throws SQLException{
        solicitud.getSolicitante().setCedula("4-4444-4444");
        solicitud.getOferta().setId(7);
//        solicitudData.insertarSolicitud(solicitud);
//        solicitudData.eliminarSolicitud(12);
        
        for (Solicitud solicitudActual : solicitudData.buscarSolicitudesFiltradas("9",3)) {
            System.out.println(solicitudActual.getId() + " " + solicitudActual.getSolicitante().getCedula() + " " + solicitudActual.getOferta().getId());
        }
    }
    
    @After
    public void tearDown() {
    }
    
}
