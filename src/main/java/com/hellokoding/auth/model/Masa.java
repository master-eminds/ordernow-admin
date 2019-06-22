package com.hellokoding.auth.model;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name="mese")
public class Masa {
    private Long id;
    private Set<Comanda> comenzi;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @OneToMany(cascade=ALL, mappedBy="masa")

    public Set<Comanda> getComenzi() {
        return comenzi;
    }

    public void setComenzi(Set<Comanda> comenzi) {
        this.comenzi = comenzi;
    }
}
