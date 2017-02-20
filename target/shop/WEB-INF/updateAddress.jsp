<%--
  Created by IntelliJ IDEA.
  User: 傲然
  Date: 2017/2/17
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>编辑地址</title>
</head>
<body>
<div align="center">
    <h1>编辑地址</h1>
    <hr/>
    <form name="addAddressForm" action="address.do?method=updateAddress&from=${param.from}" method="post" onsubmit="return addAddressValidate()">
        <input hidden name="addrId" value="${param.addressId}"/>
        <table class="thin-border" width="500" cellpadding="0" cellspacing="0">
            <tr>
                <td align="center">详细地址</td>
                <td><input type="text" name="address" value="${toUpdateAddress.address}"/><span class="errMsg"></span></td>
            </tr>

            <tr>
                <td align="center">联系电话</td>
                <td><input type="text" name="phone" value="${toUpdateAddress.phone}"/><span class="errMsg"></span></td>
            </tr>

            <tr>
                <td align="center">邮政编码</td>
                <td><input type="text" name="postcode" value="${toUpdateAddress.postcode}"/><span class="errMsg"></span></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="确　定">　<input type="reset" value="重　置"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
