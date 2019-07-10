package com.hellokoding.auth.repository;

import com.hellokoding.auth.model.Produs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProdusRepository extends JpaRepository<Produs, Long>{
    @Transactional
    @Modifying
    @Query(value = "UPDATE produse  set sters=?1 where id= ?2",
            nativeQuery = true)
    void deleteProdus( int sters, Long id);

}
