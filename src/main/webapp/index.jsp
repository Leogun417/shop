<%--
  Created by IntelliJ IDEA.
  User: 傲然
  Date: 2017/2/12
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<% request.getRequestDispatcher("good.do?method=goodsPage").forward(request, response);%>
</body>
</html>
