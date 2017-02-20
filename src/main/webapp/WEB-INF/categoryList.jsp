<%--
  Created by IntelliJ IDEA.
  User: 傲然
  Date: 2017/2/20
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商品类别管理</title>
</head>
<body>
<div align="center">
    <h1>商品类别管理</h1><hr>
    <div align="left">
        <a href="category.do?method=addCategoryPage">点击添加类别</a><br>
    </div>
    <c:forEach items="${categoryList}" var="category">
        <a href="category.do?method=updateCategoryPage&id=${category.id}" class="category">${category.name}</a>&nbsp;&nbsp;&nbsp;&nbsp;
    </c:forEach>
</div>
</body>
</html>
