<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 傲然
  Date: 2017/2/16
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息</title>
</head>
<body>
<div align="center">
    <h1>用户信息</h1>
    <hr/>
    <table class="thin-border" width="600" cellpadding="0" cellspacing="0">
        <tr>
            <td align="center" width="100">账　号</td>
            <td>${toShowUser.username}</td>
        </tr>

        <tr>
            <td align="center" width="100">密　码</td>
            <td>${toShowUser.password}</td>
        </tr>

        <tr>
            <td align="center" width="100">昵　称</td>
            <td>${toShowUser.nickname}</td>
        </tr>

        <tr>
            <td align="center" width="100">权限类型</td>
            <td>
                <c:if test="${toShowUser.type eq 0}">用　户</c:if>
                <c:if test="${toShowUser.type eq 1}">管理员</c:if>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <c:if test="${toShowUser.id eq loginUser.id}">
                    <a href="address.do?method=addAddressPage&from=userInfo">点击添加地址</a>
                </c:if>
                <c:if test="${empty toShowUser.addrList}">
                    <c:if test="${toShowUser.id ne loginUser.id}">
                        该用户暂未添加地址
                    </c:if>
                </c:if>
            </td>
        </tr>
        <c:if test="${not empty toShowUser.addrList}">
            <c:forEach items="${toShowUser.addrList}" var="address">
                <tr>
                    <td colspan="2">
                        详细地址：${address.address}<br/>
                        联系电话：${address.phone}<br/>
                        邮政编码：${address.postcode}
                        <c:if test="${toShowUser.id eq loginUser.id}">
                            <div align="right">
                                <a href="address.do?method=updateAddressPage&addressId=${address.id}&from=userInfo">修改</a>&nbsp;&nbsp;
                                <a href="address.do?method=deleteAddress&addressId=${address.id}&from=userInfo">删除</a>
                            </div>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>
</body>
</html>
