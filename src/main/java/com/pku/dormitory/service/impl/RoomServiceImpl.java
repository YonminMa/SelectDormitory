package com.pku.dormitory.service.impl;

import com.pku.dormitory.mapper.RoomMapper;
import com.pku.dormitory.mapper.UserMapper;
import com.pku.dormitory.service.RoomService;
import com.pku.dormitory.utils.JwtUtils;
import com.pku.dormitory.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Yonmin
 * @date 2022/12/2 11:13
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomMapper roomMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public Result getBuildings() {
        ArrayList<HashMap<String, Object>> buildingList = roomMapper.getBuildings();
        return Result.ok().data("rows", buildingList);
    }

    @Override
    public Result getBuilding(Integer id) {
        HashMap<String, Object> map = roomMapper.getBuildingById(id);
        return Result.ok().data(map);
    }

    @Override
    public Result getRoomById(Integer id) {
        HashMap<String, Object> map = roomMapper.getRoomById(id);
        return Result.ok().data(map);
    }

    @Override
    public Result getRestByGender(String accessToken) {
        String username = JwtUtils.getClaimsByToken(accessToken).getSubject();
        Integer id = userMapper.getIdByUsername(username);
        Integer gender = userMapper.getGenderById(id);
        ArrayList<HashMap<String, Object>> buildingList = roomMapper.getRestByGender(gender);
        return Result.ok().data("rows", buildingList);
    }
}
