package com.pku.dormitory.service.impl;

import com.pku.dormitory.domain.Building;
import com.pku.dormitory.mapper.BuildingMapper;
import com.pku.dormitory.mapper.RoomMapper;
import com.pku.dormitory.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Yonmin
 * @date 2023/5/4 22:53
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    BuildingMapper buildingMapper;
    @Autowired
    RoomMapper roomMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void setWithExpire(String key, String value, long time, TimeUnit timeUnit) {
        BoundValueOperations<String, String> boundValueOperations = redisTemplate.boundValueOps(key);
        boundValueOperations.set(value);
        boundValueOperations.expire(time, timeUnit);
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean delete(String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    @Override
    public String getBuildingName(int buildingId) {
        String buildingName = this.get("building:name" + buildingId);
        if (buildingName == null) {
            Building building = buildingMapper.selectById(buildingId);
            if (building == null) {
                return null;
            }
            buildingName = building.getName();
            // 设置随机过期时间，防止缓存雪崩
            this.setWithExpire("building:name:" + buildingId, buildingName, (long) (Math.random() * 10 + 10), TimeUnit.HOURS);
        }
        return buildingName;
    }

    @Override
    public int getBuildingRestByGender(int buildingId, int gender) {
        String rest = this.get("building:" + gender + ":rest:" + buildingId);
        if (rest == null) {
            int res = roomMapper.getRestByBuildingAndGender(buildingId, gender);
            // 设置随机过期时间，防止缓存雪崩
            this.setWithExpire("building:" + gender + ":rest:" + buildingId, res + "", (long) (Math.random() * 10 + 10), TimeUnit.HOURS);
            return res;
        }
        return Integer.parseInt(rest);
    }

    // 使用延迟双删策略更改剩余床位数
    @Override
    public void updateRestByIdAndNeed(Integer roomId, Integer buildingId, Integer need, Integer gender) {
        // 删除缓存
        this.delete("building:" + gender + ":rest:" + buildingId);
        // 更改数据库
        roomMapper.updateRestByIdAndNeed(roomId, need);
        // 新建一个线程，异步等待1s后再次删除缓存
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 删除缓存
            this.delete("building:" + gender + ":rest:" + buildingId);
        }).start();
    }
}
