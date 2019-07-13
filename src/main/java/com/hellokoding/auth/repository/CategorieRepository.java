package com.hellokoding.auth.repository;

import com.hellokoding.auth.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CategorieRepository extends JpaRepository<Categorie, Long>{
    @Transactional
    @Modifying
    @Query(value = "UPDATE categorii  set sters=1 where id= ?1",
            nativeQuery = true)
    void deleteCategorie(  Long id);
}
