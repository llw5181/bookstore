<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--只负责请求转发，去servlet获取数据请求转发到其他页面显示首页,最终去/pages/client/index.jsp这个页面--%>
<jsp:forward page="/client/bookServlet?action=page"></jsp:forward>