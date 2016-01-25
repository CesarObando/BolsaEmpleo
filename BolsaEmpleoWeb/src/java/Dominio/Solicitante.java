/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.sql.Date;

/**
 *
 * @author Cesar
 */
public class Solicitante {

    private int id;
    private String cedula;
    private String nombre;
    private String apellidos;
    private String username;
    private String password;
    private byte[] foto;
    private int edad;
    private String sexo;
    private String escolaridad;
    private String titulos;
    private int experienciaLaboral;
    private String detalleExperienciaLaboral;
    private String telefonoFijo;
    private String telefonoMovil;
    private String correo;
    private String idiomas;
    private Date ultimaActualizacion;

    public Solicitante() {
    }

    public Solicitante(int id, String cedula, String nombre, String apellidos, String username, String password, byte[] foto, int edad, String sexo, String escolaridad, String titulos, int experienciaLaboral, String detalleExperienciaLaboral, String telefonoFijo, String telefonoMovil, String correo, String idiomas) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.username = username;
        this.password = password;
        this.foto = foto;
        this.edad = edad;
        this.sexo = sexo;
        this.escolaridad = escolaridad;
        this.titulos = titulos;
        this.experienciaLaboral = experienciaLaboral;
        this.detalleExperienciaLaboral = detalleExperienciaLaboral;
        this.telefonoFijo = telefonoFijo;
        this.telefonoMovil = telefonoMovil;
        this.correo = correo;
        this.idiomas = idiomas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }

    public String getTitulos() {
        return titulos;
    }

    public void setTitulos(String titulos) {
        this.titulos = titulos;
    }

    public int getExperienciaLaboral() {
        return experienciaLaboral;
    }

    public void setExperienciaLaboral(int experienciaLaboral) {
        this.experienciaLaboral = experienciaLaboral;
    }

    public String getDetalleExperienciaLaboral() {
        return detalleExperienciaLaboral;
    }

    public void setDetalleExperienciaLaboral(String detalleExperienciaLaboral) {
        this.detalleExperienciaLaboral = detalleExperienciaLaboral;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getFotoString() {
        return new String(this.foto);
    }

    public Date getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(Date ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }
    
    
}
