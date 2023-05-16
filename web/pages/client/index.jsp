<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>小梁书屋</title>
    <%-- base相对路径 css样式表引入 jQuery文件引入 --%>
    <%@include file="/pages/common/head.jsp" %>
    <script>
        $(function () {
            // 给加入购物车按钮绑定单击事件
            $("button.addToCart").click(function () {
                if (${empty sessionScope.user}) {
                    alert("请先登录！");
                    return false;
                } else {
                    var id = $(this).attr("bookId");
                    $.getJSON(
                        "cartServlet?action=ajaxAddItem",
                        {"id": id},
                        function (data) {
                            $("#cartTotalCount").text("购物车中有" + data.totalCount + "件商品");
                            $("#cartLastName").html("您刚刚将<span style='color: red' id='cartLastName'>" + data.lastCartName + "</span> 加入到了购物车中");
                        }
                    )
                }
            })
        })
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">小梁书屋</span>
    <div>
        <c:if test="${empty sessionScope.user }">
            <a href="pages/user/login.jsp">登录</a> ||
            <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        </c:if>
        <c:if test="${not empty sessionScope.user}">
            <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临小梁书屋</span>
            <a href="manager/orderServlet?action=showMyOeders">我的订单</a>
            <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
            <a href="pages/cart/cart.jsp">购物车</a>
            <c:if test="${not empty sessionScope.admin}">
                <a href="pages/manager/manager.jsp">后台管理</a>
            </c:if>
        </c:if>

    </div>
</div>
<div id="main">
    <div id="book">

        <div class="book_cond" >
            <form action="client/bookServlet" method="get">
                <input type="hidden" name="action" value="pageByPrice">
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="submit" value="查询"/>
            </form>
        </div>

        <div style="text-align: center">

            <c:if test="${empty sessionScope.cart.items}">
                <span id="cartTotalCount"> </span>
                <div id="cartLastName">
                    <span style="color: red;font-size: 21px"> 快选购商品吧！ </span>
                </div>
            </c:if>
            <c:if test="${not empty sessionScope.cart.items}">
                <span id="cartTotalCount">购物车中有${sessionScope.cart.totalCount}件商品</span>
                <div id="cartLastName">
                    您刚刚将<span style="color: red">${sessionScope.lastCartName}</span>加入到了购物车中
                </div>
            </c:if>

        </div>

        <c:forEach items="${requestScope.page.items}" var="book">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="${book.img_path}"/>
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">￥${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>
                    <div class="book_add">
                        <button bookId="${book.id}" class="addToCart">加入购物车</button>
                    </div>
                </div>
            </div>
        </c:forEach>


    </div>
    <%-- 分页条 --%>
    <jsp:include page="/pages/common/page_nav.jsp"></jsp:include>

</div>
<%-- 页脚  --%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>