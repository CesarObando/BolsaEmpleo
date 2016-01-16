/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.CategoriaBusiness;
import Business.EmpleadorBusiness;
import Dominio.Administrador;
import Dominio.Categoria;
import Dominio.Empleador;
import Dominio.Oferta;
import Exception.DataException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;

/**
 *
 * @author Tin
 */
public class OfertaData extends BaseData {

    public OfertaData() {
    }

    //inserta una nueva oferta 
    public Oferta insertarOferta(Oferta newOferta) throws SQLException {
        Connection conexion = super.getConnection();
        String sqlInsert = "{CALL insertar_oferta(?,?,?,?,?,?,?,?)}";
        CallableStatement statement = conexion.prepareCall(sqlInsert);

        conexion.setAutoCommit(false);
        //cargamos el statement con la informacion nueva
        try {
            statement.registerOutParameter(1, Types.INTEGER);//variable de salida
            statement.setString(2, newOferta.getPuesto());
            statement.setInt(3, newOferta.getEmpleador().getId());
            statement.setFloat(4, newOferta.getSalario());
            statement.setInt(5, newOferta.getCantidadVacantes());
            statement.setString(6, newOferta.getRequerimientos());
            statement.setInt(7, newOferta.getCategoria().getId());
            statement.setString(8, newOferta.getDescripcion());
            statement.executeUpdate();

            newOferta.setId(statement.getInt(1));//solitamos el id generado

            conexion.commit();
        } catch (SQLException e) {
            conexion.rollback();
            throw e;
        }
        conexion.close();

        return newOferta;
    }

    public void editarOferta(Oferta oferta) throws SQLException {
        String sqlEditar = "{CALL editar_oferta(?,?,?,?,?)}";
        Connection conexion = this.getConnection();
        conexion.setAutoCommit(false);
        try {
            CallableStatement statement = conexion.prepareCall(sqlEditar);
            statement.setInt(1, oferta.getId());
            statement.setFloat(2, oferta.getSalario());
            statement.setInt(3, oferta.getCantidadVacantes());
            statement.setString(4, oferta.getRequerimientos());
            statement.setString(5, oferta.getDescripcion());
            statement.executeUpdate();
            conexion.commit();
        } catch (SQLException e) {
            conexion.rollback();
            throw e;
        }
        conexion.close();
    }

    public void eliminarOferta(int id) throws SQLException {
        String sqlEliminar = "{CALL eliminar_oferta(?)}";
        Connection conexion = this.getConnection();
        conexion.setAutoCommit(false);
        try {
            CallableStatement statement = conexion.prepareCall(sqlEliminar);
            statement.setInt(1, id);
            statement.executeUpdate();
            conexion.commit();
        } catch (SQLException e) {
            conexion.rollback();
            throw e;
        }
        conexion.close();
    }

    //retorna todas las ofertas existentes
    public LinkedList<Oferta> getOfertas() throws SQLException {

        String sqlSelect = "buscar_ofertas";
        Connection conexion = super.getConnection();
        CallableStatement statement = conexion.prepareCall(sqlSelect);
        ResultSet result = statement.executeQuery();

        LinkedList<Oferta> ofertas = new LinkedList<Oferta>();
        while (result.next()) {
            Oferta oferta = new Oferta();
            oferta.setId(result.getInt("id"));
            oferta.setCantidadVacantes(result.getInt("cantidad_vacantes"));
            oferta.getCategoria().setId(result.getInt("categoria"));
            oferta.getEmpleador().setId(result.getInt("empleador"));
            oferta.setSalario(result.getFloat("salario"));
            oferta.setPuesto(result.getString("puesto"));
            oferta.setRequerimientos(result.getString("requerimentos"));
            oferta.setDescripcion(result.getString("descripcion"));

            ofertas.add(oferta);
        }
        return ofertas;
    }

    public LinkedList<Oferta> getOfertasPorCategorias(int categoria, String puesto) throws SQLException {

        String sqlSelect = "{CALL buscar_ofertas_filtradas(?,?)}";
        Connection conexion = super.getConnection();
        CallableStatement statement = conexion.prepareCall(sqlSelect);
        statement.setString(1, puesto);
        statement.setInt(2, categoria);
        ResultSet result = statement.executeQuery();

        LinkedList<Oferta> ofertas = new LinkedList<Oferta>();
        while (result.next()) {
            Oferta oferta = new Oferta();
            oferta.setId(result.getInt("id"));
            oferta.setCantidadVacantes(result.getInt("cantidad_vacantes"));
            oferta.getCategoria().setId(result.getInt("categoria"));
            oferta.getEmpleador().setId(result.getInt("empleador"));
            oferta.setSalario(result.getFloat("salario"));
            oferta.setPuesto(result.getString("puesto"));
            oferta.setRequerimientos(result.getString("requerimentos"));
            oferta.setDescripcion(result.getString("descripcion"));

            ofertas.add(oferta);
        }
        return ofertas;
    }

    public LinkedList<Oferta> getOfertasPorEmpleador(int categoria, String puesto, int empleador) throws SQLException {

        String sqlSelect = "{CALL buscar_ofertas_por_empleador(?,?,?)}";
        Connection conexion = super.getConnection();
        CallableStatement statement = conexion.prepareCall(sqlSelect);
        statement.setString(1, puesto);
        statement.setInt(2, categoria);
        statement.setInt(3, empleador);
        ResultSet result = statement.executeQuery();

        LinkedList<Oferta> ofertas = new LinkedList<Oferta>();
        while (result.next()) {
            Oferta oferta = new Oferta();
            oferta.setId(result.getInt("id"));
            oferta.setCantidadVacantes(result.getInt("cantidad_vacantes"));
            oferta.getCategoria().setId(result.getInt("categoria"));
            oferta.getEmpleador().setId(result.getInt("empleador"));
            oferta.setSalario(result.getFloat("salario"));
            oferta.setPuesto(result.getString("puesto"));
            oferta.setRequerimientos(result.getString("requerimentos"));
            oferta.setDescripcion(result.getString("descripcion"));

            ofertas.add(oferta);
        }
        return ofertas;
    }

    public Oferta buscarOferta(int id) throws SQLException, DataException {
        String sqlBuscarOferta = "{CALL buscar_oferta(?)}";
        Connection conexion = this.getConnection();
        Oferta oferta = new Oferta();
        try {
            CallableStatement statement = conexion.prepareCall(sqlBuscarOferta);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                oferta.setId(result.getInt("id"));
                oferta.setCantidadVacantes(result.getInt("cantidad_vacantes"));
                oferta.getCategoria().setId(result.getInt("categoria"));
                oferta.getEmpleador().setId(result.getInt("empleador"));
                oferta.setSalario(result.getFloat("salario"));
                oferta.setPuesto(result.getString("puesto"));
                oferta.setRequerimientos(result.getString("requerimentos"));
                oferta.setDescripcion(result.getString("descripcion"));

                int idEmpleador = oferta.getEmpleador().getId();
                Empleador empleador = new Empleador();
                empleador = new EmpleadorBusiness().buscarEmpleador(idEmpleador);
                oferta.setEmpleador(empleador);

                int idCategoria = oferta.getCategoria().getId();
                Categoria categoria = new Categoria();
                categoria = new CategoriaBusiness().buscarCategoria(idCategoria);
                oferta.setCategoria(categoria);
            }
        } catch (SQLException e) {
            throw new DataException("Ha ocurrido un error con la base de datos");
        }
        conexion.close();
        return oferta;
    }
}
