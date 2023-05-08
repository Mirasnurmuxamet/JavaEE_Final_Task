package kz.bitlab.techorda.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.techorda.db.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/news_details")
public class NewsDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = (Users) req.getSession().getAttribute("currentUser");
        if (user != null) {
            int id = Integer.parseInt(req.getParameter("id"));
            News news = DBConnection.getNewsById(id);
            if(news!=null){
                ArrayList<NewsCategories> categories = DBConnection.getNewsCategories();
                req.setAttribute("newsCategories", categories);

                req.setAttribute("news1", news);

                ArrayList<Comments> comments = DBConnection.getComments(id);

                req.setAttribute("allComments", comments);

                req.getRequestDispatcher("NewsDetails.jsp").forward(req,resp);
            }
            resp.sendRedirect("/sing_in");
        }
        else {
            resp.sendRedirect("/sing_in");
        }
    }
}
