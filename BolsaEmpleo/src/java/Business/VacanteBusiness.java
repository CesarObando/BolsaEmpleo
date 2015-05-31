/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import Data.VacanteData;
import Dominio.Vacante;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author ricardo
 */
public class VacanteBusiness {
    
    VacanteData vacanteData;

    public VacanteBusiness() {
        vacanteData=new VacanteData();
    }
    
    public LinkedList<String []> getNumeroVacantesPorCategoria() throws SQLException{
        return vacanteData.getNumeroVacantesPorCategoria();
    }
    
    public void crearVacante(Vacante vacante, String cedulaEmpleador) throws SQLException{
        vacanteData.crearVacante(vacante, cedulaEmpleador);
    }
    
    public void borrarVacante(int idVacante) throws SQLException{
        vacanteData.borrarVacante(idVacante);
    }
    
    public LinkedList<Vacante> obtenerVacantes() throws SQLException {
        return vacanteData.obtenerVacantes();
    }
    
    public Vacante obtenerVacante(int idPuesto) throws SQLException{
        return vacanteData.obtenerVacante(idPuesto);
    }
    
    public LinkedList<Vacante> obtenerVacantesOferente(String cedula) throws SQLException{
        return vacanteData.obtenerVacantesOferente(cedula);
    }
    
    public LinkedList<Vacante> obtenerVacantesEmpleador(String cedula) throws SQLException{
        return vacanteData.obtenerVacantesEmpleador(cedula);
    }
    
    public LinkedList<Vacante> getVacantesPorCategoria(int idCategoria) throws SQLException{ 
        return vacanteData.getVacantesPorCategoria(idCategoria);
    }
    
    public void actualizarVacante(Vacante vacante) throws SQLException{
        vacanteData.actualizarVacante(vacante);
    }
    
    public LinkedList<Vacante> buscarVacantes(String nombreCategoria, String provincia, String busqueda) throws SQLException {
        LinkedList<Vacante> vacantes = vacanteData.obtenerVacantes();
        String[] palabras = busqueda.split(" ");//hace un vector con las palabras para ver cada palabra que concuerde
        int[] orden = new int[vacantes.size()];//para llevar un conteo y igualdades en cada servicio

        for (int i = 0; i < vacantes.size(); i++) {
            Vacante v = vacantes.get(i);
            for (String palabraActual : palabras) {
                if (!palabraActual.equalsIgnoreCase("de") && !palabraActual.equalsIgnoreCase("del")
                        && !palabraActual.equalsIgnoreCase("el") && !palabraActual.equalsIgnoreCase("la")
                        && !palabraActual.equalsIgnoreCase("con") && !palabraActual.equalsIgnoreCase("en")
                        && !palabraActual.equalsIgnoreCase("a") && !palabraActual.equalsIgnoreCase("por")) {//quita algunas preposiciones que se consideran irelevantes para una búsqueda

                    if (v.getDescripcion().toLowerCase().contains(palabraActual.toLowerCase())) {//ve si la palabra concuerda con la descripción o título
                        orden[i]++;//suma la cantidad de igualdades
                        if (i == 2 || i == 3) {
                            orden[i]++;//da más puntaje a las segundas o terceras palabras ya que considero que son las importantes "Venta autos", importa más autos que venta
                        }
                    }
                    
                    if (v.getProvincia().toLowerCase().contains(provincia.toLowerCase())) {//ve si la palabra concuerda con la provincia
                        orden[i]++;//suma la cantidad de igualdades
                    }
                    
                    if (v.getCategoria().getNombre().toLowerCase().contains(nombreCategoria.toLowerCase())) {//ve si la palabra concuerda con la categoria
                        orden[i]++;//suma la cantidad de igualdades
                    }
                }
            }
        }//da cantidad de igualdades

        int[] a = new int[orden.length];//nuevo arreglo para ordenar las igualdades
        for(int i = 0; i < a.length; i++){
            a[i] = -1;
        }

        for (int i = 0; i < orden.length; i++) {//busca cuál es el que tiene mayor puntaje de igualdad en "orden" y lo guarda en "a"
            int mayor = 0;
            int indiceMayor = 0;
            for (int j = 0; j < orden.length; j++) {//busca cuál es el mayor
                if (orden[j] > mayor) {
                    mayor = orden[j];//guarda el valor mayor
                    indiceMayor = j;//guarda el índice para guardarlo en "a"
                }
            }
            if (mayor == 0) {
                break;//si queda en 0 rompe para no gastar memoria
            }
            a[i] = indiceMayor;//guarde el índice donde está el mayor en a
            orden[indiceMayor] = -1;//donde estaba el número lo pone en 0 para que no lo cuente otra vez
        }

        LinkedList<Vacante> vacantesOrdenadas = new LinkedList<>();
        for (int aActual : a) {
            if (aActual == -1) {
                break;//rompe si está en 0 ya que esos no pegaron en nada
            }
            vacantesOrdenadas.add(vacantes.get(aActual));//agrega el servicio que tuvo el mayor puntaje de igualdad al menor
        }

        return vacantesOrdenadas;
    }
}
