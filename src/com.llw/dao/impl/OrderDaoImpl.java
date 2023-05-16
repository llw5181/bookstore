package com.llw.dao.impl;

import com.llw.dao.OrderDao;
import com.llw.pojo.Order;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public Integer saveOrder(Order order) {
        String sql = "insert into t_order(`orderId`,`createTime`,`price`,`status`,`userId`) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryOrders() {
        String sql = "select `orderId`,`createTime`,`price`,`status`,`userId` from t_order";
        return queryForList(Order.class,sql);
    }

    @Override
    public Integer changeOrderStatus(String orderId, int status) {
        String sql = "update t_order set status=? where orderId=?";
        return update(sql,status,orderId);
    }

    @Override
    public List<Order> queryOrdersByUserId(int userId) {
        String sql = "select `orderId`,`createTime`,`price`,`status`,`userId` from t_order where userId=? ";
        return queryForList(Order.class,sql,userId);
    }
}
