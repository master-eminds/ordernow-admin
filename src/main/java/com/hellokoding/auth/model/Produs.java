package com.hellokoding.auth.model;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name="produse")
public class Produs {
    private Long id;
    private String denumire;
    private Float pret;
    private Integer gramaj;
    private String descriere;
    private String urlImagine;
    private Categorie categorie;
    private Meniu meniu;

    private Set<ItemComanda> listaItemComanda;
    private Set<Review> reviews;

    public Produs(Long id, String denumire, Float pret, Integer gramaj, String descriere, Meniu meniu, Set<ItemComanda> listaItemComanda, Set<Review> reviews) {
        this.id = id;
        this.denumire = denumire;
        this.pret = pret;
        this.gramaj = gramaj;
        this.descriere = descriere;
        this.meniu = meniu;
        this.listaItemComanda = listaItemComanda;
        this.reviews = reviews;
    }

    public Produs() {
        this.categorie=new Categorie();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public Float getPret() {
        return pret;
    }

    public void setPret(Float pret) {
        this.pret = pret;
    }

    public Integer getGramaj() {
        return gramaj;
    }

    public void setGramaj(Integer gramaj) {
        this.gramaj = gramaj;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }
    @ManyToOne
    @JoinColumn(name="categorie_id", nullable=false)
    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @ManyToOne
    @JoinColumn(name="meniu_id", nullable=false)
    public Meniu getMeniu() {
        return meniu;
    }

    public void setMeniu(Meniu meniu) {
        this.meniu = meniu;
    }
    @OneToMany(cascade=ALL, mappedBy="produs")
    public Set<ItemComanda> getListaItemComanda() {
        return listaItemComanda;
    }

    public void setListaItemComanda(Set<ItemComanda> listaItemComanda) {
        this.listaItemComanda = listaItemComanda;
    }

    public String getUrlImagine() {
        return urlImagine;
    }

    public void setUrlImagine(String urlImagine) {
        this.urlImagine = urlImagine;
    }

    @OneToMany(cascade=ALL, mappedBy="produs")
    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return
                denumire + "   "+
                pret +" lei";

    }
}
