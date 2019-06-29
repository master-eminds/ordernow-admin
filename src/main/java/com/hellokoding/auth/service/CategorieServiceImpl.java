package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Categorie;
import com.hellokoding.auth.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategorieServiceImpl implements CategorieService {

    @Autowired
    CategorieRepository categorieRepository;

    @Override
    public void save(Categorie categorie) {
        categorieRepository.save(categorie);
    }

    @Override
    public void delete(Long id) {
        categorieRepository.delete(id);
    }

    @Override
    public Categorie findById(Long id) {
        return categorieRepository.findOne(id);
    }



    @Override
    public List<Categorie> findAll() {
        return categorieRepository.findAll();
    }
}
