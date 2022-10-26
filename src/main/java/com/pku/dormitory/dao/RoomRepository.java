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

    @Query("select count(r.rest) from Room r where r.building_id = ?1 and r.gender = ?2")
    int checkRestByIdAndGender(int bid, int gender);

    @Query(value = "select * from tb_room r where r.rest >= ?1 and r.gender = ?2 and r.available = 0 order by r.rest desc limit 1", nativeQuery = true)
    Room findRoomByRestAndGender(int rest, int gender);
}
