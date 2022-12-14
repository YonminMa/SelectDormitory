package com.pku.dormitory.service;

import com.pku.dormitory.domain.Building;

import java.util.List;

public interface BuildingService {

    List<Building> getAllBuildings();

    void saveBuilding(Building building);

    int getIdByNumber(int number);

    void deleteBuilding(int number);

    void updateRest(int number, int rest);

    int checkRest(int id);

    int checkRestByIdAndGender(int id, int gender);
}
