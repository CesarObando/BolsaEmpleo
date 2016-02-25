package Dominio;

public class Solicitud {

    //Atributos
    private int id;
    private boolean favorito;
    private Solicitante solicitante;
    private Oferta oferta;
    
    //Constructor vacio
    public Solicitud() {
        this.solicitante = new Solicitante();
        this.oferta = new Oferta();
    }

    //Constructor cargado

    public Solicitud(int id, boolean favorito, Solicitante solicitante, Oferta oferta) {
        this.id = id;
        this.favorito = favorito;
        this.solicitante = solicitante;
        this.oferta = oferta;
    }
    
    //Setter-Getter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }
}
