<%--
  Created by IntelliJ IDEA.
  User: 傲然
  Date: 2017/2/12
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
<div align="center">
    <h1>用户注册</h1>
    <hr/>
    <form name="registerForm" action="user.do?method=register" method="post" onsubmit="return registerValidate()">
        <table class="thin-border" width="500" cellpadding="0" cellspacing="0">
            <tr>
                <td align="center">账　号</td>
                <td><input type="text" name="username" value="${param.username}"/><span class="errMsg">${e}${errors.username}</span></td>
            </tr>

            <tr>
                <td align="center">密　码</td>
                <td><input type="password" name="password" value="${param.password}"/><span class="errMsg">${errors.password}</span></td>
            </tr>

            <tr>
                <td align="center">昵　称</td>
                <td><input type="text" name="nickname" value="${param.nickname}"/><span class="errMsg">${errors.nickname}</span></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="注　册">　<input type="reset" value="重　置"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
