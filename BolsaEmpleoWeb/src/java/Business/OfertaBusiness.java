package Business;

import Data.OfertaData;
import Dominio.Oferta;
import Dominio.Solicitante;
import Exception.DataException;
import java.sql.SQLException;
import java.util.LinkedList;

public class OfertaBusiness {

    private final OfertaData ofertaData;

    public OfertaBusiness() {
        ofertaData = new OfertaData();
    }

    public Oferta insertaOferta(Oferta oferta) throws SQLException, DataException {
        return ofertaData.insertarOferta(oferta);
    }
    
    public void insertaOfertaFavorita(Oferta oferta, Solicitante solicitante) throws SQLException, DataException {
        ofertaData.insertarOfertaFavorita(oferta, solicitante);
    }
    
    public boolean buscarOfertaFavorita(Oferta oferta, Solicitante solicitante) throws SQLException, DataException{
        return ofertaData.buscarOfertaFavorita(oferta, solicitante);
    }
    
     public LinkedList<Oferta> buscarOfertasFavoritas(Solicitante solicitante) throws SQLException, DataException{
         return ofertaData.buscarOfertasFavoritas(solicitante);
     }

    public void editarOferta(Oferta oferta) throws SQLException, DataException {
        ofertaData.editarOferta(oferta);
    }

    public void eliminarOferta(int idOferta) throws SQLException, DataException {
        ofertaData.eliminarOferta(idOferta);
    }

    public LinkedList<Oferta> buscarOfertas() throws SQLException, DataException {
        return ofertaData.buscarOfertas();
    }

    public LinkedList<Oferta> buscarOfertasPorCategoria(int categoria, String puesto, String provincia,String canton) throws SQLException, DataException {
        return ofertaData.buscarOfertasPorCategoria(categoria, puesto, provincia,canton);
    }

    public Oferta buscarOferta(int idOferta) throws SQLException, DataException {
        return ofertaData.buscarOferta(idOferta);
    }

    public LinkedList<Oferta> buscarOfertasPorEmpleador(int categoria, String puesto, int empleador, String provincia,String canton) throws SQLException, DataException {
        return ofertaData.buscarOfertasPorEmpleador(categoria, puesto, empleador, provincia,canton);
    }
}
