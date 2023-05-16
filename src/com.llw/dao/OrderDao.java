package com.llw.dao;

import com.llw.pojo.Order;

import java.util.List;

public interface OrderDao {

    /**
     * 保存订单到数据库中
     *
     * @param order
     * @return 返回方法执行影响数据库表的行数
     */
    public Integer saveOrder(Order order);

    /**
     * 查询数据库中的所有订单，供管理员使用
     *
     * @return 返回数据库中所有订单
     */
    public List<Order> queryOrders();

    /**
     * 修改订单状态，
     *
     * @param orderId 订单编号
     * @param status  订单状态 0表示未发货 1表示已发货 2表示已签收
     * @return 返回方法执行影响数据库表的记录行数
     */
    public Integer changeOrderStatus(String orderId, int status);

    /**
     * 根据用户id查询订单信息
     *
     * @param userId
     * @return 返回根据用户id查询到的使用订单信息
     */
    public List<Order> queryOrdersByUserId(int userId);
}
