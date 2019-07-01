package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Review;
import com.hellokoding.auth.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Review> findByIdProdus(Long idProdus) {

        List reviews = em.createNativeQuery(
                "select * from review where id_produs=:idProdus", Review.class)
                .setParameter("idProdus", idProdus)
                .getResultList();
        return reviews;
    }

    @Override
    public List<Review> findByIdOspatar(Long idOspatar) {
        List reviews = em.createNativeQuery(
                "select * from review where id_ospatar=:idOspatar", Review.class)
                .setParameter("idOspatar", idOspatar)
                .getResultList();
        return reviews;    }

}
