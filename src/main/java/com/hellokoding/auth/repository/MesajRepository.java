package com.hellokoding.auth.repository;

import com.hellokoding.auth.model.Mesaj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MesajRepository extends JpaRepository<Mesaj, Long> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE mesaje m set continut_raspuns =?1 where m.id = ?2",
            nativeQuery = true)
    void updateMesaj( String continutRaspuns, Long idMesaj);

    @Transactional
    @Modifying
    @Query(value = "UPDATE mesaje m set stare =?1 where m.id = ?2",
            nativeQuery = true)
    void updateStareMesaj( String stare, Long idMesaj);
}
