<%--
  Created by IntelliJ IDEA.
  User: 傲然
  Date: 2017/2/12
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/main.js">
</script>
<head>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>
    <title><decorator:title default="网上商城"/></title>
</head>
<body>
<c:if test="${empty loginUser}">
    <a href="user.do?method=registerPage">用户注册</a>&nbsp;&nbsp;
    <a href="user.do?method=loginPage">用户登录</a>&nbsp;&nbsp;
    <a href="good.do?method=goodsList">浏览商品</a>&nbsp;&nbsp;
</c:if>
<c:if test="${not empty loginUser}">
    欢迎登录，${loginUser.nickname}!&nbsp;&nbsp;
    <a href="good.do?method=goodsList">浏览商品</a>&nbsp;&nbsp;
    <a href="user.do?method=showSelfInfoPage">我的信息</a>&nbsp;&nbsp;
    <a href="user.do?method=updateSelfPage">修改个人信息</a>&nbsp;&nbsp;
    <a href="user.do?method=registerPage">用户注册</a>&nbsp;&nbsp;
    <a href="user.do?method=userList">用户管理</a>&nbsp;&nbsp;
    <a href="category.do?method=categoryList">商品类别管理</a>&nbsp;&nbsp;
    <a href="user.do?method=logout">退出</a>&nbsp;&nbsp;
</c:if>
<hr/>
<decorator:body/>
</body>
</html>
