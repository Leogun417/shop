<%--
  Created by IntelliJ IDEA.
  User: 傲然
  Date: 2017/2/12
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<pg:pager items="${param.items}" maxPageItems="12" maxIndexPages="10" url="${param.url}" export="pageNumber">
    <c:forEach items="${param.params}" var="p">
        <pg:param name="${p}"/>
    </c:forEach>
    <br>
    共有${param.items}条记录，当前第${pageNumber}页
    <br>
    <pg:first>
        <a href="${pageUrl}">首页</a>
    </pg:first>
    <pg:prev>
        <a href="${pageUrl}">前一页</a>
    </pg:prev>
    <pg:pages>
        <a href="${pageUrl}">[${pageNumber}]</a>
    </pg:pages>
    <pg:next>
        <a href="${pageUrl}">后一页</a>
    </pg:next>
    <pg:last>
        <a href="${pageUrl}">尾页</a>
    </pg:last>
</pg:pager>


