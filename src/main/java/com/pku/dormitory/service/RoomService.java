package com.pku.dormitory.service;

import com.pku.dormitory.domain.Room;

public interface RoomService {

    void saveRoom(Room room);

    void updateRoom(int id, int decline);

}
