package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Sugestie;
import com.hellokoding.auth.repository.SugestieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class SugestieServiceImpl implements SugestieService {
    @Autowired
    SugestieRepository sugestieRepository;
    @PersistenceContext

    private EntityManager em;

    @Override
    public Sugestie findById(Long id) {
        return sugestieRepository.findOne(id) ;
    }

    @Override
    public List<Sugestie> findAll() {
        return sugestieRepository.findAll();
    }

    @Override
    public List<Sugestie> findAllByStare(String stare) {


        return em.createNativeQuery(
                "select * from sugestii where stare=:stare order by data desc", Sugestie.class)
                .setParameter("stare", stare)
                .getResultList();

    }
    @Override
    public List<Sugestie> findAllByCategorie(String categorie) {


        return em.createNativeQuery(
                "select * from sugestii where categorie=:categorie order by data desc", Sugestie.class)
                .setParameter("categorie", categorie)
                .getResultList();

    }
    @Override
    public int findCounterByStare(String stare) {
        String counter= em.createNativeQuery(
                "select count(id) from sugestii where stare=:stare")
                .setParameter("stare", stare)
                .getSingleResult().toString();
        return Integer.parseInt(counter);
    }

    @Override
    public void save(Sugestie mesaj) {
         sugestieRepository.save(mesaj);
    }

    @Override
    public List<Sugestie> findAllByStareAndCateg(String stare, String categorie) {

        return em.createNativeQuery(
                "select * from sugestii where categorie=:categorie  and stare=:stare order by data desc", Sugestie.class)
                .setParameter("categorie", categorie)
                .setParameter("stare",stare)
                .getResultList();
    }

    @Override
    public List<Sugestie> findFirstByStare(String stare, int limit) {


        return em.createNativeQuery(
                "select * from sugestii where stare=:stare limit "+limit, Sugestie.class)
                .setParameter("stare", stare)
                .getResultList();

    }




}
