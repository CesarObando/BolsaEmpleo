package Dominio;

public class Categoria {

    //Aributos
    private int id;
    private String nombre;

    //Constructor vacio
    public Categoria() {
    }

    //constructor cargado
    public Categoria(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    //Setter-Getter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
