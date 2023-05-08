<%@ page import="kz.bitlab.techorda.db.Users" %><%--
  Created by IntelliJ IDEA.
  User: Miras
  Date: 06.05.2023
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="Head.jsp"%>
</head>
<body>
<%@include file="Navbar.jsp"%>
<div class="card container shadow-lg  my-5">
    <div class="px-4 py-3 text-center ">
        <h3>Welcome <%=user.getFullName()%></h3>
    </div>
    <div class="h-100">
    </div>
    <%@include file="ModalForm.jsp"%>
</div>
<%@include file="Footer.jsp"%>
</body>
</html>
