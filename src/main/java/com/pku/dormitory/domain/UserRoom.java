package com.pku.dormitory.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Yonmin
 * @date 2022/12/2 9:15
 */
@Data
@TableName("user_room")
public class UserRoom {

    private Integer user_id;

    private Integer room_id;

    public UserRoom() {
    }

    public UserRoom(Integer user_id, Integer room_id) {
        this.user_id = user_id;
        this.room_id = room_id;
    }
}
