package com.pku.dormitory.service.impl;

import com.pku.dormitory.domain.Order;
import com.pku.dormitory.mq.MQSender;
import com.pku.dormitory.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * OrderService用于将订单提交给MQ
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    MQSender mqSender;

    @Override
    public void postOrder(Order order) {
        try {
            mqSender.sendOrder(order);
            System.out.println("订单提交成功");
        } catch (Exception e) {
            System.out.println("提交订单异常");
        }
    }
}
