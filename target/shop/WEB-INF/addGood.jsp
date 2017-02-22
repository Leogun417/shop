<%--
  Created by IntelliJ IDEA.
  User: 傲然
  Date: 2017/2/21
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加商品</title>
</head>
<body>
<div align="center">
    <h1>添加商品</h1>
    <hr>
    <form action="good.do" method="post" enctype="multipart/form-data">
        <input type="hidden" name="method" value="addGood"/>
        <table class="thin-border" cellpadding="0" cellspacing="0" width="500">
            <tr>
                <td>商品名称</td>
                <td><input type="text" name="name"><span class="errMsg">${errors.name}</span> </td>
            </tr>
            <tr>
                <td>商品价格</td>
                <td><input type="text" name="price"><span class="errMsg">${errors.price}</span></td>
            </tr>
            <tr>
                <td>商品库存</td>
                <td><input type="text" name="stock"><span class="errMsg">${errors.stock}</span></td>
            </tr>
            <tr>
                <td>商品类别</td>
                <td>
                    <select name="categoryId">
                        <c:forEach items="${categoryList}" var="category">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>商品图片</td>
                <td><input type="file" name="img"><span class="errMsg">${errors.img}</span></td>
            </tr>
            <tr>
                <td colspan="2">商品介绍</td>
            </tr>
            <tr>
                <td colspan="2"><textarea name="introduce" rows="10" cols="70"></textarea><span class="errMsg">${errors.introduce}</span></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="添加">　<input type="reset" value="重置">　　<span class="errMsg">${e}</span></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
