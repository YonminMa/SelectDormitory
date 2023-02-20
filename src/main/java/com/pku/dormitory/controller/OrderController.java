package com.pku.dormitory.controller;

import com.pku.dormitory.service.OrderService;
import com.pku.dormitory.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author Yonmin
 * @date 2022/12/4 12:08
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public Result createOrder(@RequestAttribute(name = "access_token") String accessToken,
                              @RequestBody HashMap<String, String> params) {
        return orderService.createOrder(accessToken, params);
    }

    @GetMapping("/list")
    public Result getOrderList(@RequestAttribute(name = "access_token") String accessToken) {
        return orderService.getOrderList(accessToken);
    }

    @GetMapping("/info")
    public Result getOrderInfo(@RequestAttribute(name = "access_token") String accessToken,
                               @RequestBody HashMap<String, String> params) {
        return orderService.getOrderInfo(accessToken, params);
    }
}
