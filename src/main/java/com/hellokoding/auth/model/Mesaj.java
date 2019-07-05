package com.hellokoding.auth.model;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Entity
@Table(name="mesaje")
public class Mesaj implements Comparable {


    private Long id;
    private String expeditor;
    private Long idExpeditor;

    private String continut;
    private String subiect;
    private Long idRaspuns;
    private String continutRaspuns;
    private String stare;
    private String data;

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
    public String getStare() {
        return stare;
    }

    public void setStare(String stare) {
        this.stare = stare;
    }

    public Long getIdExpeditor() {
        return idExpeditor;
    }

    public void setIdExpeditor(Long idExpeditor) {
        this.idExpeditor = idExpeditor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public int compareTo(Object o) {
        Mesaj m=(Mesaj)o;
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy hh:mm");
        int rez=0;
        try {
             rez=dateFormat.parse(this.getData()).compareTo(dateFormat.parse(m.getData()));
        } catch (ParseException e) {
            e.printStackTrace();
        } if(rez!=0)
            return -rez;
        return 0;
    }
}
