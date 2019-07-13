package com.hellokoding.auth.repository;

import com.hellokoding.auth.model.Ospatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OspatarRepository extends JpaRepository<Ospatar, Long>{
    Ospatar findByEmail(String email);
    @Transactional
    @Modifying
    @Query(value = "UPDATE ospatari  set sters=1 where id= ?1",
            nativeQuery = true)
    void deleteOspatar(Long id);
}
