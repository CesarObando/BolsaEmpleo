package Dominio;


public class CategoriaVacante {
   
    private int idCategoria;
    private String nombre;

    public CategoriaVacante() {
    }

    public CategoriaVacante(int idCategoria, String nombre) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CategoriaVacante other = (CategoriaVacante) obj;
        return this.idCategoria == other.idCategoria;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.idCategoria;
        return hash;
    }
    
    
}
