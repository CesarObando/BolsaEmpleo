package Dominio;

import java.sql.Date;
import java.util.LinkedList;

public class Usuario {
     
    protected String cedula;
    protected String nombreUsuario;
    protected String clave;
    protected String nombre;
    protected String apellidos;
    protected String provincia;
    protected String ciudad;
    protected String direccion;
    protected int codigoPostal;
    protected String email;
    protected String telefonoCasa;
    protected String telefonoCelular;
    protected String fax;
    protected Date fechaNacimiento;
    protected boolean oferente;
    protected boolean empleador;
    protected boolean administrador;
    
    protected LinkedList<Servicio> servicios;

    public Usuario() {
        servicios = new LinkedList<>();
    }
    
    public Usuario(String cedula, String nombreUsuario, String clave, String nombre, String apellidos, String provincia, String ciudad, String direccion, int codigoPostal, String email, String telefonoCasa, String telefonoCelular, String fax, Date fechaNacimiento, boolean oferente, boolean empleador, boolean administrador, LinkedList<Servicio> servicios) {
        this.cedula = cedula;
        this.nombreUsuario = nombreUsuario;
        this.clave = clave;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.email = email;
        this.telefonoCasa = telefonoCasa;
        this.telefonoCelular = telefonoCelular;
        this.fax = fax;
        this.fechaNacimiento = fechaNacimiento;
        this.oferente = oferente;
        this.empleador = empleador;
        this.administrador = administrador;
        this.servicios = servicios;
    }
    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefonoCasa() {
        return telefonoCasa;
    }

    public void setTelefonoCasa(String telefonoCasa) {
        this.telefonoCasa = telefonoCasa;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isOferente() {
        return oferente;
    }

    public void setOferente(boolean oferente) {
        this.oferente = oferente;
    }

    public boolean isEmpleador() {
        return empleador;
    }

    public void setEmpleador(boolean empleador) {
        this.empleador = empleador;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public LinkedList<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(LinkedList<Servicio> servicios) {
        this.servicios = servicios;
    }

    @Override
    public String toString() {
        return "Usuario{" + "cedula=" + cedula + ", nombreUsuario=" + nombreUsuario + ", clave=" + clave + ", nombre=" + nombre + ", apellidos=" + apellidos + ", provincia=" + provincia + ", ciudad=" + ciudad + ", direccion=" + direccion + ", codigoPostal=" + codigoPostal + ", email=" + email + ", telefonoCasa=" + telefonoCasa + ", telefonoCelular=" + telefonoCelular + ", fax=" + fax + ", fechaNacimiento=" + fechaNacimiento + ", oferente=" + oferente + ", empleador=" + empleador + ", administrador=" + administrador + ", servicios=" + servicios + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        return this.cedula.equals(other.cedula);
    }
    
    
}
