package Dominio;


public class AreaEspecialidad {
    private int idAreaEspecialidad;
    private String descripcion;

    public AreaEspecialidad() {
    }

    public AreaEspecialidad(int idAreaEspecialidad, String descripcion) {
        this.idAreaEspecialidad = idAreaEspecialidad;
        this.descripcion = descripcion;
    }

    public int getIdAreaEspecialidad() {
        return idAreaEspecialidad;
    }

    public void setIdAreaEspecialidad(int idAreaEspecialidad) {
        this.idAreaEspecialidad = idAreaEspecialidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AreaEspecialidad other = (AreaEspecialidad) obj;
        return this.idAreaEspecialidad == other.idAreaEspecialidad;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.idAreaEspecialidad;
        return hash;
    }
    
    
}
