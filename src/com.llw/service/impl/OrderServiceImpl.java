package com.llw.service.impl;

import com.llw.dao.BookDao;
import com.llw.dao.OrderDao;
import com.llw.dao.OrderItemDao;
import com.llw.dao.impl.BookDaoImpl;
import com.llw.dao.impl.OrderDaoImpl;
import com.llw.dao.impl.OrderItemDaoImpl;
import com.llw.pojo.*;
import com.llw.service.OrderService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号唯一性，根据下单时间和用户id生成
        String orderId = System.currentTimeMillis()+""+userId;
        // 保存订单
        orderDao.saveOrder(new Order(orderId,new Date(),cart.getTotalPrice(),0,userId));

        // 遍历购物车里的每一个商品转化为订单项保存在数据库
        for (Map.Entry<Integer, CartItem> entry: cart.getItems().entrySet()) {
            //获取购物车中的每一个商品项
            CartItem cartItem = entry.getValue();
            //转化为每一个订单项
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            //保存订单到数据库
            orderItemDao.saveOrderItem(orderItem);

            // 卖出了书后，把数据库中书的库存和销量更新
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDao.updateBook(book);
        }
        //清空购物车
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        List<Order> orders = orderDao.queryOrders();
        return orders;
    }

    @Override
    public void sendOrder(String orderId) {
        orderDao.changeOrderStatus(orderId,1);
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        List<OrderItem> orderItems = orderItemDao.queryOrderItemByOrderId(orderId);
        return orderItems;
    }

    @Override
    public List<Order> showMyOrders(Integer userId) {
        return orderDao.queryOrdersByUserId(userId);
    }

    @Override
    public void receiverOrder(String orderId) {
        orderDao.changeOrderStatus(orderId,2);
    }
}
