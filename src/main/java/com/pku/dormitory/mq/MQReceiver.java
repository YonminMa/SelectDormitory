package com.pku.dormitory.mq;

import com.pku.dormitory.domain.Order;
import com.pku.dormitory.domain.UserRoom;
import com.pku.dormitory.mapper.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;


/**
 * @author Yonmin
 * @date 2022/12/4 21:34
 */
@Service
public class MQReceiver {

    @Autowired
    RoomMapper roomMapper;

    @Autowired
    UserTeamMapper userTeamMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserRoomMapper userRoomMapper;

    @Autowired
    InfoMapper infoMapper;

    @RabbitListener(queues = MQConfig.ORDER_QUEUE)
    public void receive(Order order) {

        System.out.println("从消息队列中收到订单");

        Integer userId = order.getUid();

        Integer buildingId = order.getBuildingId();

        Integer gender = infoMapper.getGenderById(userId);

        // 获取一起选宿舍人数
        int need = order.getGroupId() == 0 ? 1 : userTeamMapper.getUserIdsByTeamId(order.getGroupId()).size();
        // 获取符合条件的宿舍
        Integer roomId = roomMapper.getRoomIdByBuildingAndGenderAndRest(buildingId, gender, need);

        order.setRoomId(roomId);

        // 更新room表的剩余人数
        roomMapper.updateRestByIdAndNeed(roomId, need);

        // 添加user_room新记录
        if (order.getGroupId() == 0) {
            // 一个人选宿舍
            userRoomMapper.insert(new UserRoom(userId, roomId));
        } else {
            /*
              组队抢宿舍
              每个人都要提交到消息队列队列
             */
            List<Integer> userIds = userTeamMapper.getUserIdsByTeamId(order.getGroupId());
            for (Integer id : userIds) {
                userRoomMapper.insert(new UserRoom(id, roomId));
            }
        }
        order.setFinishTime(new Timestamp(System.currentTimeMillis()));
        order.setStatus(1);
        orderMapper.updateById(order);
    }
}
