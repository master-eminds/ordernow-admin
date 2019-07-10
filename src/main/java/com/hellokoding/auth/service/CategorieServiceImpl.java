package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Categorie;
import com.hellokoding.auth.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Service
public class CategorieServiceImpl implements CategorieService {

    @Autowired
    CategorieRepository categorieRepository;
    @PersistenceContext
    private EntityManager em;

    @Override
    public Categorie save(Categorie categorie) {
        return categorieRepository.saveAndFlush(categorie);

    }

    @Override
    public void delete(Long id) {
        categorieRepository.delete(id);
    }

    @Override
    public void stergeCategorie(Long id) {
            em.createNativeQuery(
                    "UPDATE categorii m set sters=?1 where m.id= ?2")
                    .setParameter(1, "true")
                    .setParameter(2,id)
                    .executeUpdate();

    }

    @Override
    public Categorie findById(Long id) {
        return categorieRepository.findOne(id);
    }



    @Override
    public List<Categorie> findAll() {
        List categorii= em.createNativeQuery(
                "select * from categorii where sters=0", Categorie.class)
                .getResultList();
        return categorii;
    }

    @Override
    public List<Categorie> findAllByMeniuId(Long meniu_id) {
        List categorie = em.createNativeQuery(
                "select * from categorii where meniu_id=:meniuId and sters =0", Categorie.class)
                .setParameter("meniuId", meniu_id)
                .getResultList();
        return categorie;   }

    @Override
    public List<Categorie> findAllByVizibilitate(Long meniuId,String vizibilitate) {
        List categorie = em.createNativeQuery(
                "select * from categorii where meniu_id=:meniuId and vizibilitate =:vizibilitate and sters=0", Categorie.class)
                .setParameter("meniuId", meniuId)
                .setParameter("vizibilitate", vizibilitate)
                .getResultList();
        return categorie;    }
}
