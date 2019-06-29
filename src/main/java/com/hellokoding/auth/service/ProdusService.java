package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Produs;

public interface ProdusService {

    void save(Produs produs);
    void saveOrUpdate(Produs produs);

    Produs findById(Long id);

}
