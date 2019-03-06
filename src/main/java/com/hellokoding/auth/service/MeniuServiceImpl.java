package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Meniu;
import com.hellokoding.auth.repository.MeniuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeniuServiceImpl implements MeniuService {

    @Autowired
    MeniuRepository meniuRepository;

    @Override
    public void save(Meniu meniu) {
        meniuRepository.save(meniu);
    }

    @Override
    public Meniu findById(Long id) {
        return meniuRepository.findOne(id);
    }

    @Override
    public List<Meniu> findAll() {
        return meniuRepository.findAll();
    }
}
