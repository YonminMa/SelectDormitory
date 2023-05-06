package com.pku.dormitory.service.impl;

import com.pku.dormitory.domain.Order;
import com.pku.dormitory.domain.UserTeam;
import com.pku.dormitory.mapper.*;
import com.pku.dormitory.mq.MQSender;
import com.pku.dormitory.service.OrderService;
import com.pku.dormitory.utils.JwtUtils;
import com.pku.dormitory.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Yonmin
 * @date 2022/12/4 12:10
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    InfoMapper infoMapper;

    @Autowired
    RoomMapper roomMapper;

    @Autowired
    UserRoomMapper userRoomMapper;

    @Autowired
    BuildingMapper buildingMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    MQSender mqSender;

    @Autowired
    UserTeamMapper userTeamMapper;

    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 创建订单
     *
     * @param accessToken 创建订单的学生
     * @param params      paras.get("building_id") 抢宿舍的楼
     * @return 成功 | 没有此宿舍楼 | 没有空余床位 | 已分配宿舍
     */
    @Override
    public Result createOrder(String accessToken, HashMap<String, String> params) {

        Order order = new Order();
        order.setSubmitTime(new Timestamp(System.currentTimeMillis()));

        String username = JwtUtils.getClaimsByToken(accessToken).getSubject();
        Integer userId = userMapper.getIdByUsername(username);
        order.setUid(userId);

        if ("".equals(params.get("building_id"))) {
            order.setBuildingId(-1);
            order.setStatus(2);
            order.setFinishTime(new Timestamp(System.currentTimeMillis()));
            orderMapper.insert(order);
            return Result.error("请输入宿舍楼id").data("order_id", order.getId());
        }

        Integer buildingId = Integer.valueOf(params.get("building_id"));
        order.setBuildingId(buildingId);

        List<UserTeam> userTeams = userTeamMapper.getUserTeamsByUserId(userId);
        if (userTeams.size() != 0) {
            Integer teamId = userTeams.get(0).getTeamId();
            order.setGroupId(teamId);
        }

        if (buildingMapper.selectById(buildingId) == null) {
            order.setStatus(2);
            order.setFinishTime(new Timestamp(System.currentTimeMillis()));
            orderMapper.insert(order);
            return Result.error("没有此宿舍楼").data("order_id", order.getId());
        }

        Integer gender = infoMapper.getGenderById(userId);
        int need = order.getGroupId() == 0 ? 1 : userTeamMapper.getUserIdsByTeamId(order.getGroupId()).size();
        if (roomMapper.getRestByBuildingAndGender(buildingId, gender) < need) {
            order.setStatus(2);
            order.setFinishTime(new Timestamp(System.currentTimeMillis()));
            orderMapper.insert(order);
            return Result.error("空余床位不足").data("order_id", order.getId());
        }

        if (userRoomMapper.existsUserId(userId)) {
            order.setStatus(2);
            order.setFinishTime(new Timestamp(System.currentTimeMillis()));
            orderMapper.insert(order);
            return Result.error("已分配宿舍").data("order_id", order.getId());
        }


        if (seckillWithLua(buildingId, gender, need + "")) {
            // 生成订单
            orderMapper.insert(order);
            // 发送订单消息
            mqSender.sendOrder(order);
        }
        return Result.ok().data("order_id", order.getId());
    }

    @Override
    public Result getOrderList(String accessToken) {
        ArrayList<HashMap<String, Object>> orderList = orderMapper.getOrderList();
        return Result.ok().data("order_list", orderList);
    }

    @Override
    public Result getOrderInfo(String accessToken, HashMap<String, String> params) {
        Integer orderId = Integer.valueOf(params.get("order_id"));
        Order order = orderMapper.selectById(orderId);
        if (order == null) {
            return Result.error("没有此订单");
        } else {
            return Result.ok()
                    .data("status", order.getStatus())
                    .data("room_id", order.getRoomId());
        }
    }

    // 使用lua脚本实现秒杀
    private boolean seckillWithLua(int buildingId, int gender, String need) {
        boolean result = false;
        try {
            String luaScript = "if tonumber(redis.call('get', KEYS[1])) >= tonumber(ARGV[1]) then " +
                    "redis.call('decrby', KEYS[1], ARGV[1]) " +
                    "return 1 " +
                    "else " +
                    "return 0 " +
                    "end";

            DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
            redisScript.setScriptText(luaScript);
            redisScript.setResultType(Long.class);

            List<String> keyList = new ArrayList<>();
            // KEY[1]
            keyList.add("building:" + gender + ":rest:" + buildingId);
            result = (redisTemplate.execute(redisScript, keyList, need) == 1L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
