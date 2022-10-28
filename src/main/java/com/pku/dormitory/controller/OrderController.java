package com.pku.dormitory.controller;

import com.pku.dormitory.domain.Student;
import com.pku.dormitory.service.BuildingService;
import com.pku.dormitory.service.OrderService;
import com.pku.dormitory.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    StudentService studentService;

    @Autowired
    BuildingService buildingService;

    @PostMapping("/order")
    public String postOrder(String uid, Integer building) {
        // uid是学生的学号
        // building是宿舍楼号
        Student student = studentService.getStudent(uid);
        try {
            int bid = buildingService.getIdByNumber(building);
            if (student == null) {
                return "未查询到此学生";
            } else if (student.getRoom() != null) {
                return "该学生已经分配宿舍" + student.getRoom();
            } else {
                if (student.getGid() == 0) {
                    orderService.postOrder(student.getId(), bid, 0, student.getGender());
                } else {
                    orderService.postOrder(student.getGid(), bid, 1, student.getGender());
                }
                return "提交订单";
            }
        } catch (Exception e) {
            return "没有查询到该宿舍楼";
        }
    }
}
