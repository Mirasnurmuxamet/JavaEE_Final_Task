package kz.bitlab.techorda.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.techorda.db.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/add_comment")
public class AddCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Users user = (Users) req.getSession().getAttribute("currentUser");
        if (user != null) {
            int newsId = Integer.parseInt(req.getParameter("news_id"));
            String comment = req.getParameter("comment");

            News news = DBConnection.getNewsById(newsId);
            if (news!=null){
                Comments comments = new Comments();
                comments.setComment(comment);
                comments.setNews(news);
                comments.setUser(user);

                DBConnection.addComment(comments);
                resp.sendRedirect("/news_details?id="+newsId);
            } else {
                resp.sendRedirect("/");
            }
        }
        else {
            resp.sendRedirect("/sing_in");
        }
    }
}