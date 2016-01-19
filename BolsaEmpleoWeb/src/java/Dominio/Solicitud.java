/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

/**
 *
 * @author JonathanA
 */
public class Solicitud {

    private int id;
    private Solicitante solicitante;
    private Oferta oferta;
    private boolean favorito;

    public Solicitud() {
        this.solicitante = new Solicitante();
        this.oferta = new Oferta();
    }

    public Solicitud(int id, Solicitante solicitante, Oferta oferta, boolean favorito) {
        this.id = id;
        this.solicitante = solicitante;
        this.oferta = oferta;
        this.favorito = favorito;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

}
