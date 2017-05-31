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
    <title>订单详情</title>
</head>
<body>
<div align="center">
    <h1>订单详情</h1>
    <hr>
    <form action="order.do?method=payFinishedPage" method="post">
        <input type="hidden" name="orderId" value="${order.id}">
        <table class="thin-border" width="800" cellpadding="0" cellspacing="0">
            <tr>
                <td align="center">商品图片</td>
                <td align="center">商品名称</td>
                <td align="center">商品单价</td>
                <td align="center">商品数量</td>
                <td align="center">商品总价</td>
                <td align="center">下单时间</td>
                <td align="center">支付时间</td>
                <td align="center">发货时间</td>
                <td align="center">收货时间</td>
            </tr>
            <c:forEach items="${order.goodList}" var="goodInCart">
                <tr>
                    <td><img src="<%=request.getContextPath()%>/upload/${goodInCart.good.img}" width="60" height="80"></td>
                    <td>${goodInCart.good.name}</td>
                    <td align="center">${goodInCart.good.price}</td>
                    <td align="center">${goodInCart.number}
                    <td align="center">${goodInCart.price}</td>
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
            <tr>
                <td colspan="9" align="right">　　　　　　　　　　　　　　　　　　　　　　
                    收货地址：${order.address.address}
                </td>
            </tr>
            <tr>
                <td colspan="9" align="right">　　　　　　　　　　　　　　　　　　　　　　
                    总计：￥${order.price}
                </td>
            </tr>
            <c:if test="${empty fromMethod}">
                <tr>
                    <td colspan="9">
                        <input type="submit" value="提交订单"/>
                    </td>
                </tr>
            </c:if>
        </table>
    </form>
</div>
</body>
</html>
