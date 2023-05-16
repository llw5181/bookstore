<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%-- base相对路径 css样式表引入 jQuery文件引入 --%>
	<%@include file="/pages/common/head.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单详细</span>
		<%--jsp指令静态包含，登录成功后的菜单 --%>
		<%@include file="/pages/common/login_success_menu.jsp" %>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
			</tr>
			<c:forEach items="${sessionScope.orderItems}" var="cart" >
				<tr>
					<td>${cart.name}</td>
					<td>${cart.count}</td>
					<td>${cart.price}</td>
					<td>${cart.totalPrice}</td>
				</tr>
			</c:forEach>

		</table>

	</div>

	<%-- 页脚  --%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>