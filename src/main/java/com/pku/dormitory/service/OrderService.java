package com.pku.dormitory.service;

public interface OrderService {

    boolean postOrder(int oid, int building, int type, int gender);
}
