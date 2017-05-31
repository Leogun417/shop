<%--
  Created by IntelliJ IDEA.
  User: 傲然
  Date: 2017/2/15
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>网上商城</title>
</head>
<body>
<div align="center">
    <c:forEach items="${categoryList}" var="category">
        <a href="good.do?method=goodsPage&categoryId=${category.id}" class="category">${category.name}</a>&nbsp;&nbsp;&nbsp;&nbsp;
    </c:forEach>
</div>
<div align="center">
    <hr>
    <h3>最新折扣</h3>
    <textarea name="introduce" rows="5" cols="40">${notice}</textarea>
</div>

<div align="center">
    <h1>商品列表</h1>
    <hr>
    <table class="thin-border" cellpadding="0" cellspacing="0" width="700">
        <tr>
            <td align="center">商品图片</td>
            <td align="center">商品名称</td>
            <td align="center">商品类别</td>
            <td align="center">商品价格</td>
            <td align="center">操　　作</td>
        </tr>
        <c:forEach items="${goodPager.datas}" var="good">
        <tr>
            <td align="center"><img src="<%=request.getContextPath()%>/upload/${good.img}" width="60" height="80"></td>
            <td align="center"><a href="good.do?method=showGoodPage&id=${good.id}">${good.name}</a></td>
            <td align="center">${good.category.name}</td>
            <td align="center">${good.price}</td>
            <td align="center"><a href="order.do?from=goodsPage&method=addToCart&goodId=${good.id}">加入购物车</a></td>
            </c:forEach>
    </table>
    <jsp:include page="includes/pager.jsp">
        <jsp:param name="url" value="good.do"/>
        <jsp:param name="items" value="${goodPager.totalRecord}"/>
        <jsp:param name="params" value="method"/>
    </jsp:include>
</div>
</body>
</html>
