/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Data.ServicioData;
import Dominio.Servicio;
import Exception.DataException;
import java.sql.SQLException;
import java.util.LinkedList;

public class ServicioBusiness {

    private ServicioData servicioData;

    public ServicioBusiness() {
        servicioData = new ServicioData();
    }

    public Servicio insertarServicio(Servicio servicio) throws SQLException {
        return servicioData.insertarServicio(servicio);
    }

    public void editarServicio(Servicio servicio) throws SQLException {
        servicioData.editarServicio(servicio);
    }

    public void eliminarServicio(int id) throws SQLException {
        servicioData.eliminarServicio(id);
    }

    public LinkedList<Servicio> buscarServicios() throws SQLException {
        return servicioData.buscarServicios();
    }

    public LinkedList<Servicio> buscarServiciosFiltrados(int categoria, String titulo, String provincia,String canton) throws SQLException {
        return servicioData.buscarServiciosFiltrados(categoria, titulo, provincia,canton);
    }

    public LinkedList<Servicio> buscarServiciosPorSolicitante(int categoria, String titulo, int solicitante, String provincia,String canton) throws SQLException {
        return servicioData.buscarServiciosPorSolicitante(categoria, titulo, solicitante, provincia,canton);
    }

    public Servicio buscarServicio(int id) throws SQLException, DataException {
        return servicioData.buscarServicio(id);
    }

}
