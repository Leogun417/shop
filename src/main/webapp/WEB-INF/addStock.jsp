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
    <title>增加库存</title>
</head>
<body>
<div align="center">
    <h1>增加库存</h1><hr>
    <form action="good.do?method=addStock" method="post">
        <input type="hidden" name="id" value="${id}">
        <table class="thin-border" cellspacing="0" cellpadding="0" width="400">
            <tr>
                <td>需要增加的库存量</td>
                <td><input type="text" name="num"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="确定">　<input type="reset" value="重置"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
