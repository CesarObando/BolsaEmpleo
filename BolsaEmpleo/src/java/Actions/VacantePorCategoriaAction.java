/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Actions;

import Business.VacanteBusiness;
import Dominio.Vacante;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author ricardo
 */
public class VacantePorCategoriaAction extends ActionSupport implements Preparable, ServletRequestAware{
    
    private HttpServletRequest request;
    private LinkedList<Vacante> vacantes;
    private int idCategoria;
    private String nombreCategoria;
    
    @Override
    public void prepare() throws Exception {
        this.idCategoria=Integer.parseInt(this.request.getParameter("idCategoria"));
        vacantes=new VacanteBusiness().getVacantesPorCategoria(idCategoria);
        if(!vacantes.isEmpty()){
        nombreCategoria=vacantes.element().getCategoria().getNombre();
        }
    }
    
    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public LinkedList<Vacante> getVacantes() {
        return vacantes;
    }

    public void setVacantes(LinkedList<Vacante> vacantes) {
        this.vacantes = vacantes;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }
}
