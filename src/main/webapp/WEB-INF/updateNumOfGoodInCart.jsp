<%--
  Created by IntelliJ IDEA.
  User: 傲然
  Date: 2017/2/27
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>编辑购买数量</title>
</head>
<body>
<div align="center">
    <h1>编辑购买数量</h1><hr>
    <form action="order.do?method=updateNumOfGoodInCart" method="post">
        <input type="hidden" name="goodId" value="${goodId}">
        <table class="thin-border" width="400" cellpadding="0" cellspacing="0">
            <tr>
                <td>请输入数量</td>
                <td><input type="text" name="number"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="确定">　
                    <input type="reset" value="重置">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
