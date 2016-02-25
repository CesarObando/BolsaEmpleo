package Dominio;

import java.sql.Date;

public class Solicitante {

    //Atributos
    private int id;
    private int edad;
    private int experienciaLaboral;
    private String cedula;
    private String nombre;
    private String apellidos;
    private String username;
    private String password;
    private String sexo;
    private String escolaridad;
    private String titulos;
    private String detalleExperienciaLaboral;
    private String telefonoFijo;
    private String telefonoMovil;
    private String correo;
    private String idiomas;
    private byte[] foto;
    private Date ultimaActualizacion;

    //Constructor vacio
    public Solicitante() {
    }

    //Constructor cargado

    public Solicitante(int id, int edad, int experienciaLaboral, String cedula, String nombre, String apellidos, String username, String password, String sexo, String escolaridad, String titulos, String detalleExperienciaLaboral, String telefonoFijo, String telefonoMovil, String correo, String idiomas, byte[] foto, Date ultimaActualizacion) {
        this.id = id;
        this.edad = edad;
        this.experienciaLaboral = experienciaLaboral;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.username = username;
        this.password = password;
        this.sexo = sexo;
        this.escolaridad = escolaridad;
        this.titulos = titulos;
        this.detalleExperienciaLaboral = detalleExperienciaLaboral;
        this.telefonoFijo = telefonoFijo;
        this.telefonoMovil = telefonoMovil;
        this.correo = correo;
        this.idiomas = idiomas;
        this.foto = foto;
        this.ultimaActualizacion = ultimaActualizacion;
    }

    //Setter-Getter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getExperienciaLaboral() {
        return experienciaLaboral;
    }

    public void setExperienciaLaboral(int experienciaLaboral) {
        this.experienciaLaboral = experienciaLaboral;
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

    public Date getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(Date ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }
}
