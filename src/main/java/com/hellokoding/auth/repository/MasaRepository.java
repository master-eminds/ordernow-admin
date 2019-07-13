package com.hellokoding.auth.repository;

import com.hellokoding.auth.model.Masa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MasaRepository extends JpaRepository<Masa, Long>{
    @Transactional
    @Modifying
    @Query(value = "UPDATE mese  set sters=1 where id= ?1",
            nativeQuery = true)
    void deleteMasa( Long id);
}
