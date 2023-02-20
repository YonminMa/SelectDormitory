package com.pku.dormitory.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    // 订单队列
    public static final String ORDER_QUEUE = "order_queue";
    // 定义交换机
    private static final String EXCHANGE_SPRINGBOOT_NAME = "/test_ex";

    // 将队列和交换机注入到Spring容器
    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_QUEUE);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_SPRINGBOOT_NAME);
    }

    // 关联交换机
    @Bean
    public Binding BindingOrderFanoutExchange(Queue orderQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(orderQueue).to(fanoutExchange);
    }
}
