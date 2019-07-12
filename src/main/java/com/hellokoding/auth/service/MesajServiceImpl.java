package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Mesaj;
import com.hellokoding.auth.repository.MesajRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class MesajServiceImpl implements MesajService {
    @Autowired
    MesajRepository mesajRepository;
    @PersistenceContext

    private EntityManager em;

    @Override
    public Mesaj findById(Long id) {
        return mesajRepository.findOne(id) ;
    }

    @Override
    public List<Mesaj> findAll() {
        return mesajRepository.findAll();
    }

    @Override
    public List<Mesaj> findAllByStare(String stare) {


        return em.createNativeQuery(
                "select * from mesaje where stare=:stare order by data desc", Mesaj.class)
                .setParameter("stare", stare)
                .getResultList();

    }
    @Override
    public int findCounterByStare(String stare) {

        String counter= em.createNativeQuery(
                "select count(id) from mesaje where stare=:stare")
                .setParameter("stare", stare)
                .getSingleResult().toString();
        return Integer.parseInt(counter);

    }
    @Override
    public List<Mesaj> findFirstByStare(String stare, int limit) {


        return em.createNativeQuery(
                "select * from mesaje where stare=:stare order by id desc limit "+limit, Mesaj.class)
                .setParameter("stare", stare)
                .getResultList();

    }
    @Override
    public void save(Mesaj mesaj) {
        mesajRepository.save(mesaj);
    }

    @Override
    public void update(Long idMesaj, String continutRaspuns) {
         em.createNativeQuery(
                "UPDATE mesaje m set continut_raspuns=?1 where m.id= ?2")
                .setParameter(1, continutRaspuns)
                .setParameter(2,idMesaj)
                .executeUpdate();
    }

    @Override
    public List<Mesaj> findAllByLimit(int start) {

       return em.createNativeQuery( "select * from mesaje order by id desc limit :start, 10", Mesaj.class)
                .setParameter("start", start)
                .getResultList();
    }


}
