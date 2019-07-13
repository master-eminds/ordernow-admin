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
    private Double gramaj;
    private String unitateMasura;
    private String descriere;
    private String ingrediente;
    private String vizibilitate;
    private int sters;

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(length=100000)
    private byte[] imagine;
    private Categorie categorie;
    private Set<ItemComanda> listaItemComanda;
    private Set<Review> reviews;
    private Long meniu_id;

public Produs(){}

    public Produs(String denumire) {
        this.denumire = denumire;
    }

    public Produs(Categorie c) {
    this.categorie=c;
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

    public Double getGramaj() {
        return gramaj;
    }

    public void setGramaj(Double gramaj) {
        this.gramaj = gramaj;
    }

    public String getUnitateMasura() {
        return unitateMasura;
    }

    public void setUnitateMasura(String unitateMasura) {
        this.unitateMasura = unitateMasura;
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

    public String getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(String ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Long getMeniu_id() {
        return meniu_id;
    }

    public void setMeniu_id(Long meniu_id) {
        this.meniu_id = meniu_id;
    }

    public String getVizibilitate() {
        return vizibilitate;
    }

    public void setVizibilitate(String vizibilitate) {
        this.vizibilitate = vizibilitate;
    }

    public int getSters() {
        return sters;
    }

    public void setSters(int sters) {
        this.sters = sters;
    }
}
