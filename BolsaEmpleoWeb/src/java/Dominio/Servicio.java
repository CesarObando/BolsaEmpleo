/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

/**
 *
 * @author JonathanA
 */
public class Servicio {

    private int id;
    private String titulo;
    private String descripcion;
    private Solicitante solicitante;
    private Categoria categoria;
    private String provincia;
    private String canton;
    private byte[] foto;

    public Servicio() {
        solicitante = new Solicitante();
        categoria = new Categoria();
    }

    public Servicio(int id, String titulo, String descripcion, Solicitante solicitante, Categoria categoria, String provincia, String canton, byte[] foto) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.solicitante = solicitante;
        this.categoria = categoria;
        this.provincia = provincia;
        this.canton = canton;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

}
