<%--
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
<div class="card container shadow-lg w-50 my-5">
    <div class=" px-4 py-3 ">
        <strong>SING IN</strong>
    </div>
        <%
        String error = request.getParameter("error");
        if(error!=null){
    %>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        Incorrect email or password!
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
        <%
        }
    %>
    <form class="px-4 my-3" action="/sing_in" method="post">
        <div class="mb-3">
            <label class="form-label"><strong>Email address</strong></label>
            <input type="email" class="form-control" placeholder="Insert email" name="userEmail">
        </div>
        <div class="mb-3"><label class="form-label"><strong>Password</strong></label>
            <input type="password" class="form-control"  placeholder="Insert password" name="userPassword">
        </div>
        <button type="submit" class="btn btn-success mt-3">SING IN</button>
    </form>
</div>
<%@include file="Footer.jsp"%>
</body>
</html>
