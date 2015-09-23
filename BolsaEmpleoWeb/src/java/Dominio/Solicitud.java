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

    public Solicitud() {
        this.solicitante = new Solicitante();
        this.oferta = new Oferta();
    }

    public Solicitud(int id, Solicitante solicitante, Oferta oferta) {
        this.id = id;
        this.solicitante = solicitante;
        this.oferta = oferta;
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

}
