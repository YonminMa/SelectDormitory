package com.pku.dormitory.dao;

import com.pku.dormitory.domain.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BuildingRepository extends JpaRepository<Building, Integer> {

    void deleteBuildingByNumber(int number);

    @Transactional
    @Modifying
    @Query("update Building b set b.rest = ?2 where b.id = ?1")
    void updateRest(int id, int rest);

    @Query("select b.rest from Building b where b.id = ?1")
    int checkRest(int id);
}
