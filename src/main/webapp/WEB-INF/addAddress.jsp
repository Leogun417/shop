<%--
  Created by IntelliJ IDEA.
  User: 傲然
  Date: 2017/2/16
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加地址</title>
</head>
<body>
<div align="center">
    <h1>添加地址</h1>
    <hr/>
    <form name="addAddressForm" action="address.do?method=addAddress&from=${param.from}" method="post" onsubmit="return addAddressValidate()">
        <input hidden name="userId" value="${param.userId}"/>
        <table class="thin-border" width="500" cellpadding="0" cellspacing="0">
            <tr>
                <td align="center">详细地址</td>
                <td><input type="text" name="address"/><span class="errMsg">${errors.address}</span></td>
            </tr>

            <tr>
                <td align="center">联系电话</td>
                <td><input type="text" name="phone"/><span class="errMsg">${errors.phone}</span></td>
            </tr>

            <tr>
                <td align="center">邮政编码</td>
                <td><input type="text" name="postcode"/><span class="errMsg">${errors.postcode}</span></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="确　定">　<input type="reset" value="重　置"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
