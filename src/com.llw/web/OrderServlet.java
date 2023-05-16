package com.llw.web;

import com.llw.pojo.Cart;
import com.llw.pojo.Order;
import com.llw.pojo.OrderItem;
import com.llw.pojo.User;
import com.llw.service.OrderService;
import com.llw.service.impl.OrderServiceImpl;
import com.llw.utils.WebBeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderServlet",value = "/manager/orderServlet")
public class OrderServlet extends BaseServlet{
    
    private OrderService orderService = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车对象和userId
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User loginUser = (User) req.getSession().getAttribute("user");

        Integer userId = loginUser.getId();

        //调用service生成订单，返回订单编号
        String orderId = orderService.createOrder(cart, userId);

        //保存订单编号到域中
        req.getSession().setAttribute("orderId",orderId);
        //重定向提交订单成功界面
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }



    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //根据请求参数订单id查询订单详细
        List<OrderItem> orderItems = orderService.showOrderDetail(req.getParameter("orderId"));
        req.getSession().setAttribute("orderItems",orderItems);
        req.getRequestDispatcher("/pages/order/orderItem.jsp").forward(req, resp);

    }

    protected void showMyOeders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //如果user是null，则请求转发去登录界面
        User loginUser = (User) req.getSession().getAttribute("user");
        Integer userId = loginUser.getId();
        List<Order> myOrders = orderService.showMyOrders(userId);
        req.getSession().setAttribute("myOrders",myOrders);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);

    }

    protected void receiverOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        orderService.receiverOrder(req.getParameter("orderId"));
        resp.sendRedirect(req.getContextPath()+"/manager/orderServlet?action=showMyOeders");
    }

}
