package Business;

import Data.CategoriaData;
import Dominio.Categoria;
import Exception.DataException;
import java.sql.SQLException;
import java.util.LinkedList;

public class CategoriaBusiness {

    private final CategoriaData categoriaData;

    public CategoriaBusiness() {
        categoriaData = new CategoriaData();
    }

    public Categoria insertarCategoria(Categoria categoria) throws SQLException, DataException {
        return categoriaData.insertarCategoria(categoria);
    }

    public Categoria editarCategoria(Categoria categoria) throws SQLException, DataException {
        return categoriaData.editarCategoria(categoria);
    }

    public void eliminarCategoria(int idCategoria) throws SQLException, DataException {
        categoriaData.eliminarCategoria(idCategoria);
    }

    public LinkedList<Categoria> buscarCategorias() throws SQLException, DataException {
        return categoriaData.buscarCategorias();
    }

    public LinkedList<Categoria> buscarCategoriasFiltradas(String nombre) throws SQLException, DataException {
        return categoriaData.buscarCategoriasFiltradas(nombre);
    }

    public Categoria buscarCategoria(int idCategoria) throws SQLException, DataException {
        return categoriaData.buscarCategoria(idCategoria);
    }
}
