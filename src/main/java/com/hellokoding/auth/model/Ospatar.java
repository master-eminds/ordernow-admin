package com.hellokoding.auth.model;


import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "ospatari")
public class Ospatar implements Comparable{

    private Long id;
    private String nume;
    private String email;
    private String parola;
    private String passwordConfirm;
    private String status;
    private Set<Review> reviews;
    private Set<Comanda> listaComenzi;
    private int sters;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }
    @OneToMany(cascade=ALL, mappedBy="ospatar")
    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }
    @OneToMany(cascade=ALL, mappedBy="ospatar")
    public Set<Comanda> getListaComenzi() {
        return listaComenzi;
    }

    public void setListaComenzi(Set<Comanda> listaComenzi) {
        this.listaComenzi = listaComenzi;
    }

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int compareTo(Object o) {
        Ospatar ospatar=(Ospatar) o;
        return this.getNume().compareTo(ospatar.getNume());
    }

    public int getSters() {
        return sters;
    }

    public void setSters(int sters) {
        this.sters = sters;
    }
}
