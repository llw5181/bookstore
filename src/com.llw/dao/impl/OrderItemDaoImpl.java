package com.llw.dao.impl;

import com.llw.dao.OrderItemDao;
import com.llw.pojo.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public Integer saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`id`,`name`,`count`,`price`,`totalPrice`,`orderId`) values (?,?,?,?,?,?) ";
        return update(sql, orderItem.getId(),orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemByOrderId(String orderId) {
        String sql = "select `id`,`name`,`count`,`price`,`totalPrice`,`orderId` from t_order_item where orderId=? ";
        return queryForList(OrderItem.class,sql,orderId);
    }
}
