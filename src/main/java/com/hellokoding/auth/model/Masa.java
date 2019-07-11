package com.hellokoding.auth.model;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name="mese")
public class Masa {
    private Long id;
    private List<Comanda> comenzi;
    private int sters;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @OneToMany(fetch = FetchType.EAGER,cascade=ALL, mappedBy="masa")

    public List<Comanda> getComenzi() {
        return comenzi;
    }

    public void setComenzi(List<Comanda> comenzi) {
        this.comenzi = comenzi;
    }

    public int getSters() {
        return sters;
    }

    public void setSters(int sters) {
        this.sters = sters;
    }
}
