package Dominio;


public class NivelEstudio {
    private int idNivelEstudio;
    private String descripcion;

    public NivelEstudio() {
    }

    public NivelEstudio(int idNivelEstudio, String descripcion) {
        this.idNivelEstudio = idNivelEstudio;
        this.descripcion = descripcion;
    }

    public int getIdNivelEstudio() {
        return idNivelEstudio;
    }

    public void setIdNivelEstudio(int idNivelEstudio) {
        this.idNivelEstudio = idNivelEstudio;
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
        final NivelEstudio other = (NivelEstudio) obj;
        return this.idNivelEstudio == other.idNivelEstudio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.idNivelEstudio;
        return hash;
    }
    
    
}
