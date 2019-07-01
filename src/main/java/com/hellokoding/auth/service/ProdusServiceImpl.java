package com.hellokoding.auth.service;

import com.hellokoding.auth.util.CountProdus;
import com.hellokoding.auth.model.Produs;
import com.hellokoding.auth.repository.ProdusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProdusServiceImpl implements ProdusService {

    @Autowired
    ProdusRepository produsRepository;
    @PersistenceContext
    private EntityManager em;
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
    public Map<Long, Integer> numarProduseComandate() {

        HashMap<Long, Integer> map=new HashMap<Long, Integer>();



        List produse = em.createNativeQuery(
                "select produs_id as id, count(produs_id) as numar_aparitii from itemi_comanda group by produs_id order by numar_aparitii desc limit 10", CountProdus.class)
                .getResultList();

        for (Object result : produse) {
            CountProdus produs= (CountProdus)result;
            map.put(produs.getId(), produs.getNumarAparitii());
        }

        return map;


}

    @Override
    public Produs findById(Long id) {
        return produsRepository.findOne(id);
    }

}
