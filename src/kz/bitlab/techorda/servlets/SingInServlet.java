package kz.bitlab.techorda.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kz.bitlab.techorda.db.DBConnection;
import kz.bitlab.techorda.db.Users;

import java.io.IOException;

@WebServlet(value = "/sing_in")
public class SingInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("SingIn.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("userEmail");
        String password = req.getParameter("userPassword");

        Users user = DBConnection.getUser(email);

        if(user!=null && password.equals(user.getPassword())){
            HttpSession  session = req.getSession();
            session.setAttribute("currentUser", user);
            resp.sendRedirect("/profile");
        }
        else {
            resp.sendRedirect("/sing_in?error");
        }

    }
}
