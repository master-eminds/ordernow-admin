package com.hellokoding.auth.model;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name="categorii")
public class Categorie {

    private Long id;
    private String denumire;
    private String descriere;
    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(length=100000)
    private byte[] imagine;
    private Set<Produs> produse;
    private Meniu meniu;
    private String vizibilitate;

    public Categorie() {

    }

    public Categorie(String denumire) {

        this.denumire = denumire;
    }

    public Categorie(Meniu meniu) {
        this.meniu = meniu;
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
    @OneToMany(cascade=ALL, mappedBy="categorie")
    public Set<Produs> getProduse() {
        return produse;
    }

    public void setProduse(Set<Produs> produse) {
        this.produse = produse;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }


    public byte[] getImagine() {
        return imagine;
    }

    public void setImagine(byte[] imagine) {
        this.imagine = imagine;
    }

    @ManyToOne
    @JoinColumn(name="meniu_id", nullable=false)
    public Meniu getMeniu() {
        return meniu;
    }

    public void setMeniu(Meniu meniu) {
        this.meniu = meniu;
    }

    public String getVizibilitate() {
        return vizibilitate;
    }

    public void setVizibilitate(String vizibilitate) {
        this.vizibilitate = vizibilitate;
    }
}
