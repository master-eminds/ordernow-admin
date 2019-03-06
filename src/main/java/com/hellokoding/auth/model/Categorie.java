package com.hellokoding.auth.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name="categorii")
public class Categorie {

    private Long id;
    private String denumire;
    private String descriere;
    private Set<Produs> produse;
    public Categorie() {

    }

    public Categorie(String denumire) {

        this.denumire = denumire;
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
}
