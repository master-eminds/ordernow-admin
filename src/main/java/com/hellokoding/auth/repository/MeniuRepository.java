package com.hellokoding.auth.repository;

import com.hellokoding.auth.model.Meniu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MeniuRepository extends JpaRepository<Meniu, Long>{

    @Transactional
    @Modifying
    @Query(value = "UPDATE meniuri  set sters=1 where id= ?1",
            nativeQuery = true)
    void deleteMeniu( Long id);
}
