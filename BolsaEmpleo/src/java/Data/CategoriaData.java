/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Dominio.Categoria;
import Exception.DataException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;

/**
 *
 * @author Cesar
 */
public class CategoriaData extends BaseData {

    public CategoriaData() {
    }

    public Categoria insertarCategoria(Categoria categoriaAInsertar) throws SQLException, DataException {
        Connection conexion = super.getConnection();
        String sqlInsert = "{Call insertar_categoria (?, ?)}";
        try {
            CallableStatement statement = conexion.prepareCall(sqlInsert);
            statement.registerOutParameter(1, Types.INTEGER);
            statement.setString(2, categoriaAInsertar.getNombre());
            statement.executeUpdate();
            categoriaAInsertar.setId(statement.getInt(1));
            conexion.commit();
        } catch (Exception e) {
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
        return categoriaAInsertar;
    }

    public Categoria editarCategoria(Categoria categoriaAEditar) throws SQLException, DataException {
        Connection conexion = super.getConnection();
        String sqlEdit = "{Call editar_categoria (?,?)}";
        try {
            CallableStatement statement = conexion.prepareCall(sqlEdit);
            statement.setInt(1, categoriaAEditar.getId());
            statement.setString(2, categoriaAEditar.getNombre());
            statement.executeUpdate();
            conexion.commit();
        } catch (Exception e) {
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
        return categoriaAEditar;
    }

    public void eliminarCategoria(int idCategoria) throws SQLException, DataException {
        Connection conexion = super.getConnection();
        String sqlDelete = "{CALL eliminar_categoria (?)}";
        try {
            CallableStatement statement = conexion.prepareCall(sqlDelete);
            statement.setInt(1, idCategoria);
            statement.executeUpdate();
            conexion.commit();
        } catch (Exception e) {
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
    }

    public LinkedList<Categoria> getCategorias() throws SQLException, DataException {
        LinkedList<Categoria> categorias = new LinkedList<Categoria>();
        Connection conexion = super.getConnection();
        String sqlSelect = "{CALL buscar_categorias ()}";
        try {
            CallableStatement statement = conexion.prepareCall(sqlSelect);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                categorias.add(categoria);
            }
        } catch (Exception e) {
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
        return categorias;
    }

}
