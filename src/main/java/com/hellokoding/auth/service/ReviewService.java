package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findByIdProdus(Long id);
    List<Review> findByIdOspatar(Long id);

}
