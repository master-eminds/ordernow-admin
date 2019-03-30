package com.hellokoding.auth.model;

public class ListaMese {

    private Long idMasa;
    private Integer numarComenzi;

    public ListaMese() {}

    public ListaMese(Long idMasa, Integer numarComenzi) {
        this.idMasa = idMasa;
        this.numarComenzi = numarComenzi;
    }

    public Long getIdMasa() {
        return idMasa;
    }

    public void setIdMasa(Long idMasa) {
        this.idMasa = idMasa;
    }

    public Integer getNumarComenzi() {
        return numarComenzi;
    }

    public void setNumarComenzi(Integer numarComenzi) {
        this.numarComenzi = numarComenzi;
    }
}
