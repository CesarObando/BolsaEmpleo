package Dominio;

public class Servicio {
    private int idServicio;
    private String nombre;
    private String descripcion;
    private String telefono;
    private String correo;
    private String provincia;
    private String ciudad;
    private String direccion;

    public Servicio() {
    }

    public Servicio(int idServicio, String nombre, String descripcion, String telefono, String correo, String provincia, String direccion, String ciudad) {
        this.idServicio = idServicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.telefono = telefono;
        this.correo = correo;
        this.provincia = provincia;
        this.direccion = direccion;
        this.ciudad = ciudad;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Servicio{" + "idServicio=" + idServicio + ", nombre=" + nombre + ", descripcion=" + descripcion + ", telefono=" + telefono + ", correo=" + correo + ", provincia=" + provincia + ", direccion=" + direccion + ", ciudad=" + ciudad + '}';
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
        final Servicio other = (Servicio) obj;
        return this.idServicio == other.idServicio;
    }
    
    
}
