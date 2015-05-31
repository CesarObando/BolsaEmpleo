/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Data.ServicioData;
import Dominio.Servicio;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Dalaia
 */
public class ServicioBusiness {

    private final ServicioData servicioData;

    public ServicioBusiness() {
        servicioData = new ServicioData();
    }

    public void ingresarServicio(Servicio servicio, String cedula) throws SQLException {
        servicioData.ingresarServicio(servicio, cedula);
    }

    public LinkedList<Servicio> obtenerServicios() throws SQLException {
        return servicioData.obtenerServicios();
    }
    
    public LinkedList<Servicio> obtenerServicios(String cedula) throws SQLException {
        return servicioData.obtenerServicios(cedula);
    }
    
    public Servicio obtenerServicio(int idServicio) throws SQLException {
        return servicioData.obtenerServicio(idServicio);
    }

    public void actualizarServicio(Servicio servicio) throws SQLException {
        servicioData.actualizarServicio(servicio);
    }

    public void eliminarServicio(int idServicio) throws SQLException {
        servicioData.eliminarServicio(idServicio);
    }

    /**
     * Idea, el sistema se basa poner más enfasis en la segunda y terecer
     * palabra, y si encuentra un match de todas las palabras la pone de primer
     * en la lista. Quita las preposiciones ya que casi no importan y da puntos
     * de igualdades La búsqueda es sumamente toda, solo eso hace por los
     * criterios que se tomaron como los más relevantes
     *
     * @param busqueda es la frase de busqueda, por ejemplo "Cortar cesped",
     * "Clases de Matamáticas"
     * @param provincia por si la persona prefiere una provincia
     * @return lista ordenada basada en las igualidades
     * @throws SQLException
     */
    public LinkedList<Servicio> buscarServicio(String busqueda, String provincia) throws SQLException {
        LinkedList<Servicio> servicios = servicioData.obtenerServicios();
        String[] palabras = busqueda.split(" ");//hace un vector con las palabras para ver cada palabra que concuerde
        int[] orden = new int[servicios.size()];//para llevar un conteo y igualdades en cada servicio

        for (int i = 0; i < servicios.size(); i++) {
            Servicio s = servicios.get(i);
            for (String palabraActual : palabras) {
                if (!palabraActual.equalsIgnoreCase("de") && !palabraActual.equalsIgnoreCase("del")
                        && !palabraActual.equalsIgnoreCase("el") && !palabraActual.equalsIgnoreCase("la")
                        && !palabraActual.equalsIgnoreCase("con") && !palabraActual.equalsIgnoreCase("en")
                        && !palabraActual.equalsIgnoreCase("a") && !palabraActual.equalsIgnoreCase("por")) {//quita algunas preposiciones que se consideran irelevantes para una búsqueda

                    if (s.getNombre().toLowerCase().contains(palabraActual.toLowerCase())
                            || s.getDescripcion().toLowerCase().contains(palabraActual.toLowerCase())) {//ve si la palabra concuerda con la descripción o título
                        orden[i]++;//suma la cantidad de igualdades
                        if (i == 2 || i == 3) {
                            orden[i]++;//da más puntaje a las segundas o terceras palabras ya que considero que son las importantes "Venta autos", importa más autos que venta
                        }
                    }
                }
            }
           // if (orden[i] != 0) {
                if (s.getProvincia().equals(provincia)) {//agrega otro punto de igualdad por si conside la provincia, es contains porque puede que no tenga provincia y el " " es un criterio
                    orden[i]++;
                }
          //  }
            if(provincia.equals("GAM")){
                if (s.getProvincia().equals("Cartago") || s.getProvincia().equals("San José") 
                        || s.getProvincia().equals("Heredia") || s.getProvincia().equals("Alajuela") 
                        || s.getProvincia().equals("GAM")) {
                    orden[i]++;
                }
                else
                    orden[i]--;
            }
            if(s.getProvincia().equals("GAM")){
                if (provincia.equals("Cartago") || provincia.equals("San José") 
                        || provincia.equals("Heredia") || provincia.equals("Alajuela")){
                    orden[i]++;
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

        LinkedList<Servicio> serviciosOrdenados = new LinkedList<>();
        for (int aActual : a) {
            if (aActual == -1) {
                break;//rompe si está en 0 ya que esos no pegaron en nada
            }
            serviciosOrdenados.add(servicios.get(aActual));//agrega el servicio que tuvo el mayor puntaje de igualdad al menor
        }

        return serviciosOrdenados;
    }

}
