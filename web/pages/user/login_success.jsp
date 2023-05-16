<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>小梁书屋登录成功页面</title>
	<%-- base相对路径 css样式表引入 jQuery文件引入 --%>
	<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<div id="header">
				<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">一起解锁知识的海洋</span>
			<%--jsp指令静态包含，登录成功后的菜单 --%>
			<%@include file="/pages/common/login_success_menu.jsp" %>
		</div>
		
		<div id="main">
		
			<h1>欢迎回来 <a href="index.jsp">转到主页</a></h1>
	
		</div>

		<%-- 页脚  --%>
		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>