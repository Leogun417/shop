<%--
  Created by IntelliJ IDEA.
  User: 傲然
  Date: 2017/3/17
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>支付成功</title>
</head>
<body>
<div align="center">
    <h1>支付成功</h1><br>
    <h4>时间：<fmt:formatDate value="${paytime}" pattern="yyyy-MM-dd HH:mm"/> </h4>
</div>
</body>
</html>
