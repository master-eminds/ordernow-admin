package com.hellokoding.auth.model;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name="meniuri")
public class Meniu {
    private Long id;
    private String denumire;
    private Set<Categorie> categorii;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @OneToMany(cascade=ALL, mappedBy="meniu")
    public Set<Categorie> getCategorii() {
        return categorii;
    }

    public void setCategorii(Set<Categorie> categorii) {
        this.categorii = categorii;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    @Override
    public String toString() {
        return "Meniu: " + denumire +
                "Categorii: " + categorii ;
    }
}
