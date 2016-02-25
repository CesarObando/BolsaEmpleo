package Data;

import Business.CategoriaBusiness;
import Business.EmpleadorBusiness;
import Dominio.Categoria;
import Dominio.Empleador;
import Dominio.Oferta;
import Dominio.Solicitante;
import Exception.DataException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;

public class OfertaData extends BaseData {

    //Constructor vacio
    public OfertaData() {
    }

    public Oferta insertarOferta(Oferta oferta) throws SQLException, DataException {
        //Creacion de la conexion a la base de datos
        Connection conexion = super.getConnection();
        //Definicion del procedimiento almacenado
        String sqlInsertarOferta = "{CALL insertar_oferta(?,?,?,?,?,?,?,?,?,?)}";
        //Preparacion del procedimiento almacenado
        CallableStatement statement = conexion.prepareCall(sqlInsertarOferta);
        //Propiedad de la conexion para evitar finalizar la transaccion automaticamente
        conexion.setAutoCommit(false);
        try {
            //Definicion del parametro de salida
            statement.registerOutParameter(1, Types.INTEGER);
            //Definicion de los parametros que recibe el procedimiento almacenado
            statement.setString(2, oferta.getPuesto());
            statement.setInt(3, oferta.getEmpleador().getId());
            statement.setFloat(4, oferta.getSalario());
            statement.setInt(5, oferta.getCantidadVacantes());
            statement.setString(6, oferta.getRequerimientos());
            statement.setInt(7, oferta.getCategoria().getId());
            statement.setString(8, oferta.getDescripcion());
            statement.setString(9, oferta.getProvincia());
            statement.setString(10, oferta.getCanton());
            //Ejecucion del procedimiento almacenado
            statement.executeUpdate();
            //Recuperacion del parametro de salida
            oferta.setId(statement.getInt(1));
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
        return oferta;
    }

    public void insertarOfertaFavorita(Oferta oferta, Solicitante solicitante) throws SQLException, DataException {
        //Creacion de la conexion a la base de datos
        Connection conexion = super.getConnection();
        //Definicion del procedimiento almacenado
        String sqlInsertarOfertaFavorita = "{CALL insertar_oferta_favorita(?,?)}";
        //Preparacion del procedimiento almacenado
        CallableStatement statement = conexion.prepareCall(sqlInsertarOfertaFavorita);
        //Propiedad de la conexion para evitar finalizar la transaccion automaticamente
        conexion.setAutoCommit(false);
        try {
            //Definicion de los parametros que recibe el procedimiento almacenado
            statement.setInt(1, oferta.getId());
            statement.setInt(2, solicitante.getId());
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

    public boolean buscarOfertaFavorita(Oferta oferta, Solicitante solicitante) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlBuscarOfertaFavorita = "{CALL buscar_oferta_favorita(?,?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlBuscarOfertaFavorita);
            //Definicion de los parametros que recibe el procedimiento almacenado
            statement.setInt(1, oferta.getId());
            statement.setInt(2, solicitante.getId());
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Retorno del resultado
            return resultSet.next();
        } catch (SQLException e) {
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
    }

    public LinkedList<Oferta> buscarOfertasFavoritas(Solicitante solicitante) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlBuscarOfertasFavoritas = "{CALL buscar_ofertas_favoritas(?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        //Preparacion del procedimiento almacenado
        CallableStatement statement = conexion.prepareCall(sqlBuscarOfertasFavoritas);
        //Definicion del parametro que recibe el procedimiento almacenado
        statement.setInt(1, solicitante.getId());
        //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
        ResultSet resultSet = statement.executeQuery();
        //Definicion de la lista de objetos a consultar
        LinkedList<Oferta> ofertas = new LinkedList<>();
        //Recorrido del resultado de busqueda
        while (resultSet.next()) {
            //Busqueda del objeto a partir del resultado e inclusion del mismo a la lista
            ofertas.add(buscarOferta(resultSet.getInt("id_oferta")));
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno de la lista
        return ofertas;
    }

    public void editarOferta(Oferta oferta) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlEditarOferta = "{CALL editar_oferta(?,?,?,?,?,?,?,?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        //Propiedad de la conexion para evitar finalizar la transaccion automaticamente
        conexion.setAutoCommit(false);
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlEditarOferta);
            //Definicion de los parametros que recibe el procedimiento almacenado
            statement.setInt(1, oferta.getId());
            statement.setFloat(2, oferta.getSalario());
            statement.setInt(3, oferta.getCantidadVacantes());
            statement.setString(4, oferta.getRequerimientos());
            statement.setString(5, oferta.getDescripcion());
            statement.setInt(6, oferta.getCategoria().getId());
            statement.setString(7, oferta.getProvincia());
            statement.setString(8, oferta.getCanton());
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

    public void eliminarOferta(int idOferta) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlEliminarOferta = "{CALL eliminar_oferta(?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        //Propiedad de la conexion para evitar finalizar la transaccion automaticamente
        conexion.setAutoCommit(false);
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlEliminarOferta);
            //Definicion del parametro que recibe el procedimiento almacenado
            statement.setInt(1, idOferta);
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

