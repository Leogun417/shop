<%--
  Created by IntelliJ IDEA.
  User: 傲然
  Date: 2017/2/15
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商品管理</title>
</head>
<body>
<a href="good.do?method=addGoodPage">点击添加商品</a>
<div align="center">
    <h1>商品管理</h1>
    <hr>
    <table class="thin-border" cellpadding="0" cellspacing="0" width="700">
        <tr>
            <td>商品图片</td>
            <td>商品名称</td>
            <td>商品价格</td>
            <td>商品库存</td>
            <td>商品状态</td>
            <td>操　　作</td>
        </tr>
        <c:forEach items="${goodPager.datas}" var="good">
            <tr>
                <td><img src="<%=request.getContextPath()%>/upload/${good.img}" width="60" height="80"></td>
                <td>${good.name}</td>
                <td>${good.price}</td>
                <td>${good.stock}</td>
                <td>
                    <c:if test="${good.status eq 0}">已下架</c:if>
                    <c:if test="${good.status eq 1}">在　售</c:if>
                </td>
                <td><a href="good.do?method=updateGoodPage">编辑</a>　　<a href="good.do?method=deleteGood">删除</a></td>
            </tr>
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
