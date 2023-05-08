package kz.bitlab.techorda.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.techorda.db.DBConnection;
import kz.bitlab.techorda.db.Users;


import java.io.IOException;
@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("Register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("userEmail");
        String password = req.getParameter("userPassword");
        String repeatPassword = req.getParameter("userPasswordRepeat");
        String fullName = req.getParameter("userFullName");

        Users user = DBConnection.getUser(email);

        if(user==null){
            if(password.equals(repeatPassword)) {
                Users newUser = new Users();
                newUser.setEmail(email);
                newUser.setPassword(password);
                newUser.setFullName(fullName);
                newUser.setRoleId(2);
                DBConnection.addUser(newUser);
                resp.sendRedirect("/register?success");
            }
            else {
                resp.sendRedirect("/register?errorPassword");
            }
        }
        else {
            resp.sendRedirect("/register?errorEmail");
        }

    }
}