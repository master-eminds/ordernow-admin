package com.hellokoding.auth.model;

import javax.persistence.*;

@Entity
@Table(name="itemi_comanda")
public class ItemComanda {

    private Long id;
    private Integer cantitate;
    private Produs produs;
    private Comanda comanda;
    private Double valoare;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantitate() {
        return cantitate;
    }

    public void setCantitate(Integer cantitate) {
        this.cantitate = cantitate;
    }


    @ManyToOne
    @JoinColumn(name="produs_id", nullable=false)
    public Produs getProdus() {
        return produs;
    }

    public void setProdus(Produs produs) {
        this.produs = produs;
    }
    @ManyToOne
    @JoinColumn(name="comanda_id", nullable=false)
    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public Double getValoare() {
        return valoare;
    }

    public void setValoare(Double valoare) {
        this.valoare = valoare;
    }
}
