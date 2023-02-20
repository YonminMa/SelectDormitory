package com.pku.dormitory.mq;

import com.pku.dormitory.domain.Order;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQSender {

    @Autowired
    AmqpTemplate amqpTemplate;

    public void sendOrder(Order order) {
        System.out.println("正在将订单发送给消息队列");
        amqpTemplate.convertAndSend("/test_ex", "", order);
        System.out.println("已将订单发送给消息队列");
    }
}
