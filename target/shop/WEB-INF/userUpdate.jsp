<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 傲然
  Date: 2017/2/15
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改用户信息</title>
</head>
<body>
<div align="center">
    <h1>修改用户信息</h1>
    <hr/>
    <c:if test="${empty toUpdateUser}">
    <form name="updateForm" action="user.do?method=updateSelf" method="post" onsubmit="return updateValidate()">
    </c:if>
    <c:if test="${not empty toUpdateUser}">
    <form name="updateForm" action="user.do?method=updateUser" method="post" onsubmit="return updateValidate()">
    </c:if>


        <c:if test="${empty toUpdateUser}">
            <input type="hidden" name="id" value="${loginUser.id}">
        </c:if>
        <c:if test="${not empty toUpdateUser}">
            <input type="hidden" name="id" value="${toUpdateUser.id}">
        </c:if>
        <table class="thin-border" width="500" cellpadding="0" cellspacing="0">
            <tr>
                <td align="center">账　号</td>
                <td>
                    <c:if test="${empty toUpdateUser}">
                        ${loginUser.username}
                    </c:if>
                    <c:if test="${not empty toUpdateUser}">
                        ${toUpdateUser.username}
                    </c:if>
                </td>
            </tr>

            <tr>
                <td align="center">密　码</td>
                <td><input type="password" name="password"/></td>
            </tr>

            <tr>
                <td align="center">昵　称</td>
                <td>
                    <c:if test="${empty toUpdateUser}">
                        <input type="text" name="nickname" value="${loginUser.nickname}"/>
                    </c:if>
                    <c:if test="${not empty toUpdateUser}">
                        <input type="text" name="nickname" value="${toUpdateUser.nickname}"/>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="修　改">　<input type="reset" value="重　置"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
