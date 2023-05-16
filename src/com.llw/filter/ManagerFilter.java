package com.llw.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器，拦截后台管理页面
 */
@WebFilter(filterName = "ManagerFilter", urlPatterns = {"/manager/*"} )
public class ManagerFilter implements Filter {

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

        Object user = httpServletRequest.getSession().getAttribute("user");
        if (user==null) {
            httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(httpServletRequest,httpServletResponse);
        }else {
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
