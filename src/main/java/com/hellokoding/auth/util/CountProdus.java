package com.hellokoding.auth.util;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "count_produs")
public class CountProdus {

    private Long id;
    private String denumire;
    private int cantitate_totala;

    public CountProdus() {
    }

    public CountProdus(Long id, String denumire, int cantitate_totala) {
        this.id = id;
        this.denumire=denumire;
        this.cantitate_totala = cantitate_totala;
    }
@Id
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

    public int getCantitate_totala() {
        return cantitate_totala;
    }

    public void setCantitate_totala(int cantitate_totala) {
        this.cantitate_totala = cantitate_totala;
    }


}
