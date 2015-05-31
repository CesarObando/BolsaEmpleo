/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Actions;

import Business.CategoriaBusiness;
import Business.VacanteBusiness;
import Dominio.CategoriaVacante;
import Dominio.Vacante;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ricardo
 */
public class VerVacantesAction extends ActionSupport implements Preparable, ModelDriven{
    
    private LinkedList<Vacante> vacantes;
    private Vacante vacante;
    private String palabraClave, vacanteCategoria;
    private LinkedList<String> provincias;
    private List<CategoriaVacante> categorias;
    
    @Override
    public void prepare() throws Exception {
        cargarProvincias();
        palabraClave = vacanteCategoria = "";
        vacantes = new VacanteBusiness().obtenerVacantes();
        categorias = new CategoriaBusiness().getCategorias();
        categorias.add(0, new CategoriaVacante(-1, ""));
    }
    
    @Override
    public String execute() throws Exception {
        
        return INPUT;
    }
    
    public String buscarVacante(){
        try {
            vacantes = new VacanteBusiness().buscarVacantes (vacanteCategoria, vacante.getProvincia(), palabraClave);
        } catch (SQLException ex) {
            Logger.getLogger(VerVacantesAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return INPUT;
    }
    
    public void cargarProvincias(){
		provincias = new LinkedList<>();
		provincias.add("");
		provincias.add("GAM");
		provincias.add("San José");
		provincias.add("Cartago");
                provincias.add("Heredia");
                provincias.add("Alajuela");
                provincias.add("Limón");
                provincias.add("Puntarenas");
                provincias.add("Guanacaste");     
    }
    

    @Override
    public Object getModel() {
        return this.vacante;
    }

    public String getVacanteCategoria() {
        return vacanteCategoria;
    }

    public void setVacanteCategoria(String vacanteCategoria) {
        this.vacanteCategoria = vacanteCategoria;
    }

    public List<CategoriaVacante> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaVacante> categorias) {
        this.categorias = categorias;
    }
    
    public LinkedList<Vacante> getVacantes() {
        return vacantes;
    }

    public void setVacantes(LinkedList<Vacante> vacantes) {
        this.vacantes = vacantes;
    }

    public Vacante getVacante() {
        return vacante;
    }

    public void setVacante(Vacante vacante) {
        this.vacante = vacante;
    }

    public String getPalabraClave() {
        return palabraClave;
    }

    public void setPalabraClave(String palabraClave) {
        this.palabraClave = palabraClave;
    }

    public LinkedList<String> getProvincias() {
        return provincias;
    }

    public void setProvincias(LinkedList<String> provincias) {
        this.provincias = provincias;
    }
    
    
}
