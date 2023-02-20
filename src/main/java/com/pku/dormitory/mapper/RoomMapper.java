package com.pku.dormitory.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Yonmin
 * @date 2022/12/2 11:14
 */
@Mapper
public interface RoomMapper {

    @Select("select id building_id, name building_name from building")
    ArrayList<HashMap<String, Object>> getBuildings();

    @Select("select b.name, b.description, b.image_url from building b where b.id = #{id}")
    HashMap<String, Object> getBuildingById(Integer id);

    @Select("select r.name, r.gender, r.building_id, r.rest from room r where r.id = #{id} and r.is_valid = 1")
    HashMap<String, Object> getRoomById(Integer id);

    @Select("select t.id as building_id, t.name as building_name, SUM(t.rest) as rest from " +
            "(select b.id, b.name, r.rest from building b left join room r on r.building_id = b.id where r.gender = #{id}) t " +
            "group by t.name, t.id")
    ArrayList<HashMap<String, Object>> getRestByGender(Integer gender);

    @Select("select ifnull(sum(rest), 0) as rest from room where building_id = #{buildingId} and gender = #{gender}")
    Integer getRestByBuildingAndGender(Integer buildingId, Integer gender);

    @Select("select id from room " +
            "where gender = #{gender} and building_id = #{buildingId} and rest >= #{rest} " +
            "order by rest limit 1")
    Integer getRoomIdByBuildingAndGenderAndRest(Integer buildingId, Integer gender, Integer rest);

    @Update("update room set rest = (rest - #{need}) where id = #{id}")
    void updateRestByIdAndNeed(Integer id, Integer need);
}
