package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Produs;

import java.util.Set;

public interface ProdusService {

    void save(Produs produs);

    Produs findById(Long id);

}
