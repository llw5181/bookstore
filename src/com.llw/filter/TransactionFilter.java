package com.llw.filter;

import com.llw.utils.JdbcUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


@WebFilter(filterName = "TransactionFilter", urlPatterns = {"/*"})
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
            JdbcUtils.commitAndClose(); //提交事务
        } catch (Exception e) {
            JdbcUtils.RollbackAndClose(); //回滚事务
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public void destroy() {

    }
}
