/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Data.SolicitudData;
import Dominio.Solicitud;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author JonathanA
 */
public class SolicitudBusiness {

    private SolicitudData solicitudData;

    public SolicitudBusiness() {
        solicitudData = new SolicitudData();
    }

    public Solicitud insertarSolicitud(Solicitud solicitud) throws SQLException {
        return solicitudData.insertarSolicitud(solicitud);
    }

    public void eliminarSolicitud(int id) throws SQLException {
        solicitudData.eliminarSolicitud(id);
    }

    public LinkedList<Solicitud> buscarSolicitudes() throws SQLException {
        return solicitudData.buscarSolicitudes();
    }

    public LinkedList<Solicitud> buscarSolicitudesFiltradas(String solicitante, int oferta) throws SQLException {
        return solicitudData.buscarSolicitudesFiltradas(solicitante, oferta);
    }
}
