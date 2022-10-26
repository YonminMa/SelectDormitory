package com.pku.dormitory.service.impl;

import com.pku.dormitory.dao.BuildingRepository;
import com.pku.dormitory.domain.Building;
import com.pku.dormitory.service.BuildingService;
import com.pku.dormitory.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    BuildingRepository buildingRepository;

    @Autowired
    RoomService roomService;

    @Override
    public List<Building> getAllBuildings() {
        return buildingRepository.findAll();
    }

    @Override
    public void saveBuilding(Building building) {
        buildingRepository.save(building);
    }

    @Override
    public int getIdByNumber(int number) {
            return buildingRepository.getIdByNumber(number);
    }

    @Override
    public void deleteBuilding(int number) {
        buildingRepository.deleteBuildingByNumber(number);
    }

    @Override
    public void updateRest(int id, int rest) {
        buildingRepository.updateRest(id, rest);
    }

    @Override
    public int checkRest(int id) {
        return buildingRepository.checkRest(id);
    }

    @Override
    public int checkRestByIdAndGender(int id, int gender) {
        return roomService.checkRestByIdAndGender(id, gender);
    }
}
