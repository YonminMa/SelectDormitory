package com.pku.dormitory.dao;

import com.pku.dormitory.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    Room findRoomById(int id);

    @Query("select r.rest from Room r where r.id = ?1")
    int getRest(int id);

    @Transactional
    @Modifying
    @Query("update Room r set r.rest = (r.rest - ?2) where r.id = ?1")
    void updateRoom(int id, int decline);
}
