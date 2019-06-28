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
    //aici ar trebui un byte[] imagine
    private String urlImagine;
    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(length=100000)
    private byte[] imagine;
    private Categorie categorie;
    private Set<ItemComanda> listaItemComanda;
    private Set<Review> reviews;

    public Produs(Long id, String denumire, Float pret, Integer gramaj, String descriere, Categorie categorie, Set<ItemComanda> listaItemComanda, Set<Review> reviews, byte[] imagine) {
        this.id = id;
        this.denumire = denumire;
        this.pret = pret;
        this.gramaj = gramaj;
        this.descriere = descriere;
        this.categorie = categorie;
        this.listaItemComanda = listaItemComanda;
        this.reviews = reviews;
        this.imagine=imagine;
    }
public Produs(){}



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

    public byte[] getImagine() {
        return imagine;
    }

    public void setImagine(byte[] imagine) {
        this.imagine = imagine;
    }
}
