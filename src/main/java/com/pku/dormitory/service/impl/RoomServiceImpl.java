package com.pku.dormitory.service.impl;

import com.pku.dormitory.mapper.RoomMapper;
import com.pku.dormitory.mapper.UserMapper;
import com.pku.dormitory.service.RedisService;
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

    @Autowired
    RedisService redisService;

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

    // 使用 Redis 缓存查询剩余床位
    @Override
    public Result getRestByGender(String accessToken) {
        String username = JwtUtils.getClaimsByToken(accessToken).getSubject();
        Integer id = userMapper.getIdByUsername(username);
        Integer gender = userMapper.getGenderById(id);
        ArrayList<HashMap<String, Object>> buildingList = roomMapper.getBuildings();
        ArrayList<HashMap<String, Object>> result = new ArrayList<>();
        for (HashMap<String, Object> stringObjectHashMap : buildingList) {
            int buildingId = (int) stringObjectHashMap.get("building_id");
            String buildingName = (String) stringObjectHashMap.get("building_name");
            int rest = redisService.getBuildingRestByGender(buildingId, gender);
            result.add(new HashMap<String, Object>() {{
                put("building_id", buildingId);
                put("building_name", buildingName);
                put("rest", rest);
            }});
        }
        return Result.ok().data("rows", result);
    }
}
