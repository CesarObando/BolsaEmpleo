package Data;

import Dominio.Categoria;
import Exception.DataException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;

public class CategoriaData extends BaseData {

    //Constructor vacio
    public CategoriaData() {
    }

    public Categoria insertarCategoria(Categoria categoria) throws SQLException, DataException {
        //Creacion de la conexion a la base de datos
        Connection conexion = super.getConnection();
        //Definicion del procedimiento almacenado
        String sqlInsertarCategoria = "{Call insertar_categoria (?, ?)}";
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlInsertarCategoria);
            //Propiedad de la conexion para evitar finalizar la transaccion automaticamente
            conexion.setAutoCommit(false);
            //Definicion del parametro de salida
            statement.registerOutParameter(1, Types.INTEGER);
            //Definicion de los parametros que recibe el procedimiento almacenado
            statement.setString(2, categoria.getNombre());
            //Ejecucion del procedimiento almacenado
            statement.executeUpdate();
            //Recuperacion del parametro de salida
            categoria.setId(statement.getInt(1));
            //Finaliza la transaccion
            conexion.commit();
        } catch (SQLException e) {
            //Reversa la transaccion
            conexion.rollback();
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno del objeto
        return categoria;
    }

    public Categoria editarCategoria(Categoria categoria) throws SQLException, DataException {
        //Creacion de la conexion a la base de datos
        Connection conexion = super.getConnection();
        //Definicion del procedimiento almacenado
        String sqlEditarCategoria = "{Call editar_categoria (?,?)}";
        //Propiedad de la conexion para evitar finalizar la transaccion automaticamente
        conexion.setAutoCommit(false);
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlEditarCategoria);
            //Definicion de los parametros que recibe el procedimiento almacenado
            statement.setInt(1, categoria.getId());
            statement.setString(2, categoria.getNombre());
            //Ejecucion del procedimiento almacenado
            statement.executeUpdate();
            //Finaliza la transaccion
            conexion.commit();
        } catch (SQLException e) {
            //Reversa la transaccion
            conexion.rollback();
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno del objeto
        return categoria;
    }

    public void eliminarCategoria(int idCategoria) throws SQLException, DataException {
        //Creacion de la conexion a la base de datos
        Connection conexion = super.getConnection();
        //Definicion del procedimiento almacenado
        String sqlEliminarCategoria = "{CALL eliminar_categoria (?)}";
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlEliminarCategoria);
            //Definicion del parametro que recibe el procedimiento almacenado
            statement.setInt(1, idCategoria);
            //Ejecucion del procedimiento almacenado
            statement.executeUpdate();
            //Finaliza la transaccion
            conexion.commit();
        } catch (SQLException e) {
            //Reversa la transaccion
            conexion.rollback();
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
    }

    public LinkedList<Categoria> buscarCategorias() throws SQLException, DataException {
        //Definicion de la lista de objetos a consultar
        LinkedList<Categoria> categorias = new LinkedList<>();
        //Creacion de la conexion a la base de datos
        Connection conexion = super.getConnection();
        //Definicion del procedimiento almacenado
        String sqlBuscarCategorias = "{CALL buscar_categorias}";
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlBuscarCategorias);
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Recorrido del resultado de busqueda
            while (resultSet.next()) {
                //Definicion de un objeto para asignarle cada atributo contenido en un resultado de busqueda
                Categoria categoriaActual = new Categoria();
                //Asignacion de atributos al objeto
                categoriaActual.setId(resultSet.getInt("id"));
                categoriaActual.setNombre(resultSet.getString("nombre"));
                //Inclusion del objeto a la lista
                categorias.add(categoriaActual);
            }
        } catch (SQLException e) {
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno de la lista
        return categorias;
    }

    public LinkedList<Categoria> buscarCategoriasFiltradas(String nombre) throws SQLException, DataException {
        //Definicion de la lista de objetos a consultar
        LinkedList<Categoria> categorias = new LinkedList<>();
        //Creacion de la conexion a la base de datos
        Connection conexion = super.getConnection();
        //Definicion del procedimiento almacenado
        String sqlBuscarCategoriasFiltradas = "{CALL buscar_categorias_filtradas (?)}";
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlBuscarCategoriasFiltradas);
            //Definicion del parametro que recibe el procedimiento almacenado
            statement.setString(1, nombre);
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Recorrido del resultado de busqueda
            while (resultSet.next()) {
                //Definicion de un objeto para asignarle cada atributo contenido en un resultado de busqueda
                Categoria categoriaActual = new Categoria();
                //Asignacion de atributos al objeto
                categoriaActual.setId(resultSet.getInt("id"));
                categoriaActual.setNombre(resultSet.getString("nombre"));
                //Inclusion del objeto a la lista
                categorias.add(categoriaActual);
            }
        } catch (SQLException e) {
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno de la lista
        return categorias;
    }

    public Categoria buscarCategoria(int idCategoria) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlBuscarCategoria = "{CALL buscar_categoria(?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        //Definicion del objeto a consultar
        Categoria categoria = new Categoria();
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlBuscarCategoria);
            //Definicion del parametro que recibe el procedimiento almacenado
            statement.setInt(1, idCategoria);
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Si hay resultado de busqueda
            if (resultSet.next()) {
                //Asignacion de atributos al objeto
                categoria.setId(resultSet.getInt("id"));
                categoria.setNombre(resultSet.getString("nombre"));
            }
        } catch (SQLException e) {
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno del objeto
        return categoria;
    }

}
