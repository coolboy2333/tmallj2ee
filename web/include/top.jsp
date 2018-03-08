<%--
  Created by IntelliJ IDEA.
  User: Danger
  Date: 2018/1/27
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
pageEncoding="UTF-8" isELIgnored="false" %>
<nav class="top">
    <div class="top_middle">
        <a href="${contextPath}">
            <span style="color: #c40000;margin: 0px;" class="glyphicon glyphicon-home redColor"></span>
            天猫首页
        </a>
        <span>喵，欢迎来天猫</span>
        <c:if test="${!empty user}">
            <a href="login.jsp">${user.name}</a>
            <a href="forelogout">退出</a>
        </c:if>
        <c:if test="${empty user}">
            <a href="login.jsp">请登陆</a>
            <a href="register.jsp">免费注册</a>
        </c:if>
        <span class="pull-right">
        <a href="forebought">我的订单</a>
        <a href="forecart">
            <span style="color: #c40000;margin: 0px" class="glyphicon glyphicon-shopping-cart redColor"></span>
            购物车<strong>${cartTotalItemNumber}</strong>件
        </a>
    </span>
    </div>

</nav>



