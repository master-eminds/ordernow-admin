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
    public List<CountProdus> numarProduseComandate() {

        HashMap<Long, Integer> map=new HashMap<Long, Integer>();

        return em.createNativeQuery(
                "select p.id, denumire, count(i.produs_id) as numar_aparitii from itemi_comanda i, produse p where p.id=i.produs_id group by p.id order by numar_aparitii desc limit 6", CountProdus.class)
                .getResultList();


}

    @Override
    public Produs findById(Long id) {
        return produsRepository.findOne(id);
    }

}
