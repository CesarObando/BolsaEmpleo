package Business;

import Data.ServicioData;
import Dominio.Servicio;
import Exception.DataException;
import java.sql.SQLException;
import java.util.LinkedList;

public class ServicioBusiness {

    private final ServicioData servicioData;

    public ServicioBusiness() {
        servicioData = new ServicioData();
    }

    public Servicio insertarServicio(Servicio servicio) throws SQLException, DataException {
        return servicioData.insertarServicio(servicio);
    }

    public void editarServicio(Servicio servicio) throws SQLException, DataException {
        servicioData.editarServicio(servicio);
    }

    public void eliminarServicio(int idServicio) throws SQLException, DataException {
        servicioData.eliminarServicio(idServicio);
    }

    public LinkedList<Servicio> buscarServicios() throws SQLException, DataException {
        return servicioData.buscarServicios();
    }

    public LinkedList<Servicio> buscarServiciosFiltrados(int categoria, String titulo, String provincia,String canton) throws SQLException, DataException {
        return servicioData.buscarServiciosFiltrados(categoria, titulo, provincia,canton);
    }

    public LinkedList<Servicio> buscarServiciosPorSolicitante(int categoria, String titulo, int solicitante, String provincia,String canton) throws SQLException, DataException {
        return servicioData.buscarServiciosPorSolicitante(categoria, titulo, solicitante, provincia,canton);
    }

    public Servicio buscarServicio(int idServicio) throws SQLException, DataException {
        return servicioData.buscarServicio(idServicio);
    }

}
