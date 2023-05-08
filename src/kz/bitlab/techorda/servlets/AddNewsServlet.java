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

@WebServlet(value = "/add_news")
public class AddNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Users user = (Users) req.getSession().getAttribute("currentUser");
        if (user != null && user.getRoleId()==1) {
            ArrayList<NewsCategories> newsCategories = DBConnection.getNewsCategories();
            if(newsCategories!=null){
                req.setAttribute("categories", newsCategories);
            }
            req.getRequestDispatcher("AddNews.jsp").forward(req,resp);
        }
        else {
            resp.sendRedirect("/sing_in");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Users user = (Users) req.getSession().getAttribute("currentUser");
        if (user != null && user.getRoleId()==1) {
            String title = req.getParameter("newsTitle");
            String content = req.getParameter("newsContent");
            int category = Integer.parseInt(req.getParameter("newsCategory"));
            if (title.length()!=0 && content.length()!=0) {
                News news = new News();
                news.setContent(content);
                news.setTitle(title);

                NewsCategories newsCategories = new NewsCategories();
                newsCategories.setId(category);

                news.setCategoryId(newsCategories);

                DBConnection.addNews(news);
                resp.sendRedirect("/");
            } else {
                resp.sendRedirect("/add_news?error");
            }
        }
        else {
            resp.sendRedirect("/sing_in");
        }
    }
}