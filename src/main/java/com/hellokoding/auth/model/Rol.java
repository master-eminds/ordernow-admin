package com.hellokoding.auth.model;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "roluri")
public class Rol {
    private Long id;
    private String nume;
    private Set<Admin> admins;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String name) {
        this.nume = name;
    }

    @OneToMany(cascade=ALL, mappedBy="rol")
    public Set<Admin> getAdmins() {
        return admins;
    }
    public void setAdmins(Set<Admin> admins) {
        this.admins = admins;
    }
}
