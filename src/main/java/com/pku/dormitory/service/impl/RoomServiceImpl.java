package com.pku.dormitory.service.impl;

import com.pku.dormitory.dao.RoomRepository;
import com.pku.dormitory.domain.Room;
import com.pku.dormitory.service.BuildingService;
import com.pku.dormitory.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    BuildingService buildingService;

    @Override
    public Room findRoomById(int id) {
        return roomRepository.findRoomById(id);
    }

    public Room findRoomByRestAndGender(int rest, int gender){
        System.out.println(roomRepository.findRoomByRestAndGender(rest, gender));
        return roomRepository.findRoomByRestAndGender(rest, gender);
    }

    @Override
    public void saveRoom(Room room) {
        roomRepository.save(room);
        // 保存完房间后要更新building的剩余床位数量
        int building_rest = buildingService.checkRest(room.getBuilding_id()) + room.getRest();
        buildingService.updateRest(room.getBuilding_id(), building_rest);
    }

    @Override
    public boolean updateRoom(int id, int decline) {
        Room room = findRoomById(id);
        if (room == null) {
            System.out.println("无此房间，提交失败");
            return false;
        } else if (room.getRest() < decline) {
            System.out.println("房间床位不足，提交失败");
            return false;
        } else {
            int building_id = roomRepository.findRoomById(id).getBuilding_id();
            roomRepository.updateRoom(id, decline);
            int building_rest = buildingService.checkRest(building_id) - decline;
            buildingService.updateRest(building_id, building_rest);
            System.out.println("房间"+id+"的床位减少了"+decline);
            return true;
        }
    }

    @Override
    public int checkRestByIdAndGender(int bid, int gender) {
        return roomRepository.checkRestByIdAndGender(bid, gender);
    }
}
