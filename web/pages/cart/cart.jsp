<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%-- base相对路径 css样式表引入 jQuery文件引入 --%>
	<%@include file="/pages/common/head.jsp"%>
	<script>
		$(function () {
			$(".Ieamcount").change(function () {
				var name = $(this).parent().prev().text();
				var id = $(this).attr("bookId");
				var count = this.value;
				if (confirm("确定修改《 "+name+" 》在购物车中的数量为 "+count+" 吗？")){
					location.href="${pageScope.basePath}cartServlet?action=updateItem&id="+id+"&count="+count;
				}else{
					this.value = this.defaultValue;
				}
			})
		})
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%--jsp指令静态包含，登录成功后的菜单 --%>
		<%@include file="/pages/common/login_success_menu.jsp" %>
	</div>
	
	<div id="main">
		<c:if test="${not empty sessionScope.cart.items}">
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${sessionScope.cart.items}" var="cartIeam" >
				<tr>
					<td>${cartIeam.value.name}</td>
					<td><input style="width: 25px;text-align:center;" class="Ieamcount" type="text" bookId="${cartIeam.key}" value="${cartIeam.value.count}"></td>
					<td>${cartIeam.value.price}</td>
					<td>${cartIeam.value.totalPrice}</td>
					<td><a href="cartServlet?action=deleteItem&id=${cartIeam.key}">删除</a></td>
				</tr>
			</c:forEach>

		</table>
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="manager/orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>
		<c:if test="${empty sessionScope.cart.items}">
			<div style="margin: 80px auto auto;border-collapse: collapse;text-align:center;">
				<span style=" font-size: 40px;text-align:center;"> <a style="color: red;" href="index.jsp">购物车空空如也！快去选购书籍吧！！</a></span>
			</div>

		</c:if>

	
	</div>

	<%-- 页脚  --%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>