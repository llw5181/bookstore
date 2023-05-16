package com.llw.web;

import com.google.gson.Gson;
import com.llw.pojo.Book;
import com.llw.pojo.Cart;
import com.llw.pojo.CartItem;
import com.llw.service.BookService;
import com.llw.service.impl.BookServiceImpl;
import com.llw.utils.WebBeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CartServlet", value = "/cartServlet")
public class CartServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        int id = WebBeanUtils.parseInt(req.getParameter("id"), 0);
        Book book = bookService.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            cart.addItem(cartItem);
            req.getSession().setAttribute("lastCartName",cartItem.getName());
            Map<String, Object> map = new HashMap<>();
            map.put("totalCount", cart.getTotalCount());
            map.put("lastCartName", cartItem.getName());
            Gson gson = new Gson();
            String json = gson.toJson(map);
            resp.getWriter().write(json);
        }
    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.deleteItem(WebBeanUtils.parseInt(req.getParameter("id"), 0));
        resp.sendRedirect(req.getContextPath() + "/pages/cart/cart.jsp");
    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.clear();
        resp.sendRedirect(req.getContextPath() + "/pages/cart/cart.jsp");
    }

    protected void updateItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebBeanUtils.parseInt(req.getParameter("id"), 0);
        int count = WebBeanUtils.parseInt(req.getParameter("count"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.updateCount(id, count);
        resp.sendRedirect(req.getContextPath() + "/pages/cart/cart.jsp");
    }

}
