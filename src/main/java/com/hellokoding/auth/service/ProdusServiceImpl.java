package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Produs;
import com.hellokoding.auth.repository.ProdusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdusServiceImpl implements ProdusService {

    @Autowired
    ProdusRepository produsRepository;

    @Override
    public void save(Produs produs) {
        produsRepository.save(produs);
    }

    @Override
    public void saveOrUpdate(Produs produs) {
        if(produs.getId()!=null){
            produsRepository.delete(produs.getId());
            produsRepository.save(produs);
        }
        else {
            produsRepository.save(produs);
        }
    }

    @Override
    public void delete(Long id) {
        produsRepository.delete(id);
    }

    @Override
    public List<Produs> findAll() {
        return produsRepository.findAll();
    }

    @Override
    public Produs findById(Long id) {
        return produsRepository.findOne(id);
    }

}
