package com.llw.web;

import com.llw.pojo.Book;
import com.llw.pojo.Page;
import com.llw.service.BookService;
import com.llw.service.impl.BookServiceImpl;
import com.llw.utils.WebBeanUtils;
import com.sun.deploy.nativesandbox.comm.Request;
import jdk.nashorn.internal.ir.RuntimeNode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "BookServlet",value = "/manager/admin/bookServlet")
public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数pageNo和pageSize 当前页码和每页显示记录条数
        int pageNo = WebBeanUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebBeanUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 调用BookService.page(pageNo，pageSize)获取Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("manager/admin/bookServlet?action=page");
        //3.保存page到Request域中
        req.setAttribute("page",page);
        //4.请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.queryBooks();
        req.setAttribute("books", books);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebBeanUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo+=1;
        Book book = WebBeanUtils.copyParamToBean(req.getParameterMap(), new Book());
        bookService.addBook(book);
        resp.sendRedirect(req.getContextPath() + "/manager/admin/bookServlet?action=page&pageNo="+pageNo);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        bookService.deleteBookById(WebBeanUtils.parseInt(req.getParameter("id"), 0));
        resp.sendRedirect(req.getContextPath() + "/manager/admin/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = bookService.queryBookById(WebBeanUtils.parseInt(req.getParameter("id"), 0));
        req.setAttribute("book", book);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = WebBeanUtils.copyParamToBean(req.getParameterMap(), new Book());
        bookService.updateBook(book);
        resp.sendRedirect(req.getContextPath() + "/manager/admin/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

}