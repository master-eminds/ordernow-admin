package com.hellokoding.auth.model;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Entity
@Table(name="sugestii")
public class Sugestie implements Comparable{
    private Long id;
    private String continut;
    private String categorie;
    private String data;
    private String stare;
    public Sugestie(){}
    public Sugestie( String continut, String categorie, String data) {

        this.continut=continut;
        this.categorie= categorie;
        this.data=data;
    }



    public Sugestie(long id, String continut, String categorie, String data) {
        this.id=id;
        this.continut=continut;
        this.categorie= categorie;
        this.data=data;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContinut() {
        return continut;
    }

    public void setContinut(String continut) {
        this.continut = continut;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStare() {
        return stare;
    }

    public void setStare(String stare) {
        this.stare = stare;
    }

    @Override
    public int compareTo(Object o) {
        Sugestie m=(Sugestie) o;
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
