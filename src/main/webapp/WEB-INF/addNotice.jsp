<%--
  Created by IntelliJ IDEA.
  User: 傲然
  Date: 2017/2/23
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>发布公告</title>
</head>
<body>
<div align="center">
    <h1>发布公告</h1><hr>
    <form action="good.do?method=addNotice" method="post">
        <textarea name="notice" rows="5" cols="40"></textarea>
        <input type="submit" value="发布"/>
    </form>
</div>
</body>
</html>
