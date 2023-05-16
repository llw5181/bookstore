package com.llw.web;

import com.google.gson.Gson;
import com.llw.pojo.Cart;
import com.llw.pojo.User;
import com.llw.service.UserService;
import com.llw.service.impl.UserServiceImpl;
import com.llw.utils.WebBeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@WebServlet(name = "UserServlet", value = "/userServlet")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //登录页面提交过来
        //调用工具类的方法将请求参数封装成一个bane
        User user = WebBeanUtils.copyParamToBean(req.getParameterMap(), new User());
        if (userService.login(user) != null) {
            Cookie cookieName = new Cookie("userName", user.getUsername());
            cookieName.setMaxAge(3 * 24 * 60 * 60);
            cookieName.setPath(req.getContextPath() + "/pages/user");
            resp.addCookie(cookieName);
            Cookie cookiePassword = new Cookie("userPassword", user.getPassword());
            cookiePassword.setMaxAge(3 * 24 * 60 * 60);
            cookiePassword.setPath(req.getContextPath() + "/pages/user");
            resp.addCookie(cookiePassword);
            req.getSession().setAttribute("user", userService.login(user));
            req.getSession().setAttribute("cart", new Cart());
            if ("admin".equals(user.getUsername())) {
                req.getSession().setAttribute("admin",userService.login(user));
            }
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        } else {
            req.setAttribute("username", user.getUsername());
            req.setAttribute("msg", "用户名或密码错误");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //注册页面提交过来
        //获取表单数据
        //调用工具类的方法将请求参数封装成一个bane
        User user = WebBeanUtils.copyParamToBean(req.getParameterMap(), new User());

        String code = req.getParameter("code");
        //谷歌验证码api，会放到session域中，通过com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY 获取session域中的验证码并且删除
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //验证码是否正确
        if (token != null && token.equalsIgnoreCase(code.trim())) {
            //用户名是否可用
            if (userService.existsUsername(user.getUsername())) {
                req.setAttribute("msg", "用户名已存在！");
                req.setAttribute("username", user.getUsername());
                req.setAttribute("email", user.getEmail());
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                //调用Dao写入数据库
                userService.registUser(user);
                //跳转登录界面
                req.setAttribute("username", user.getUsername());
                req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("msg", "验证码错误！");
            req.setAttribute("username", user.getUsername());
            req.setAttribute("email", user.getEmail());
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        String username = req.getParameter("username");
        // 调用userService.existsUsername();
        boolean existsUsername = userService.existsUsername(username);
        // 把返回的结果封装成为map对象
        Map<String, Object> map = new HashMap<>();
        map.put("existsUsername", existsUsername);

        Gson gson = new Gson();
        String json = gson.toJson(map);
        resp.getWriter().write(json);
    }
}
