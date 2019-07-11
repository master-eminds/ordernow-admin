package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Sugestie;

import java.util.List;

public interface SugestieService {
    Sugestie findById(Long id);
    List<Sugestie> findAll();
    List<Sugestie> findAllByStare(String stare);
    List<Sugestie> findAllByCategorie(String categorie);

    List<Sugestie> findFirstByStare(String stare, int limit);
    int findCounterByStare(String stare);

    void save(Sugestie mesaj);


    List<Sugestie> findAllByStareAndCateg(String necitit, String categorie);
}
