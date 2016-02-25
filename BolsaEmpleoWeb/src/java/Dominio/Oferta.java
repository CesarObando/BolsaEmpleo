package Dominio;

public class Oferta {

    //Atributos
    private int id;
    private int cantidadVacantes;
    private float salario;
    private String puesto;
    private String requerimientos;
    private String descripcion;
    private String provincia;
    private String canton;
    private Empleador empleador;
    private Categoria categoria;
    
    //Constructor vacio
    public Oferta() {
        categoria = new Categoria();
        empleador = new Empleador();
    }

    //Constructor cargado
    public Oferta(int id, int cantidadVacantes, float salario, String puesto, String requerimientos, String descripcion, String provincia, String canton, Empleador empleador, Categoria categoria) {
        this.id = id;
        this.cantidadVacantes = cantidadVacantes;
        this.salario = salario;
        this.puesto = puesto;
        this.requerimientos = requerimientos;
        this.descripcion = descripcion;
        this.provincia = provincia;
        this.canton = canton;
        this.empleador = empleador;
        this.categoria = categoria;
    }
    
    //Setter-Getter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidadVacantes() {
        return cantidadVacantes;
    }

    public void setCantidadVacantes(int cantidadVacantes) {
        this.cantidadVacantes = cantidadVacantes;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getRequerimientos() {
        return requerimientos;
    }

    public void setRequerimientos(String requerimientos) {
        this.requerimientos = requerimientos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public Empleador getEmpleador() {
        return empleador;
    }

    public void setEmpleador(Empleador empleador) {
        this.empleador = empleador;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
