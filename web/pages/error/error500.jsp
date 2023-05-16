<%--
  Created by IntelliJ IDEA.
  User: 86131
  Date: 2023/2/1
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>服务器发生异常</title>
    <%-- base相对路径 css样式表引入 jQuery文件引入 --%>
    <%@include file="/pages/common/head.jsp"%>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>

</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">500</span>
    <%--jsp指令静态包含，登录成功后的菜单 --%>
    <%@include file="/pages/common/login_success_menu.jsp" %>
</div>

<div id="main">

    <h1>服务器发生异常！站长正在全力抢修！！</h1>
    <div style="margin: 80px auto auto;border-collapse: collapse;text-align:center;">
        <span style=" font-size: 40px;text-align:center;"> <a style="color: red;" href="index.jsp">返回首页</a></span>
    </div>
</div>

<%-- 页脚  --%>
<%@include file="/pages/common/footer.jsp"%>

</body>
</html>
