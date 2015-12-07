/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Data.CategoriaData;
import Dominio.Categoria;
import Exception.DataException;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Cesar
 */

public class CategoriaBusiness {

    private CategoriaData categoriaData;

    public CategoriaBusiness() {
        categoriaData = new CategoriaData();
    }

    public Categoria insertarCategoria(Categoria categoriaAInsertar) throws SQLException, DataException {
        return categoriaData.insertarCategoria(categoriaAInsertar);
    }

    public Categoria editarCategoria(Categoria categoriaAEditar) throws SQLException, DataException {
        return categoriaData.editarCategoria(categoriaAEditar);
    }

    public void eliminarCategoria(int idCategoria) throws SQLException, DataException {
        categoriaData.eliminarCategoria(idCategoria);
    }

    public LinkedList<Categoria> getCategorias() throws SQLException, DataException {
        return categoriaData.getCategorias();
    }

    public LinkedList<Categoria> getCategoriasFiltradas(String nombre) throws SQLException, DataException{
        return categoriaData.getCategoriasFiltradas(nombre);
    }
    
    public Categoria buscarCategoria(int id) throws SQLException, DataException{
        return categoriaData.buscarCategoria(id);
    }
}
