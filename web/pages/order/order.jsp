<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
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
			<span class="wel_word">我的订单</span>
		<%--jsp指令静态包含，登录成功后的菜单 --%>
		<%@include file="/pages/common/login_success_menu.jsp" %>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<th>下单时间</th>
				<th>金额</th>
				<th>物流状态</th>
				<th>详情</th>
				<th>是否确认收货</th>
			</tr>
			<c:forEach items="${sessionScope.myOrders}" var="order">
				<tr>
					<td>${fn:substring(order.createTime,0,19)}</td>
					<td>${order.price}</td>
					<c:choose>
						<c:when test="${order.status==0}">
							<td>未发货</td>
						</c:when>
						<c:when test="${order.status==1}">
							<td>已发货</td>
						</c:when>
						<c:when test="${order.status==2}">
							<td>已签收</td>
						</c:when>
					</c:choose>
					<td><a href="manager/orderServlet?action=showOrderDetail&orderId=${order.orderId}">查看详情</a></td>
					<c:if test="${order.status!=2}">
						<td><a href="manager/orderServlet?action=receiverOrder&orderId=${order.orderId}">确认收货</a></td>
					</c:if>


				</tr>
			</c:forEach>

		</table>
		
	
	</div>

	<%-- 页脚  --%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>