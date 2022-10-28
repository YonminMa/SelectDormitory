package com.pku.dormitory.service.impl;

import com.pku.dormitory.dao.OrderRepository;
import com.pku.dormitory.domain.Order;
import com.pku.dormitory.domain.Room;
import com.pku.dormitory.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    BuildingService buildingService;

    @Autowired
    RoomService roomService;

    @Autowired
    GroupService groupService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    StudentService studentService;

    @Override
    public boolean postOrder(int oid, int bid, int type, int gender) {
        // oid为学生的id或者组队的id
        // bid是宿舍楼的id
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
                Order order = new Order(oid, type, rid);
                roomService.updateRoom(rid, need);
                orderRepository.save(order);
                if (type == 0) {
                    // 单人抢宿舍
                    studentService.updateRoomById(oid, room.getName());
                } else {
                    // 组队抢宿舍
                    studentService.updateRoomByGid(oid, room.getName());
                }
                System.out.println("分配宿舍成功");
                return true;
            }
        }
        return false;
    }
}
