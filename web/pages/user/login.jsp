<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>小梁书屋登录页面</title>
    <%-- base相对路径 css样式表引入 jQuery文件引入 --%>
    <%@include file="/pages/common/head.jsp"%>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">登录才能使用噢</span>
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎登录</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>小梁书屋会员</h1>
                    <a href="pages/user/regist.jsp">立即注册</a>
                </div>
                <div class="msg_cont">
                    <b></b>
                    <span class="errorMsg">${empty requestScope.msg ? "请输入用户名和密码" : requestScope.msg}</span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="login" />
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名"
                               autocomplete="off" tabindex="1" name="username" value="${empty requestScope.username ? cookie.userName.value : requestScope.username}"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码"
                               autocomplete="off" tabindex="1" name="password" value="${empty requestScope.username ? cookie.userPassword.value :null}"/>
                        <br/>
                        <br/>
                        <input type="submit" value="登录" id="sub_btn"/>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%-- 页脚  --%>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>