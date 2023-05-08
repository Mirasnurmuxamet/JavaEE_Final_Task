package kz.bitlab.techorda.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.techorda.db.DBConnection;
import kz.bitlab.techorda.db.Users;

import java.io.IOException;

@WebServlet(value = "/update")
public class UpdateUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Users user = (Users) req.getSession().getAttribute("currentUser");
        if(user!=null) {
            int id = Integer.parseInt(req.getParameter("userId"));
            String email = req.getParameter("userEmail");
            String password = req.getParameter("userPassword");
            String repeatPassword = req.getParameter("userPasswordRepeat");
            String fullName = req.getParameter("userFullName");

            Users userOne = DBConnection.getUserId(id);
            if (userOne != null) {
                if (password.equals(repeatPassword)) {
                    userOne.setEmail(email);
                    userOne.setPassword(password);
                    userOne.setFullName(fullName);
                    userOne.setRoleId(1);
                    DBConnection.updateUser(userOne);
                    resp.sendRedirect("/profile?success");
                } else {
                    resp.sendRedirect("/profile?errorPassword");
                }
            }
        }
        else {
            resp.sendRedirect("/sing_in");
        }
    }
}