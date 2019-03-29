package com.hellokoding.auth.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name="comenzi")
public class Comanda {

    private Long id;
    private Set<ItemComanda> listaItemComanda;
    private Ospatar ospatar;
    private Masa masa;
    private String data;
    private String stare;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @OneToMany(cascade=ALL, mappedBy="comanda")
    public Set<ItemComanda> getListaItemComanda() {
        return listaItemComanda;
    }

    public void setListaItemComanda(Set<ItemComanda> listaItemComanda) {
        this.listaItemComanda = listaItemComanda;
    }
    @ManyToOne
    @JoinColumn(name="ospatar_id", nullable=false)
    public Ospatar getOspatar() {
        return ospatar;
    }

    public void setOspatar(Ospatar ospatar) {
        this.ospatar = ospatar;
    }
    @ManyToOne
    @JoinColumn(name="masa_id", nullable=false)
    public Masa getMasa() {
        return masa;
    }

    public void setMasa(Masa masa) {
        this.masa = masa;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStare() {
        return stare;
    }

    public void setStare(String stare) {
        this.stare = stare;
    }
}