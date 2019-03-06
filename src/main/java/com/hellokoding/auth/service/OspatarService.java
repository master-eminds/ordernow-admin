package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Ospatar;

import java.util.List;

public interface OspatarService {
    void save(Ospatar ospatar);

    Ospatar findById(Long id);

    Ospatar findByEmail(String email);

    List<Ospatar> findAll();
}
