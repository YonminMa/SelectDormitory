package com.pku.dormitory.dao;

import com.pku.dormitory.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    @Query("select g.count from Group g where g.id = ?1")
    int getCountById(int id);
}
