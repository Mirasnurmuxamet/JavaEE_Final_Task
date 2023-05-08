<%@ page import="kz.bitlab.techorda.db.NewsCategories" %>
<div class="text-center mt-0 mb-2" >
    <div class="btn-group" role="group" aria-label="Basic outlined example">
        <button type="button"
                class="btn btn-outline-primary px-3"
                data-bs-toggle="modal"
                data-bs-target="#editNews" >
            EDIT
        </button>
        <button type="button"
                class="btn btn-outline-danger px-2"
                data-bs-toggle="modal"
                data-bs-target="#deleteNews">
            DELETE
        </button>
    </div>
</div>

<%--Modal Edit--%>
<div class="modal fade"
     id="editNews"
     data-bs-backdrop="static"
     data-bs-keyboard="false"
     tabindex="-1"
     aria-labelledby="staticBackdropLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <form  action="/edit_news" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5">Edit News</h1>
                    <button type="button"
                            class="btn-close"
                            data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>
                <div class="modal-body">
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
                    <input type="hidden" name="newsId" value="<%=news.getId()%>">
                    <div class="mb-3">
                        <label class="form-label"><strong>Title</strong></label>
                        <input type="text" class="form-control"  name="newsTitle" value="<%=news.getTitle()%>">
                    </div>
                    <div class="mb-3"><label class="form-label"><strong>News Category</strong></label>
                        <select class="form-control mb-3" name="newsCategory">
                            <%
                                ArrayList<NewsCategories> newsCategories = (ArrayList<NewsCategories>)
                                        request.getAttribute("newsCategories");
                                if(newsCategories!=null){
                                    for(NewsCategories category : newsCategories ){
                            %>
                            <option <%=(category.getId()==news.getCategoryId().getId() ? "selected" : "")%>
                                    value="<%=category.getId()%>"><%=category.getNameCategory()%>
                            </option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3"><label class="form-label"><strong>Content</strong></label>
                        <textarea class="form-control"
                                  rows="10" name="newsContent">
                            <%=news.getContent()%>
                        </textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button"
                            class="btn btn-secondary"
                            data-bs-dismiss="modal">Close
                    </button>
                    <button type="submit" class="btn btn-primary">Edit</button>
                </div>
            </div>
        </form>
    </div>
</div>


<%-- modal delete--%>
<div class="modal fade" id="deleteNews"
     data-bs-backdrop="static"
     data-bs-keyboard="false"
     tabindex="-1"
     aria-labelledby="staticBackdropLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <form action="/delete_news" method="post">
            <input type="hidden" name="newsId" value="<%=news.getId()%>">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5">Warning</h1>
                    <button type="button"
                            class="btn-close"
                            data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Are you sure?
                </div>
                <div class="modal-footer">
                    <button type="button"
                            class="btn btn-secondary"
                            data-bs-dismiss="modal">Close
                    </button>
                    <button type="submit" class="btn btn-primary">Delete</button>
                </div>
            </div>
        </form>
    </div>
</div>
