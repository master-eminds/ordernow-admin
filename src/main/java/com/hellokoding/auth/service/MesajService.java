package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Mesaj;

import java.util.List;

public interface MesajService {
    Mesaj findById(Long id);
    List<Mesaj> findAll();
    List<Mesaj> findAllByStare(String stare);
    List<Mesaj> findFirstByStare(String stare, int limit);

    int findCounterByStare(String stare);

    void save(Mesaj mesaj);
    void update(Long idMesaj, String continutRaspuns);


    List<Mesaj> findAllByLimit(int start);
}
