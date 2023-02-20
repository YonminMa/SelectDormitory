package com.pku.dormitory.service;

import com.pku.dormitory.utils.Result;

import java.util.HashMap;

/**
 * @author Yonmin
 * @date 2022/12/4 12:10
 */
public interface OrderService {
    Result createOrder(String accessToken, HashMap<String, String> params);

    Result getOrderList(String accessToken);

    Result getOrderInfo(String accessToken, HashMap<String, String> params);
}
