package com.pku.dormitory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pku.dormitory.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author Yonmin
 * @date 2022/12/4 19:00
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    @Select("select o.id order_id, ifnull(t.name, '没有组队') as team_name, b.name building_name, o.submit_time, o.status from orders o " +
            "left join building b on b.id = o.building_id " +
            "left join team t on t.id = o.group_id")
    ArrayList<HashMap<String, Object>> getOrderList();
}
