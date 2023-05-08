package kz.bitlab.techorda.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.techorda.db.Comments;
import kz.bitlab.techorda.db.DBConnection;
import kz.bitlab.techorda.db.News;
import kz.bitlab.techorda.db.Users;

import java.io.IOException;

@WebServlet(value = "/delete_comment")
public class DeleteCommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Users user = (Users) req.getSession().getAttribute("currentUser");
        if (user != null) {
            int id = Integer.parseInt(req.getParameter("comment_Id"));
            int newsId = Integer.parseInt(req.getParameter("comment_NewsId"));
            Comments comment = DBConnection.getCommentById(id);
            if (comment != null) {
                if(comment.getUser().getId()== user.getId()|| user.getRoleId()==1){
                    DBConnection.deleteComment(id);
                    resp.sendRedirect("/news_details?id="+newsId);
                }
                else {
                    resp.sendRedirect("/news_details?id="+newsId);
                }

            }
            else{
                resp.sendRedirect("/news_details?id="+newsId);;
            }

        }
        else {
            resp.sendRedirect("/sing_in");
        }
    }
}