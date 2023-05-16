package com.llw.service;

import com.llw.pojo.Cart;
import com.llw.pojo.Order;
import com.llw.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    /**
     *  根据购物车对象和用户id生成订单和订单详细
     * @param cart 购物车信息
     * @param userId 当前登录用户id
     * @return 返回订单编号
     */
    public String createOrder(Cart cart,Integer userId);

    /**
     * 提供给管理员的 查看所有订单
     * @return 返回数据库表中的所有订单
     */
    public List<Order> showAllOrders();

    /**
     * 提供给管理员的，发货，将订单状态由0改为1
     * @param orderId
     */
    public void sendOrder(String orderId);

    /**
     * 根据订单编号查看订单详细
     * @param orderId
     * @return 返回订单详细集合
     */
    public List<OrderItem> showOrderDetail(String orderId);

    /**
     * 提供给普通客户的，根据用户编号查看用户的订单
     * @param userId
     * @return 返回用户的订单
     */
    public List<Order> showMyOrders(Integer userId);

    /**
     * 提供给用户的，确认收货，订单状态由1变为2
     * @param orderId
     */
    public void receiverOrder(String orderId);
}
