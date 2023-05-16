<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%-- base相对路径 css样式表引入 jQuery文件引入 --%>
	<%@include file="/pages/common/head.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
		<%-- 静态包含manager管理模块的菜单 --%>
		<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<th>下单用户ID</th>
				<th>下单时间</th>
				<th>金额</th>
				<th>详情</th>
				<th>物流情况</th>
			</tr>
			<c:forEach items="${sessionScope.orders}" var="order">
				<tr>
					<td>${order.userId}</td>
					<td>${fn:substring(order.createTime,0,19)}</td>
					<td>${order.price}</td>
					<td><a href="manager/orderServlet?action=showOrderDetail&orderId=${order.orderId}">查看详情</a></td>
					<c:choose>
						<c:when test="${order.status==0}">
							<td><a href="manager/admin/orderServlet?action=sendOrder&orderId=${order.orderId}">一键发货</a></td>
						</c:when>
						<c:when test="${order.status==1}">
							<td>已发货</td>
						</c:when>
						<c:when test="${order.status==2}">
							<td>已签收</td>
						</c:when>
					</c:choose>
				</tr>
			</c:forEach>
		</table>
	</div>

	<%-- 页脚  --%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>