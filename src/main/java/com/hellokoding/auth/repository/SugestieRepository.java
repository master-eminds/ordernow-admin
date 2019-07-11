package com.hellokoding.auth.repository;

import com.hellokoding.auth.model.Sugestie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SugestieRepository extends JpaRepository<Sugestie, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE sugestii  set stare =?1 where id =?2",
            nativeQuery = true)
    void updateStareSugestii(String stare, Long idSelectat );
}
