<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.bitlab.techorda.db.News" %><%--
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


<div class="card container shadow  my-2">
    <div class="px-4 py-3 text-center ">
        <div class="btn-group">
            <a href="/" class="btn btn-dark active">All news</a>
            <a href="/sport_news" class="btn btn-dark">Sport</a>
            <a href="/political_news" class="btn btn-dark">Political</a>
            <a href="/business_news" class="btn btn-dark">Business and Finance</a>
            <a href="/media_news" class="btn btn-dark">Media and Art</a>
            <a href="/science_news" class="btn btn-dark">Science and Technology</a>
        </div>
    </div>
</div>
<%
    ArrayList<News> news = (ArrayList<News>) request.getAttribute("allNews");
    if(news!=null){
        for(News n: news){
%>
<div class="card container shadow-lg  my-2">
    <div class="row px-4 py-3">
        <div class="col-10" >
            <a class="text-decoration-none text-reset" href="/news_details?id=<%=n.getId()%>"><h3><%=n.getTitle()%></h3></a>
        </div>
        <div class="col-2 text-secondary text-end">
            <span ><%=n.getCategoryId().getNameCategory()%></span>
        </div>
    </div>
    <div class="row px-4 py-3 ">
        <div  class="col-12">
            <p class="col-11"><%=n.getContent()%></p>
        </div>
    </div>
    <div class="row px-4 pt-1 pb-2 ">
        <div  class="col-12 text-secondary text-end">
            <span>Post At <%=n.getPost_Date()%></span>
        </div>
    </div>
</div>
<%
        }
    }
%>
<%@include file="Footer.jsp"%>
</body>
</html>
