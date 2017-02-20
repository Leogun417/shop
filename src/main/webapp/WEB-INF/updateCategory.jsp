<%--
  Created by IntelliJ IDEA.
  User: 傲然
  Date: 2017/2/20
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>编辑类别</title>
</head>
<body>
<div align="center">
    <h1>编辑类别</h1><hr>
    <form action="category.do?method=updateCategory" method="post">
        <input type="hidden" value="${toUpdateCategory.id}" name="id">
        <table width="500" cellpadding="0" cellspacing="0" class="thin-border">
            <tr>
                <td>类别名称</td>
                <td><input type="text" name="name" value="${toUpdateCategory.name}"/>&nbsp;&nbsp;<a href="category.do?method=deleteCategory&id=${toUpdateCategory.id}">删除</a> <span class="errMsg">${errors.name}</span></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="确定"/>　<input type="reset" value="重置">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
