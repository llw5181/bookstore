package com.llw.dao;

import com.llw.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {

    /**
     * 保存订单详细到数据库中
     * @param orderItem
     * @return 返回方法执行影响的数据库表记录行数
     */
    public Integer saveOrderItem(OrderItem orderItem);

    /**
     *  根据订单编号查询订单详细
     * @param orderId
     * @return 返回根据订单编号查询到的订单详细
     */
    public List<OrderItem> queryOrderItemByOrderId(String orderId);
}
