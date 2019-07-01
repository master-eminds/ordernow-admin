package com.hellokoding.auth.model;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name="comenzi")
public class Comanda  implements  Comparable{

    private Long id;
    private Set<ItemComanda> listaItemComanda;
    private Ospatar ospatar;
    private Masa masa;
    private String data;
    private Double valoare;
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

    public Double getValoare() {
        return valoare;
    }

    public void setValoare(Double valoare) {
        this.valoare = valoare;
    }

    @Override
    public int compareTo(Object o) {
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy hh:mm");
        Comanda comanda=(Comanda) o;
        try {
            return simpleDateFormat.parse(this.data).compareTo(simpleDateFormat.parse(comanda.getData()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}