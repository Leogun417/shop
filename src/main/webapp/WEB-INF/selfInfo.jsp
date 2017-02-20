<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 傲然
  Date: 2017/2/16
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>个人信息</title>
</head>
<body>
<div align="center">
    <h1>个人信息</h1>
    <hr/>
    <table class="thin-border" width="700" cellpadding="0" cellspacing="0">
        <tr>
            <td align="center">账　号</td>
            <td>${loginUser.username}</td>
        </tr>

        <tr>
            <td align="center">密　码</td>
            <td>${loginUser.password}</td>
        </tr>

        <tr>
            <td align="center">昵　称</td>
            <td>${loginUser.nickname}</td>
        </tr>

        <tr>
            <td align="center">权限类型</td>
            <td>
                <c:if test="${loginUser.type eq 0}">用　户</c:if>
                <c:if test="${loginUser.type eq 1}">管理员</c:if>
            </td>
        </tr>


        <tr>
            <td colspan="2">
                <a href="address.do?method=addAddressPage&from=selfInfo">点击添加地址</a>
            </td>
        </tr>

        <c:if test="${not empty loginUser.addrList}">
            <c:forEach items="${loginUser.addrList}" var="address">
                <tr>
                    <td colspan="2">
                        详细地址：${address.address}<br/>
                        联系电话：${address.phone}<br/>
                        邮政编码：${address.postcode}
                        <div align="right">
                            <a href="address.do?method=updateAddressPage&addressId=${address.id}&from=selfInfo">修改</a>&nbsp;&nbsp;
                            <a href="address.do?method=deleteAddress&addressId=${address.id}&from=selfInfo">删除</a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>
</body>
</html>
