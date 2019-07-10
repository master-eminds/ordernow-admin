package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Ospatar;
import com.hellokoding.auth.repository.OspatarRepository;
import com.hellokoding.auth.util.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class OspatarServiceImpl implements OspatarService {

    @Autowired
    OspatarRepository ospatarRepository;
    @PersistenceContext
    EntityManager em;

    @Override
    public Ospatar save(Ospatar ospatar) {

        try {
            ospatar.setParola(Global.criptare(ospatar.getParola(),Global.cheieCriptare));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ospatar.setStatus("offline");
        return ospatarRepository.saveAndFlush(ospatar);
    }


    @Override
    public Ospatar findById(Long id) {

        return ospatarRepository.findOne(id);
    }

    @Override
    public Ospatar findByEmail(String email) {
        return ospatarRepository.findByEmail(email);
    }

    @Override
    public List<Ospatar> findAll() {
        return ospatarRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        em.createNativeQuery(
                "UPDATE ospatari  set sters=?1 where id= ?2")
                .setParameter(1, 1)
                .setParameter(2,id)
                .executeUpdate();


    }
    @Override
    public List<Ospatar> findAllNestersi() {

        List ospatari= em.createNativeQuery(
                "select * from ospatari where sters=?1",Ospatar.class)
                .setParameter(1,0)
                .getResultList();
        return ospatari;
    }


}
