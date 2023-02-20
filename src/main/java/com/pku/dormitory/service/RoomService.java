package com.pku.dormitory.service;

import com.pku.dormitory.utils.Result;

/**
 * @author Yonmin
 * @date 2022/12/2 11:13
 */
public interface RoomService {

    Result getBuildings();

    Result getBuilding(Integer id);

    Result getRoomById(Integer id);

    Result getRestByGender(String accessToken);
}
