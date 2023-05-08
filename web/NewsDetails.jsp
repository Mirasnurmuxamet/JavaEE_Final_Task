<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.bitlab.techorda.db.News" %>
<%@ page import="kz.bitlab.techorda.db.Comments" %><%--
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
<%
    News news = (News) request.getAttribute("news1");
    if(news!=null){
%>
<div class="card container shadow-lg  my-2">
    <div class="row px-4 py-3">
        <div class="col-10" >
            <h3><%=news.getTitle()%></h3>
        </div>
        <div class="col-2 text-secondary text-end">
            <span ><%=news.getCategoryId().getNameCategory()%></span>
        </div>
    </div>
    <div class="row px-4 py-3 ">
        <div  class="col-12">
            <p class="col-12"><%=news.getContent()%></p>
        </div>
    </div>
    <div class="row px-4 pt-1 pb-0 ">
        <div  class="col-12 text-secondary text-end">
            <span>Post At <%=news.getPost_Date()%></span>
        </div>
    </div>
    <div class=" px-4 py-3 ">
        <%
            if(user!=null && user.getRoleId()==1){
        %>
        <%@include file="EditNewsModalForm.jsp"%>
        <%
            }
        %>
    </div>

</div>
<%
    if(user!=null){
%>
<div class="card container shadow-lg  my-2 p-3">
    <form  action="/add_comment" method="post">
        <input type="hidden" name="news_id" value="<%=news.getId()%>">
        <div class="row">
            <div class="col-12">
                <textarea class="form-control" placeholder="Insert comment" name="comment"></textarea>
            </div>
        </div>
        <button type="submit" class="btn btn-outline-dark btn-sm mt-3">ADD COMMENT</button>
    </form>
</div>
<%
    ArrayList<Comments> comments = (ArrayList<Comments>) request.getAttribute("allComments");
    if(comments!=null){
        for(Comments c: comments){
%>
<div class="container ">
    <div class="row">
            <div class="list-group shadow-lg col-6 ms-auto">
            <div class="list-group-item list-group-item-action">
                <div class="d-flex w-100 justify-content-between">
                    <h5 class="mb-1">Comment by <%=c.getUser().getFullName()%></h5>
                    <small class="text-body-secondary"><%=c.getPostTime()%></small>
                </div>
                <p class="mb-1"><%=c.getComment()%></p>
                <span class="text-body-secondary">
                    <%
                        if(c.getUser().getId()==user.getId() || user.getRoleId()==1){
                    %>
                     <div class="row-cols-sm-1 ms-auto">
                        <form action="/delete_comment" method="post">
                            <input type="hidden" name="comment_Id" value="<%=c.getId()%>">
                            <input type="hidden" name="comment_NewsId" value="<%=news.getId()%>">
                            <button type="submit" class="btn btn-outline-danger btn-sm ms-auto">
                                DELETE
                            </button>
                        </form>
                    </div>
                    <%
                        }
                    %>
                </span>
            </div>
        </div>
    </div>
</div>

<%
        }
    }
    }
    }
%>

<%@include file="Footer.jsp"%>
</body>
</html>
