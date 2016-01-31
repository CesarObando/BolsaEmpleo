/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Data.SolicitudData;
import Dominio.Solicitud;
import Exception.DataException;
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

    public Solicitud editarSolicitud(Solicitud solicitudAEditar) throws SQLException, DataException {
        return solicitudData.editarSolicitud(solicitudAEditar);
    }

    public void eliminarSolicitud(int id) throws SQLException {
        solicitudData.eliminarSolicitud(id);
    }

    public LinkedList<Solicitud> buscarSolicitudes() throws SQLException {
        return solicitudData.buscarSolicitudes();
    }

    public LinkedList<Solicitud> buscarSolicitudesFiltradas(int solicitante, int oferta) throws SQLException {
        return solicitudData.buscarSolicitudesFiltradas(solicitante, oferta);
    }

    public Solicitud buscarSolicitud(int id) throws SQLException, DataException {
        return solicitudData.buscarSolicitud(id);
    }

    public LinkedList<Solicitud> getOfertasPorCategorias(int categoria, String puesto) throws SQLException {
        return solicitudData.getOfertasPorCategorias(categoria, puesto);
    }
}
