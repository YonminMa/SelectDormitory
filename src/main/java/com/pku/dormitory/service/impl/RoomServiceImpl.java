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
    public void saveRoom(Room room) {
        roomRepository.save(room);
        // 保存完房间后要更新building的剩余床位数量
        int building_rest = buildingService.checkRest(room.getBuilding_id()) + room.getRest();
        buildingService.updateRest(room.getBuilding_id(), building_rest);
    }

    @Override
    public void updateRoom(int id, int decline) {
        roomRepository.updateRoom(id, decline);
    }

}
