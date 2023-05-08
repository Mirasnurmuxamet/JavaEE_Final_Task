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
<div class="card container shadow-lg w-50 mt-5 mb-5">
    <div class=" px-4 py-3 ">
        <strong>SING UP</strong>
    </div>
    <form class="px-4 my-3" action="/register" method="post">
        <%
            String error = request.getParameter("errorEmail");
            if(error!=null){
        %>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            Email is busy!
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <%
            }
        %>
        <%
            String passwordError = request.getParameter("errorPassword");
            if(passwordError!=null){
        %>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            Password are not same!
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <%
            }
        %>

        <%
            String success = request.getParameter("success");
            if(success!=null){
        %>
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            User created successfully!
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <%
            }
        %>
        <div class="mb-3">
            <label class="form-label"><strong>Email address</strong></label>
            <input type="email" class="form-control" placeholder="Insert email" name="userEmail">
        </div>
        <div class="mb-3"><label class="form-label"><strong>Password</strong></label>
            <input type="password" class="form-control" placeholder="Insert password" name="userPassword">
        </div>
        <div class="mb-3"><label class="form-label"><strong>Repeat password</strong></label>
            <input type="password" class="form-control" placeholder="Insert password"  name="userPasswordRepeat">
        </div>
        <div class="mb-3"><label class="form-label"><strong>Full name</strong></label>
            <input type="text" class="form-control" placeholder="Insert full name" name="userFullName">
        </div>
        <button type="submit" class="btn btn-success mt-2">SING UP</button>
    </form>
</div>
<%@include file="Footer.jsp"%>
</body>
</html>
