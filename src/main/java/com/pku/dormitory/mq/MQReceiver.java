package com.pku.dormitory.mq;

import com.pku.dormitory.domain.Order;
import com.pku.dormitory.domain.Record;
import com.pku.dormitory.domain.Room;
import com.pku.dormitory.service.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 从消息队列中接收到订单，并根据订单内容进行宿舍分配
 */
@Service
public class MQReceiver {

    @Autowired
    BuildingService buildingService;

    @Autowired
    GroupService groupService;

    @Autowired
    RoomService roomService;

    @Autowired
    StudentService studentService;

    @Autowired
    ResultService resultService;

    @RabbitListener(queues = MQConfig.ORDER_QUEUE)
    public void receive(Order order) {
        System.out.println("从消息队列中收到订单");
        // oid为学生的id或者组队的id
        // bid是宿舍楼的id
        int bid = order.getBid();
        int gender = order.getGender();
        int type = order.getType();
        int oid = order.getOid();


        int rest = buildingService.checkRestByIdAndGender(bid, gender);
        int need = type == 0 ? 1 : groupService.getCountById(oid);

        if (rest <= 0) {
            System.out.println("宿舍床位不足，分配失败");
        } else {
            Room room = roomService.findRoomByRestAndGender(need, gender);
            if (room == null) {
                System.out.println("宿舍床位不足，分配失败");
            } else {
                int rid = room.getId();
                Record record = new Record(oid, type, rid);
                roomService.updateRoom(rid, need);
                resultService.save(record);
                if (type == 0) {
                    // 单人抢宿舍
                    studentService.updateRoomById(oid, room.getName());
                } else {
                    // 组队抢宿舍
                    studentService.updateRoomByGid(oid, room.getName());
                }
                System.out.println("分配宿舍成功");
            }
        }
    }
}
