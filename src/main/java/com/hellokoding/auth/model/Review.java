package com.hellokoding.auth.model;

import javax.persistence.*;

@Entity
@Table(name="reviewuri")
public class Review {

    private Long id;
    private boolean like ;
    private Produs produs;
    private Ospatar ospatar;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
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
    @JoinColumn(name="ospatar_id", nullable=false)
    public Ospatar getOspatar() {
        return ospatar;
    }

    public void setOspatar(Ospatar ospatar) {
        this.ospatar = ospatar;
    }
}
