package com.hellokoding.auth.util;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "count_produs")
public class CountProdus {

    private Long id;
    private int numarAparitii;

    public CountProdus() {
    }

    public CountProdus(Long id, int numarAparitii) {
        this.id = id;
        this.numarAparitii = numarAparitii;
    }
@Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumarAparitii() {
        return numarAparitii;
    }

    public void setNumarAparitii(int numarAparitii) {
        this.numarAparitii = numarAparitii;
    }


}
