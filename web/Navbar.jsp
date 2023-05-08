<%@ page import="kz.bitlab.techorda.db.Users" %>
<%
    Users user =  (Users) request.getSession().getAttribute("currentUser");

%>
<nav class="navbar navbar-expand-lg bg-black text-light ">
    <div class="container-fluid  mx-2 " >
        <a class="navbar-brand text-light " href="#"><strong>NEWS</strong></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active text-light" aria-current="page" href="/">Home</a>
                </li>
                <%
                    if(user!=null){

                %>
                <%
                    if(user.getRoleId()==1){
                %>
                <li class="nav-item">
                    <a class="nav-link text-light" href="/add_news">Add news</a>
                </li>
                <%
                    }
                %>

                <li class="nav-item">
                    <a class="nav-link text-light" href="/profile"><%=user.getFullName()%></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-light" href="/logout">Logout</a>
                </li>
                <%
                    }
                    else {
                %>
                <li class="nav-item">
                    <a class="nav-link text-light" href="/register">Register</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-light" href="/sing_in">Sing in</a>
                </li>
                <%
                    }
                %>
            </ul>
            <form class="d-flex mt-3" role="search" action="/" method="get">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-light" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>