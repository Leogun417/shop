<%--
  Created by IntelliJ IDEA.
  User: 傲然
  Date: 2017/2/15
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<div align="center">
    <h1>用户登录</h1>
    <hr/>
    <form name="loginForm" action="user.do?method=login" method="post" onsubmit="return loginValidate()">
        <table class="thin-border" width="500" cellpadding="0" cellspacing="0">
            <tr>
                <td align="center">账　号</td>
                <td><input type="text" name="username" value="${param.username}"/></td>
            </tr>

            <tr>
                <td align="center">密　码</td>
                <td><input type="password" name="password"/></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="登　录">　<input type="reset" value="重　置">　　<span class="errMsg">${e}</span></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
