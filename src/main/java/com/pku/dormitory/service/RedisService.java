package com.pku.dormitory.service;

import java.util.concurrent.TimeUnit;

/**
 * RedisService
 *
 * @author Yonmin
 * @date 2023/5/4 22:52
 */
public interface RedisService {
    /**
     * 向Redis中存储键值对
     *
     * @param key   KEY
     * @param value VALUE
     */
    void set(String key, String value);

    /**
     * 向Redis中存储键值对，并设置过期时间
     *
     * @param key      KEY
     * @param value    VALUE
     * @param time     过期时间
     * @param timeUnit 时间单位
     */
    void setWithExpire(String key, String value, long time, TimeUnit timeUnit);

    String get(String key);

    /**
     * 删除Redis中的某个KEY
     *
     * @param key KEY
     * @return boolean
     */
    boolean delete(String key);

    String getBuildingName(int buildingId);

    int getBuildingRestByGender(int buildingId, int gender);

    void updateRestByIdAndNeed(Integer roomId, Integer buildingId, Integer need, Integer gender);
}