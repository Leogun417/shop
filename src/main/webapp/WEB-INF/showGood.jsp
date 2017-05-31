<%--
  Created by IntelliJ IDEA.
  User: 傲然
  Date: 2017/2/21
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商品详情</title>
</head>
<body>
<div align="center">
    <h1>商品详情</h1>
    <hr>
    <table class="thin-border" cellpadding="0" cellspacing="0" width="400">
        <tr>
            <c:if test="${good.status eq 0}">
                <td>商品状态</td>
                <td>已下架</td>
            </c:if>
            <c:if test="${good.status eq 1}">
                <td colspan="2" align="right"><a href="order.do?from=showGood&method=addToCart&goodId=${good.id}">加入购物车</a></td>
            </c:if>
        </tr>
        <tr>
            <td colspan="2">商品图片</td>
        </tr>
        <tr>
            <td colspan="2" align="center"><img src="<%=request.getContextPath()%>/upload/${good.img}" width="120" height="160"></td>
        </tr>
        <tr>
            <td>商品名称</td>
            <td>${good.name}</td>
        </tr>
        <tr>
            <td>商品价格</td>
            <td>${good.price}</td>
        </tr>
        <tr>
            <td>商品库存</td>
            <td>${good.stock}</td>
        </tr>
        <tr>
            <td>商品类别</td>
            <td>${category.name}</td>
        </tr>
        <tr>
            <td colspan="2">商品介绍</td>
        </tr>
        <tr>
            <td colspan="2"><textarea name="introduce" rows="10" cols="70">${good.introduce}</textarea></td>
        </tr>
    </table>
</div>
</body>
</html>
