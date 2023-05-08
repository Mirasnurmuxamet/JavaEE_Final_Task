package kz.bitlab.techorda.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.techorda.db.DBConnection;
import kz.bitlab.techorda.db.Users;

import java.io.IOException;

@WebServlet(value = "/delete")
public class DeleteUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Users user = (Users) req.getSession().getAttribute("currentUser");
        if (user != null) {
            int id = Integer.parseInt(req.getParameter("userId"));
            Users userOne = DBConnection.getUserId(id);
            if (userOne != null) {
                DBConnection.deleteUser(id);
                req.getSession().removeAttribute("currentUser");
                resp.sendRedirect("/sing_in");
            }
        }
        else {
            resp.sendRedirect("/sing_in");
        }
    }
}