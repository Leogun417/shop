<%--
  Created by IntelliJ IDEA.
  User: 傲然
  Date: 2017/2/20
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加商品类别</title>
</head>
<body>
<div align="center">
    <h1>添加商品类别</h1><hr>
    <form action="category.do?method=addCategory" method="post">
        <table width="500" cellpadding="0" cellspacing="0" class="thin-border">
            <tr>
                <td>类别名称</td>
                <td><input type="text" name="name"/><span class="errMsg">${errors.name}</span></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="添加"/>　<input type="reset" value="重置">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
