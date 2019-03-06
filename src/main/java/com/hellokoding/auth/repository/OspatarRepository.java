package com.hellokoding.auth.repository;

import com.hellokoding.auth.model.Ospatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OspatarRepository extends JpaRepository<Ospatar, Long>{
    Ospatar findByEmail(String email);
}
