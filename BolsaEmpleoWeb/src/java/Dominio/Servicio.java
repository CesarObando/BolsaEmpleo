package Dominio;

public class Servicio {

    //Atributos
    private int id;
    private String titulo;
    private String descripcion;
    private String provincia;
    private String canton;
    private byte[] foto;
    private Solicitante solicitante;
    private Categoria categoria;

    //Constructor vacio
    public Servicio() {
        solicitante = new Solicitante();
        categoria = new Categoria();
    }

    //Constructor cargado
    public Servicio(int id, String titulo, String descripcion, String provincia, String canton, byte[] foto, Solicitante solicitante, Categoria categoria) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.provincia = provincia;
        this.canton = canton;
        this.foto = foto;
        this.solicitante = solicitante;
        this.categoria = categoria;
    }

    //Setter-Getter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
