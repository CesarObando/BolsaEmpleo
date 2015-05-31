/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import Data.OferenteData;
import Dominio.Oferente;
import Dominio.Usuario;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author ricardo
 */
public class OferenteBusiness {

    OferenteData oferenteData;
    public OferenteBusiness() {
        oferenteData=new OferenteData();
    }
    
    public LinkedList<String []> getNumeroOferentesPorEspecialidad() throws SQLException{
        return oferenteData.getNumeroOferentesPorEspecialidad();
        
    }
    
    public void ingresarOferenteVacante(String cedulaOferente, int idVacante) throws SQLException {
        oferenteData.ingresarOferenteVacante(cedulaOferente, idVacante);
    }
    
    public LinkedList<Oferente> getPostulantes() throws SQLException{
        return oferenteData.getPostulantes();
    }
    
    public void eliminarOferenteVacante(String cedulaOferente, int idVacante) throws SQLException{
        oferenteData.eliminarOferenteVacante(cedulaOferente, idVacante);
    }
    
    public LinkedList<Oferente> getPostulantesPorVacante(int idVacante) throws SQLException{
        return oferenteData.getPostulantesPorVacante(idVacante);
    }
    
    public Oferente getPostulante(String cedula) throws SQLException{
        return oferenteData.getPostulante(cedula);
    }
    
    public void borrarPostulante(String cedulaOferente) throws SQLException{
        oferenteData.borrarPostulante(cedulaOferente);
    }
    
    public void cargarOferenete(Oferente oferente) throws SQLException{
        oferenteData.cargarOferenete(oferente);
    }
    
    public void agregarOferente(Oferente oferente) throws SQLException, FileNotFoundException {
        oferenteData.agregarOferente(oferente);
    }
    
    public void cambiarUsuarioOferente(Usuario usuario) throws SQLException {
        oferenteData.cambiarUsuarioOferente(usuario);
    }
    
    public void actualizarCurriculo(Usuario usuario, File archivoCurriculo) throws SQLException, FileNotFoundException {
        oferenteData.actualizarCurriculo(usuario, archivoCurriculo);
    }
    
}
