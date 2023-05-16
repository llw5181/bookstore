package com.llw.web;

import com.llw.pojo.Book;
import com.llw.pojo.Page;
import com.llw.service.BookService;
import com.llw.service.impl.BookServiceImpl;
import com.llw.utils.WebBeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ClientBookServlet", value = "/client/bookServlet")
public class ClientBookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数pageNo和pageSize 当前页码和每页显示记录条数
        int pageNo = WebBeanUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebBeanUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 调用BookService.page(pageNo，pageSize)获取Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("client/bookServlet?action=page");
        //3.保存page到Request域中
        req.setAttribute("page", page);
        //4.请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数min和max 是book对象的price属性值
        int pageNo = WebBeanUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebBeanUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebBeanUtils.parseInt(req.getParameter("min"), 0);
        int max = WebBeanUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
        //2 调用BookService.page(pageNo，pageSize)获取Page对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize,min,max);

        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        if (req.getParameter("min")!=null){
            sb.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max")!=null){
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());

        //3.保存page到Request域中
        req.setAttribute("page", page);

        //4.请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

}
