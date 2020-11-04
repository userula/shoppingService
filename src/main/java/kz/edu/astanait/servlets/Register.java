package kz.edu.astanait.servlets;

import com.google.gson.Gson;
import kz.edu.astanait.classes.User;
import kz.edu.astanait.controllers.UserController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet(name = "Register", urlPatterns = "/register")
public class Register extends HttpServlet {
    private static UserController userController = new UserController();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User(
                request.getParameter("fname"),
                request.getParameter("lname"),
                request.getParameter("email"),
                request.getParameter("pass")
        );

        userController.add(user);
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, String> list = new HashMap<>();
        list.put("message", "empty");
        String email = request.getParameter("em");
        email = email.toLowerCase();
        if(!email.isEmpty()) {
            try {
                if (userController.checkEmail(email)) {
                    list.put("message", "Email already taken. Try another email");
                }
                else
                {
                    list.put("message", "success");
                }
            } catch (SQLException throwables) {
                list.put("message", "Sql error");
                throwables.printStackTrace();
            }
        }
        String json = new Gson().toJson(list);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
