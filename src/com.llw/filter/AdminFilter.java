package com.llw.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter" , urlPatterns = {"/pages/manager/*","/manager/admin/*"})
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //将request和response强转成http协议的
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html;charset=UTF-8");

        Object admin = httpServletRequest.getSession().getAttribute("admin");
        if (admin==null) {
            httpServletRequest.getRequestDispatcher("/pages/error/error404.jsp").forward(httpServletRequest,httpServletResponse);
        }else {
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
