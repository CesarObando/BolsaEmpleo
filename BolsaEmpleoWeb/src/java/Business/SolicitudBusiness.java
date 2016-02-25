package Business;

import Data.SolicitudData;
import Dominio.Solicitud;
import Exception.DataException;
import java.sql.SQLException;
import java.util.LinkedList;

public class SolicitudBusiness {

    private final SolicitudData solicitudData;

    public SolicitudBusiness() {
        solicitudData = new SolicitudData();
    }

    public Solicitud insertarSolicitud(Solicitud solicitud) throws SQLException, DataException {
        return solicitudData.insertarSolicitud(solicitud);
    }

    public Solicitud editarSolicitud(Solicitud solicitud) throws SQLException, DataException {
        return solicitudData.editarSolicitud(solicitud);
    }

    public void eliminarSolicitud(int idSolicitud) throws SQLException, DataException {
        solicitudData.eliminarSolicitud(idSolicitud);
    }

    public LinkedList<Solicitud> buscarSolicitudes() throws SQLException, DataException {
        return solicitudData.buscarSolicitudes();
    }
    
    public LinkedList<Solicitud> buscarSolicitudesFavoritas(int idOferta) throws SQLException, DataException {
        return solicitudData.buscarSolicitudesFavoritas(idOferta);
    }

    public LinkedList<Solicitud> buscarSolicitudesFiltradas(int idSolicitante, int idOferta) throws SQLException, DataException {
        return solicitudData.buscarSolicitudesFiltradas(idSolicitante, idOferta);
    }

    public Solicitud buscarSolicitud(int id) throws SQLException, DataException {
        return solicitudData.buscarSolicitud(id);
    }

    public LinkedList<Solicitud> buscarSolicitudesPorCategoria(int categoria, String puesto) throws SQLException, DataException {
        return solicitudData.buscarSolicitudesPorCategoria(categoria, puesto);
    }
}
