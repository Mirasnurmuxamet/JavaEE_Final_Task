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

@WebServlet(value = "/delete_news")
public class DeleteNewsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Users user = (Users) req.getSession().getAttribute("currentUser");
        if (user != null && user.getRoleId()==1) {
            int id = Integer.parseInt(req.getParameter("newsId"));

            News news = DBConnection.getNewsById(id);

            if (news != null) {
                DBConnection.deleteNews(id);
                resp.sendRedirect("/");
            }
            else{
                resp.sendRedirect("/");
            }

        }
        else {
            resp.sendRedirect("/sing_in");
        }
    }

}