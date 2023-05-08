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

@WebServlet(value = "/home.html")
public class HomeServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String key = request.getParameter("key");
        if(key!=null){
            ArrayList<News> news = DBConnection.getSearchNews("%"+key+"%");
            request.setAttribute("allNews", news);

        }else {
            ArrayList<News> news = DBConnection.getNews();
            request.setAttribute("allNews", news);
        }

        request.getRequestDispatcher("AllNews.jsp").forward(request,response);
    }
}
