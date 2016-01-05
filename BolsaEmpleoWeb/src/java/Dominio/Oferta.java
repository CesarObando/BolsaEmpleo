/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

/**
 *
 * @author Cesar
 */
public class Oferta {

    private int id;
    private String puesto;
    private Empleador empleador;
    private float salario;
    private int cantidadVacantes;
    private String requerimientos;
    private Categoria categoria;
    private String descripcion;

    public Oferta() {
        categoria = new Categoria();
        empleador = new Empleador();
    }

    public Oferta(int id, String puesto, Empleador empleador, float salario, int cantidadVacantes, String requerimientos, Categoria categoria, String descripcion) {
        this.id = id;
        this.puesto = puesto;
        this.empleador = empleador;
        this.salario = salario;
        this.cantidadVacantes = cantidadVacantes;
        this.requerimientos = requerimientos;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Empleador getEmpleador() {
        return empleador;
    }

    public void setEmpleador(Empleador empleador) {
        this.empleador = empleador;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public int getCantidadVacantes() {
        return cantidadVacantes;
    }

    public void setCantidadVacantes(int cantidadVacantes) {
        this.cantidadVacantes = cantidadVacantes;
    }

    public String getRequerimientos() {
        return requerimientos;
    }

    public void setRequerimientos(String requerimientos) {
        this.requerimientos = requerimientos;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
