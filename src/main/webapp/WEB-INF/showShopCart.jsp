<%--
  Created by IntelliJ IDEA.
  User: 傲然
  Date: 2017/2/27
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>我的购物车</title>
</head>
<body>
<div align="center">
    <h1>我的购物车</h1>
    <hr>
    <c:if test="${shopCart.isEmpty eq true}">
        当前购物车没有商品！
    </c:if>
    <c:if test="${shopCart.isEmpty eq false}">
        <form action="" method="post">
            <table class="thin-border" width="600" cellpadding="0" cellspacing="0">
                <tr>
                    <td align="center">商品名称</td>
                    <td align="center">商品单价</td>
                    <td align="center">商品数量</td>
                    <td align="center">商品总价</td>
                    <td align="center">操　　作</td>
                </tr>
                <c:forEach items="${shopCart.goods}" var="goodInCart">
                    <c:set var="totalPrice" value="${totalPrice + goodInCart.price}"/>
                    <tr>
                        <td>${goodInCart.good.name}</td>
                        <td align="center">${goodInCart.good.price}</td>
                        <td align="center">${goodInCart.number}　<a
                                href="order.do?method=updateNumOfGoodInCartPage&goodId=${goodInCart.goodId}">修改</a></td>
                        <td align="center">${goodInCart.price}</td>
                        <td align="center"><a href="order.do?method=deleteGoodInCart&goodId=${goodInCart.goodId}">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="6">
                        <a href="order.do?method=clearGoodInCart">清空购物车</a>　　　　　　　　　　　　　　　　　　　　　　
                        总计：￥${totalPrice}
                    </td>
                </tr>
                <tr>
                    <td colspan="6">请选择收货地址</td>
                </tr>
                <tr>
                    <td colspan="6">
                        <c:if test="${empty loginUser.addrList}">
                            当前还没有添加地址，<a href="address.do?method=addAddressPage&from=shopCart">点击添加</a>
                        </c:if>
                        <c:if test="${not empty loginUser.addrList}">
                            <c:forEach items="${loginUser.addrList}" var="address" varStatus="status">
                                <c:if test="${status.index eq 0}">
                                    详细地址：${address.address}<br/>
                                    联系电话：${address.phone}<br/>
                                    邮政编码：${address.postcode}　　　　　　　　　　　　　　　　　　　　　　　　　　
                                    <input type="radio" checked="checked" name="addressId" value="${address.id}"/>
                                    <hr>
                                </c:if>
                                <c:if test="${status.index ne 0}">
                                    详细地址：${address.address}<br/>
                                    联系电话：${address.phone}<br/>
                                    邮政编码：${address.postcode}　　　　　　　　　　　　　　　　　　　　　　　　　　
                                    <input type="radio" name="addressId" value="${address.id}"/>
                                    <hr>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td colspan="6">
                        <c:if test="${not empty loginUser.addrList}">
                            <a href="">结　算</a>
                        </c:if>
                    </td>
                </tr>
            </table>
        </form>
    </c:if>
</div>
</body>
</html>
