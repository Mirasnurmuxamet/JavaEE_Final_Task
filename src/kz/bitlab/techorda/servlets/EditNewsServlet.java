package kz.bitlab.techorda.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.techorda.db.DBConnection;
import kz.bitlab.techorda.db.News;
import kz.bitlab.techorda.db.NewsCategories;
import kz.bitlab.techorda.db.Users;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/edit_news")
public class EditNewsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Users user = (Users) req.getSession().getAttribute("currentUser");
        if (user != null && user.getRoleId()==1) {
            int id = Integer.parseInt(req.getParameter("newsId"));
            String title = req.getParameter("newsTitle");
            String content = req.getParameter("newsContent");
            int category = Integer.parseInt(req.getParameter("newsCategory"));
            News news = DBConnection.getNewsById(id);
            if(news!=null) {
                if (title.length() != 0 && content.length() != 0) {
                    news.setContent(content);
                    news.setTitle(title);

                    NewsCategories newsCategories = new NewsCategories();
                    newsCategories.setId(category);

                    news.setCategoryId(newsCategories);

                    DBConnection.updateNews(news);
                    resp.sendRedirect("/news_details?id="+id);
                } else {
                    resp.sendRedirect("/add_news?error");
                }
            }
            else {
                resp.sendRedirect("/");
            }

        }
        else {
            resp.sendRedirect("/sing_in");
        }
    }
}