package com.pku.dormitory.service;

import com.pku.dormitory.domain.Room;

public interface RoomService {

    Room findRoomById(int id);

    Room findRoomByRestAndGender(int rest, int gender);

    void saveRoom(Room room);

    boolean updateRoom(int id, int decline);

    int checkRestByIdAndGender(int bid, int gender);
}
