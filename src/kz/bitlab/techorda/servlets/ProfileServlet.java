package kz.bitlab.techorda.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.techorda.db.Users;

import java.io.IOException;

@WebServlet(value = "/profile")
public class ProfileServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        Users user  = (Users) req.getSession().getAttribute("currentUser");
        if(user!=null) {
            req.getRequestDispatcher("Profile.jsp").forward(req, resp);
        }
        else {
            resp.sendRedirect("/sing_in");
        }
    }
}