    public LinkedList<Oferta> buscarOfertas() throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlBuscarOfertas = "buscar_ofertas";
        //Creacion de la conexion a la base de datos
        Connection conexion = super.getConnection();
        //Definicion de la lista de objetos a consultar
        LinkedList<Oferta> ofertas = new LinkedList<>();
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlBuscarOfertas);
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Recorrido del resultado de busqueda
            while (resultSet.next()) {
                //Definicion de un objeto para asignarle cada atributo contenido en un resultado de busqueda
                Oferta ofertaActual = new Oferta();
                //Asignacion de atributos al objeto
                ofertaActual.setId(resultSet.getInt("id"));
                ofertaActual.setCantidadVacantes(resultSet.getInt("cantidad_vacantes"));
                ofertaActual.getCategoria().setId(resultSet.getInt("categoria"));
                ofertaActual.getEmpleador().setId(resultSet.getInt("empleador"));
                ofertaActual.setSalario(resultSet.getFloat("salario"));
                ofertaActual.setPuesto(resultSet.getString("puesto"));
                ofertaActual.setRequerimientos(resultSet.getString("requerimentos"));
                ofertaActual.setDescripcion(resultSet.getString("descripcion"));
                ofertaActual.setProvincia(resultSet.getString("provincia"));
                ofertaActual.setCanton(resultSet.getString("canton"));
                //Inclusion del objeto a la lista
                ofertas.add(ofertaActual);
            }
        } catch (SQLException e) {
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno de la lista
        return ofertas;
    }

    public LinkedList<Oferta> buscarOfertasPorCategoria(int categoria, String puesto, String provincia, String canton) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlBuscarOfertasPorCategoria = "{CALL buscar_ofertas_filtradas(?,?,?,?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = super.getConnection();
        //Definicion de la lista de objetos a consultar
        LinkedList<Oferta> ofertas = new LinkedList<>();
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlBuscarOfertasPorCategoria);
            //Definicion de los parametros que recibe el procedimiento almacenado
            statement.setString(1, puesto);
            statement.setInt(2, categoria);
            statement.setString(3, provincia);
            statement.setString(4, canton);
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Recorrido del resultado de busqueda
            while (resultSet.next()) {
                //Definicion de un objeto para asignarle cada atributo contenido en un resultado de busqueda
                Oferta ofertaActual = new Oferta();
                //Asignacion de atributos al objeto
                ofertaActual.setId(resultSet.getInt("id"));
                ofertaActual.setCantidadVacantes(resultSet.getInt("cantidad_vacantes"));
                ofertaActual.getCategoria().setId(resultSet.getInt("categoria"));
                ofertaActual.getEmpleador().setId(resultSet.getInt("empleador"));
                ofertaActual.setSalario(resultSet.getFloat("salario"));
                ofertaActual.setPuesto(resultSet.getString("puesto"));
                ofertaActual.setRequerimientos(resultSet.getString("requerimentos"));
                ofertaActual.setDescripcion(resultSet.getString("descripcion"));
                ofertaActual.setProvincia(resultSet.getString("provincia"));
                ofertaActual.setCanton(resultSet.getString("canton"));
                //Inclusion del objeto a la lista
                ofertas.add(ofertaActual);
            }
        } catch (SQLException e) {
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno de la lista
        return ofertas;
    }

    public LinkedList<Oferta> buscarOfertasPorEmpleador(int categoria, String puesto, int empleador, String provincia, String canton) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlBuscarOfertasPorEmpleador = "{CALL buscar_ofertas_por_empleador(?,?,?,?,?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        //Definicion de la lista de objetos a consultar
        LinkedList<Oferta> ofertas = new LinkedList<>();
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlBuscarOfertasPorEmpleador);
            //Definicion de los parametros que recibe el procedimiento almacenado
            statement.setString(1, puesto);
            statement.setInt(2, categoria);
            statement.setString(3, provincia);
            statement.setString(4, canton);
            statement.setInt(5, empleador);
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Recorrido del resultado de busqueda
            while (resultSet.next()) {
                //Definicion de un objeto para asignarle cada atributo contenido en un resultado de busqueda
                Oferta ofertaActual = new Oferta();
                //Asignacion de atributos al objeto
                ofertaActual.setId(resultSet.getInt("id"));
                ofertaActual.setCantidadVacantes(resultSet.getInt("cantidad_vacantes"));
                ofertaActual.getCategoria().setId(resultSet.getInt("categoria"));
                ofertaActual.getEmpleador().setId(resultSet.getInt("empleador"));
                ofertaActual.setSalario(resultSet.getFloat("salario"));
                ofertaActual.setPuesto(resultSet.getString("puesto"));
                ofertaActual.setRequerimientos(resultSet.getString("requerimentos"));
                ofertaActual.setDescripcion(resultSet.getString("descripcion"));
                ofertaActual.setProvincia(resultSet.getString("provincia"));
                ofertaActual.setCanton(resultSet.getString("canton"));
                //Inclusion del objeto a la lista
                ofertas.add(ofertaActual);
            }
        } catch (SQLException e) {
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno de la lista
        return ofertas;
    }

    public Oferta buscarOferta(int idOferta) throws SQLException, DataException {
        //Definicion del procedimiento almacenado
        String sqlBuscarOferta = "{CALL buscar_oferta(?)}";
        //Creacion de la conexion a la base de datos
        Connection conexion = this.getConnection();
        //Definicion del objeto a consultar
        Oferta oferta = new Oferta();
        try {
            //Preparacion del procedimiento almacenado
            CallableStatement statement = conexion.prepareCall(sqlBuscarOferta);
            //Definicion del parametro que recibe el procedimiento almacenado
            statement.setInt(1, idOferta);
            //Ejecucion del procedimiento almacenado y recuperacion del resultado de busqueda
            ResultSet resultSet = statement.executeQuery();
            //Si hay resultado de busqueda
            if (resultSet.next()) {
                //Asignacion de atributos al objeto
                oferta.setId(resultSet.getInt("id"));
                oferta.setCantidadVacantes(resultSet.getInt("cantidad_vacantes"));
                oferta.getCategoria().setId(resultSet.getInt("categoria"));
                oferta.getEmpleador().setId(resultSet.getInt("empleador"));
                oferta.setSalario(resultSet.getFloat("salario"));
                oferta.setPuesto(resultSet.getString("puesto"));
                oferta.setRequerimientos(resultSet.getString("requerimentos"));
                oferta.setDescripcion(resultSet.getString("descripcion"));
                oferta.setProvincia(resultSet.getString("provincia"));
                oferta.setCanton(resultSet.getString("canton"));
                //Recuperacion del id del empleador de la oferta
                int idEmpleador = oferta.getEmpleador().getId();
                //Busqueda del empleador de la oferta
                Empleador empleador = new EmpleadorBusiness().buscarEmpleador(idEmpleador);
                //Asignacion del objeto empleador a la oferta
                oferta.setEmpleador(empleador);
                //Recuperacion del id de la categoria de la oferta
                int idCategoria = oferta.getCategoria().getId();
                //Busqueda de la categoria de la oferta
                Categoria categoria = new CategoriaBusiness().buscarCategoria(idCategoria);
                //Asignacion del objeto categoria a la oferta
                oferta.setCategoria(categoria);
            }
        } catch (SQLException e) {
            //Lanza la excepcion para mostrar el error
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        //Cierre de la conexion
        conexion.close();
        //Retorno del objeto
        return oferta;
    }

}
