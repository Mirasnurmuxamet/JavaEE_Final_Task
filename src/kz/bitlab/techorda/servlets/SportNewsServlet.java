package kz.bitlab.techorda.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.techorda.db.DBConnection;
import kz.bitlab.techorda.db.News;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/sport_news")
public class SportNewsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        ArrayList<News> news = DBConnection.getSportNews();

        request.setAttribute("allNews", news);
        request.getRequestDispatcher("SportNews.jsp").forward(request,response);
    }
}
