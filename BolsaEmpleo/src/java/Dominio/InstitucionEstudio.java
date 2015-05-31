package Dominio;

public class InstitucionEstudio {
    private int idInstitucion;
    private String nombreInstitucion;

    public InstitucionEstudio() {
    }

    public InstitucionEstudio(int idInstitucion, String nombre) {
        this.idInstitucion = idInstitucion;
        this.nombreInstitucion = nombre;
    }

    public int getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(int idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public String getNombre() {
        return nombreInstitucion;
    }

    public void setNombre(String nombre) {
        this.nombreInstitucion = nombre;
    }

    @Override
    public String toString() {
        return nombreInstitucion;    
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
        final InstitucionEstudio other = (InstitucionEstudio) obj;
        return this.idInstitucion == other.idInstitucion;
    }
    
    
    
    
}
