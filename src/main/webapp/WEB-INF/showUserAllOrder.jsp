<%--
  Created by IntelliJ IDEA.
  User: 傲然
  Date: 2017/2/27
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>我的订单</title>
</head>
<body>
<div align="center">
    <h1>我的订单</h1>
    <hr>
    <table class="thin-border" width="750" cellpadding="0" cellspacing="0">
        <tr>
            <td align="center">订单编号</td>
            <td align="center">下单时间</td>
            <td align="center">支付时间</td>
            <td align="center">发货时间</td>
            <td align="center">收货时间</td>
        </tr>
        <c:forEach items="${orderPager.datas}" var="order">
            <tr>
                <td align="center"><a href="order.do?method=showOrderInfoPage&orderId=${order.id}">${order.id}</a></td>
                <td align="center"><fmt:formatDate value="${order.buyDate}" pattern="yyyy-MM-dd HH:mm"/></td>
                <c:if test="${empty order.payDate}">
                    <td align="center">未付款</td>
                </c:if>
                <c:if test="${not empty order.payDate}">
                    <td align="center"><fmt:formatDate value="${order.payDate}" pattern="yyyy-MM-dd HH:mm"/></td>
                </c:if>

                <c:if test="${empty order.sendGoodDate}">
                    <td align="center">未发货</td>
                </c:if>
                <c:if test="${not empty order.sendGoodDate}">
                    <td align="center"><fmt:formatDate value="${order.sendGoodDate}"
                                                       pattern="yyyy-MM-dd HH:mm"/></td>
                </c:if>

                <c:if test="${empty order.confirmDate}">
                    <td align="center">未收货</td>
                </c:if>
                <c:if test="${not empty order.confirmDate}">
                    <td align="center"><fmt:formatDate value="${order.confirmDate}"
                                                       pattern="yyyy-MM-dd HH:mm"/></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <jsp:include page="includes/pager.jsp">
        <jsp:param name="params" value="method"/>
        <jsp:param name="url" value="order.do"/>
        <jsp:param name="items" value="${orderPager.totalRecord}"/>
    </jsp:include>
</div>
</body>
</html>
