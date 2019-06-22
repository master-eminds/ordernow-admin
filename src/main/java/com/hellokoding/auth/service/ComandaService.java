package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Categorie;
import com.hellokoding.auth.model.Comanda;

import java.util.List;

public interface ComandaService {
    List<Comanda> findAll();
    Comanda findById(Long id);

}
