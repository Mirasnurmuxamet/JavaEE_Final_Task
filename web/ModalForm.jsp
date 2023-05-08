<div class="text-center my-5" >
    <div class="btn-group" role="group" aria-label="Basic outlined example">
        <button type="button"
                class="btn btn-outline-primary px-2"
                data-bs-toggle="modal"
                data-bs-target="#update" >
            UPDATE
        </button>
        <button type="button"
                class="btn btn-outline-danger px-2"
                data-bs-toggle="modal"
                data-bs-target="#delete">
            DELETE
        </button>
    </div>
</div>

<%--Modal Update--%>
<div class="modal fade"
     id="update"
     data-bs-backdrop="static"
     data-bs-keyboard="false"
     tabindex="-1"
     aria-labelledby="staticBackdropLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <form  action="/update" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5">Modal title</h1>
                    <button type="button"
                            class="btn-close"
                            data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <%
                        String passwordError = request.getParameter("errorPassword");
                        if(passwordError!=null){
                    %>
                    <div class="alert alert-danger alert-dismissible fade show"
                         role="alert">
                        Password are not same!
                        <button type="button"
                                class="btn-close"
                                data-bs-dismiss="alert"
                                aria-label="Close">
                        </button>
                    </div>
                    <%
                        }
                    %>

                    <%
                        String success = request.getParameter("success");
                        if(success!=null){
                    %>

                    <div class="alert alert-success alert-dismissible fade show"
                         role="alert">
                        User updated successfully!
                        <button type="button"
                                class="btn-close"
                                ata-bs-dismiss="alert"
                                aria-label="Close">
                        </button>
                    </div>
                    <%
                        }
                    %>
                    <div>
                        <input type="hidden" name="userId" value="<%=user.getId()%>">
                        <input type="hidden" name="userRole" value="<%=user.getRoleId()%>">
                    </div>
                    <div class="mb-3">
                        <label class="form-label"><strong>Email address</strong></label>
                        <input type="email"
                               class="form-control"
                               name="userEmail"
                               value="<%=user.getEmail()%>">
                    </div>
                    <div class="mb-3"><label class="form-label"><strong>Password</strong></label>
                        <input type="password"
                               class="form-control"
                               name="userPassword"
                               value="<%=user.getPassword()%>">
                    </div>
                    <div class="mb-3"><label class="form-label"><strong>Repeat password</strong></label>
                        <input type="password"
                               class="form-control"
                               name="userPasswordRepeat"
                               value="<%=user.getPassword()%>">
                    </div>
                    <div class="mb-3"><label class="form-label"><strong>Full name</strong></label>
                        <input type="text"
                               class="form-control"
                               placeholder="Insert full name"
                               name="userFullName"
                               value="<%=user.getFullName()%>">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button"
                            class="btn btn-secondary"
                            data-bs-dismiss="modal">Close
                    </button>
                    <button type="submit" class="btn btn-primary">Update</button>
                </div>
            </div>
        </form>
    </div>
</div>


<%-- modal delete--%>
<div class="modal fade" id="delete"
     data-bs-backdrop="static"
     data-bs-keyboard="false"
     tabindex="-1"
     aria-labelledby="staticBackdropLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <form action="/delete" method="post">
            <input type="hidden" name="userId" value="<%=user.getId()%>">
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



