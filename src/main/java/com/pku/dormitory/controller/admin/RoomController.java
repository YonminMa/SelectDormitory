package com.pku.dormitory.controller.admin;


import com.pku.dormitory.domain.Room;
import com.pku.dormitory.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/room")
public class RoomController {

    @Autowired
    RoomService roomService;

    @PostMapping("/save")
    public String saveRoom(@RequestBody Room room) {
        roomService.saveRoom(room);
        return "保存room" + room.getName() + "成功";
    }

    @PutMapping("/update")
    public String updateRoom(int id, int decline) {
        roomService.updateRoom(id, decline);
        return "房间"+id+"的床位减少了"+decline;
    }
}
