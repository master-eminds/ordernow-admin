package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Produs;
import com.hellokoding.auth.repository.ProdusRepository;
import com.hellokoding.auth.util.CountProdus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ProdusServiceImpl implements ProdusService {

    @Autowired
    ProdusRepository produsRepository;
    @PersistenceContext
    private EntityManager em;
    @Override
    public Produs save(Produs produs) {
        return produsRepository.saveAndFlush(produs);
    }

    @Override
    public void saveOrUpdate(Produs produs) {
        if(produs.getId()!=null){
            produsRepository.delete(produs.getId());
        }
            produsRepository.save(produs);

    }

    @Override
    public void delete(Long id) {
        produsRepository.delete(id);
    }

    @Override
    public List<Produs> findAll() {
        List produse= em.createNativeQuery(
                "select * from produse where sters=0", Produs.class)
                .getResultList();
        return produse;
    }

    @Override
    public List<CountProdus> numarProduseComandate() {


        return em.createNativeQuery(
                "select p.id, denumire, sum(cantitate) as cantitate_totala" +
                        " from itemi_comanda i, produse p where p.id=i.produs_id" +
                        " group by p.id order by cantitate_totala desc limit 6;", CountProdus.class)
                .getResultList();
    }

    @Override
    public Produs findById(Long id) {
        return produsRepository.findOne(id);
    }

    @Override
    public List<Produs> findAllByVizibilitate(Long categorieId, String vizibilitate) {
        List produse = em.createNativeQuery(
                "select * from produse where categorie_id=:categorieId and vizibilitate =:vizibilitate and sters=0", Produs.class)
                .setParameter("categorieId", categorieId)
                .setParameter("vizibilitate", vizibilitate)
                .getResultList();
        return produse;    }

    @Override
    public List<Produs> findAllByCategorie(Long categorieId) {
        List produse = em.createNativeQuery(
                "select * from produse where categorie_id=:categorieId and sters=0", Produs.class)
                .setParameter("categorieId", categorieId)
                .getResultList();
        return produse;
    }

}
