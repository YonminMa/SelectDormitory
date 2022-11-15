package com.pku.dormitory.dao;

import com.pku.dormitory.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Record, Integer> {

}
