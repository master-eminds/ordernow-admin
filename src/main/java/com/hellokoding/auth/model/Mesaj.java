package com.hellokoding.auth.model;

import javax.persistence.*;

@Entity
@Table(name="mesaje")
public class Mesaj {


    private Long id;
    private String expeditor;
    private String continut;
    private String subiect;
    private Long idRaspuns;
    private String continutRaspuns;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpeditor() {
        return expeditor;
    }

    public void setExpeditor(String expeditor) {
        this.expeditor = expeditor;
    }

    public String getContinut() {
        return continut;
    }

    public void setContinut(String continut) {
        this.continut = continut;
    }

    public String getSubiect() {
        return subiect;
    }

    public void setSubiect(String subiect) {
        this.subiect = subiect;
    }

    public Long getIdRaspuns() {
        return idRaspuns;
    }

    public void setIdRaspuns(Long idRaspuns) {
        this.idRaspuns = idRaspuns;
    }

    public String getContinutRaspuns() {
        return continutRaspuns;
    }

    public void setContinutRaspuns(String continutRaspuns) {
        this.continutRaspuns = continutRaspuns;
    }
}
