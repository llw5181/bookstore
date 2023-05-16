package com.llw.web;

import com.llw.pojo.Order;
import com.llw.service.OrderService;
import com.llw.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminOrderServlet",value = "/manager/admin/orderServlet")
public class AdminOrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = orderService.showAllOrders();

        req.getSession().setAttribute("orders",orders);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }

    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        orderService.sendOrder(req.getParameter("orderId"));
        resp.sendRedirect(req.getContextPath()+"/manager/admin/orderServlet?action=showAllOrders");
    }
}
