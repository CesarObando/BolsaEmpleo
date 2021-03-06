package Dominio;

public class Administrador {
    
    //Atributos
    private int id;
    private String cedula;
    private String nombre;
    private String apellidos;
    private String username;
    private String password;

    //Constructor vacio
    public Administrador() {
    }

    //constructor cargado
    public Administrador(int id, String cedula, String nombre, String apellidos, String username, String password) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.username = username;
        this.password = password;
    }

    //Setter-Getter
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
}
