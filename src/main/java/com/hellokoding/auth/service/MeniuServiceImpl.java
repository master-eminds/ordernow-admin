package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Meniu;
import com.hellokoding.auth.repository.MeniuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class MeniuServiceImpl implements MeniuService {

    @Autowired
    MeniuRepository meniuRepository;
    @PersistenceContext
    private EntityManager em;

    @Override
    public Meniu save(Meniu meniu) {
        return meniuRepository.saveAndFlush(meniu);
    }

    @Override
    public Meniu findById(Long id) {
        return meniuRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        meniuRepository.delete(id);
    }

    @Override
    public List<Meniu> findTot() {
        return meniuRepository.findAll();
    }
    @Override
    public List<Meniu> findAll() {
        List meniuri= em.createNativeQuery(
                "select * from meniuri where sters=0", Meniu.class)
                .getResultList();
        return meniuri;
    }
    @Override
    public List<Meniu> findAllByStare(String stare) {
        List meniuri = em.createNativeQuery(
                "select * from meniuri where stare=:stare and sters=0", Meniu.class)
                .setParameter("stare", stare)
                .getResultList();
        return meniuri;    }
}
