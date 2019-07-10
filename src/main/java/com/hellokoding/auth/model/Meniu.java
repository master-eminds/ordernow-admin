package com.hellokoding.auth.model;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name="meniuri")
public class Meniu {
    private Long id;
    private String denumire;
    private String stare;
    private List<Categorie> categorii;
    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(length=100000)
    private byte[] image;
    private int sters;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(cascade=ALL, mappedBy="meniu")
    public List<Categorie> getCategorii() {
        return categorii;
    }

    public void setCategorii(List<Categorie> categorii) {
        this.categorii = categorii;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getStare() {
        return stare;
    }

    public void setStare(String stare) {
        this.stare = stare;
    }

    public int getSters() {
        return sters;
    }

    public void setSters(int sters) {
        this.sters = sters;
    }
    @Override
    public String toString() {
        return "Meniu: " + denumire +
                "Categorii: " + categorii ;
    }
}
