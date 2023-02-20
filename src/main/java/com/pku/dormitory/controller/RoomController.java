package com.pku.dormitory.controller;

import com.pku.dormitory.service.RoomService;
import com.pku.dormitory.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yonmin
 * @date 2022/12/2 11:23
 */
@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping("/buildings")
    public Result getBuildings(@RequestAttribute(name = "access_token") String accessToken) {
        return roomService.getBuildings();
    }

    @GetMapping("/building")
    public Result getBuilding(@RequestAttribute(name = "access_token") String accessToken,
                              @RequestParam("id") Integer id) {
        return roomService.getBuilding(id);
    }

    @GetMapping("/room/{id}")
    public Result getRoomById(@RequestAttribute(name = "access_token") String accessToken,
                              @PathVariable Integer id) {
        return roomService.getRoomById(id);
    }

    @GetMapping("/empty")
    public Result getRestByGender(@RequestAttribute(name = "access_token") String accessToken) {
        return roomService.getRestByGender(accessToken);
    }
}
