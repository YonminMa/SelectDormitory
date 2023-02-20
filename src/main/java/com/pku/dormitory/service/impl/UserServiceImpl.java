package com.pku.dormitory.service.impl;

import com.pku.dormitory.mapper.UserMapper;
import com.pku.dormitory.mapper.UserRoomMapper;
import com.pku.dormitory.service.UserService;
import com.pku.dormitory.utils.JwtUtils;
import com.pku.dormitory.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Yonmin
 * @date 2022/11/26 14:21
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRoomMapper userRoomMapper;

    @Override
    public Result getInfo(String accessToken) {
        String username = JwtUtils.getClaimsByToken(accessToken).getSubject();
        HashMap<String, Object> infoMap = userMapper.getInfo(username);
        return Result.ok().data(infoMap);
    }

    @Override
    public Result changePasswd(String accessToken, HashMap<String, String> params) {

        String username = JwtUtils.getClaimsByToken(accessToken).getSubject();
        String oldPasswd = params.get("oldPasswd");
        String newPasswd = params.get("newPasswd");

        if (oldPasswd.equals(newPasswd)) return Result.error("新旧密码一致");

        if (oldPasswd.equals(userMapper.getPwdByUsername(username))) {
            userMapper.changePasswd(username, newPasswd);
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    @Override
    public Result getRoom(String accessToken) {

        String username = JwtUtils.getClaimsByToken(accessToken).getSubject();
        int id = userMapper.getIdByUsername(username);
        if (userRoomMapper.existsUserId(id)) {
            int roomId = userMapper.getRidById(id);
            String roomName = userMapper.getRoomNameByRid(roomId);
            ArrayList<String> memberList = userMapper.getStudentNameByRid(roomId);

            return Result.ok()
                    .data("room_name", roomName)
                    .data("member", memberList);
        }
        return Result.error("未分配宿舍");
    }
}
