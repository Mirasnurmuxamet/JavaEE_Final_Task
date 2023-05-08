<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.bitlab.techorda.db.NewsCategories" %><%--
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
    ArrayList<NewsCategories> newsCategories = (ArrayList<NewsCategories>) request.getAttribute("categories");
%>
<div class="card container shadow-lg  mt-5 mb-5">
    <div class=" px-4 py-3 ">
        <strong>ADD NEWS</strong>
    </div>
    <form class="px-4 my-3" action="/add_news" method="post">
        <%
            String error = request.getParameter("error");
            if(error!=null){
        %>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            Fill in all lines
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <%
            }
        %>
        <div class="mb-3">
            <label class="form-label"><strong>Title</strong></label>
            <input type="text" class="form-control" placeholder="Insert title of news" name="newsTitle">
        </div>
        <div class="mb-3"><label class="form-label"><strong>News Category</strong></label>
            <select class="form-control mb-3" name="newsCategory">
                <%
                    if(newsCategories!=null){
                        for(NewsCategories category : newsCategories ){
                %>
                <option value="<%=category.getId()%>"><%=category.getNameCategory()%></option>
                <%
                        }
                    }
                %>
            </select>
        </div>
        <div class="mb-3"><label class="form-label"><strong>Content</strong></label>
            <textarea class="form-control" placeholder="Insert news" rows="10" name="newsContent"></textarea>
        </div>
        <button type="submit" class="btn btn-success mt-2">ADD NEWS</button>
    </form>
</div>
<%@include file="Footer.jsp"%>
</body>
</html>
