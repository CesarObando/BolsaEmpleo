/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Data.OfertaData;
import Dominio.Oferta;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Tin
 */
public class OfertaBusiness {
     
    private OfertaData ofertaData;

    public OfertaBusiness() {
       ofertaData = new OfertaData();
    }
    
    public Oferta insertaOferta(Oferta oferta) throws SQLException{
        return ofertaData.insertarOferta(oferta);
    
    }
    
    public void editarOferta(Oferta oferta) throws SQLException{
        
    ofertaData.editarOferta(oferta);
    
    }
    public void eliminarOferta(int id) throws SQLException{
        
     ofertaData.eliminarOferta(id);
    
    }
    
    public LinkedList<Oferta> getOfertas() throws SQLException{
    
    return ofertaData.getOfertas();
    }
     public LinkedList<Oferta> getOfertasPorCategoria(String nombreCategoria,String puesto) throws SQLException{
    
    return ofertaData.getOfertasPorCategorias(nombreCategoria, puesto);
    }
    
}
