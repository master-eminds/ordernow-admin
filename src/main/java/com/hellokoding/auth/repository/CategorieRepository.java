package com.hellokoding.auth.repository;

import com.hellokoding.auth.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorieRepository extends JpaRepository<Categorie, Long>{
   // List<Categorie>findAllByIdMeniu(Long id);
}
