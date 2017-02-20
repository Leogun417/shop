<%--
  Created by IntelliJ IDEA.
  User: 傲然
  Date: 2017/2/12
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户列表</title>
</head>
<body>
<div align="center">
    <h1>用户列表</h1>
    <hr/>
    <table class="thin-border" width="700" cellspacing="0" cellpadding="0">
        <tr>
            <td align="center">编　　号</td>
            <td align="center">账　　号</td>
            <td align="center">密　　码</td>
            <td align="center">昵　　称</td>
            <td align="center">用户类型</td>
            <td align="center">操　　作</td>
        </tr>
        <c:forEach items="${userPager.datas}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td><a href="user.do?method=showUserInfoPage&id=${user.id}">${user.nickname}</a></td>
                <td>
                    <c:if test="${user.type eq 0 }">用　户</c:if>
                    <c:if test="${user.type eq 1 }">管理员</c:if>
                    &nbsp;&nbsp;<a href="user.do?method=changeType&id=${user.id}">更改</a>
                </td>
                <td align="center">
                    <a href="user.do?method=deleteUser&id=${user.id}">删除</a>　<a
                        href="user.do?method=updateUserPage&userId=${user.id}">修改</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="6">
                <jsp:include page="includes/pager.jsp">
                    <jsp:param name="params" value="method"/>
                    <jsp:param name="url" value="user.do"/>
                    <jsp:param name="items" value="${userPager.totalRecord}"/>
                </jsp:include>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
