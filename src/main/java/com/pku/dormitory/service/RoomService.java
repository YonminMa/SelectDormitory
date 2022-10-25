package com.pku.dormitory.service;

import com.pku.dormitory.domain.Room;

public interface RoomService {

    Room findRoomById(int id);

    void saveRoom(Room room);

    boolean updateRoom(int id, int decline);

}
