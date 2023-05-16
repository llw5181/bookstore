<%--
  Created by IntelliJ IDEA.
  User: 86131
  Date: 2023/1/18
  Time: 0:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
  <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临小梁书屋 </span>
  <a href="manager/orderServlet?action=showMyOeders">我的订单 </a>
  <a href="userServlet?action=logout">注销 </a>
  <a href="client/bookServlet?action=page&pageNo=${requestScope.page.pageNo}">返回 </a>
</div>
