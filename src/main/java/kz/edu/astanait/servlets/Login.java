package kz.edu.astanait.servlets;

import kz.edu.astanait.classes.User;
import kz.edu.astanait.controllers.UserController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Login", urlPatterns = "/login")
public class Login extends HttpServlet {
    private static UserController userController = new UserController();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email").toLowerCase();
        String password = request.getParameter("pass");

        User user = null;
        try {
            user = userController.checkLogin(email,password);
            HttpSession session = request.getSession();

            if(user == null){
                String message = "Invalid email or password! Try again";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/index.jsp").forward(request,response);
            }
            else{
                Cookie cookie = new Cookie("user", user.getEmail());
                cookie.setMaxAge(60*60);
                response.addCookie(cookie);
                Cookie idCookie = new Cookie("user_id",String.valueOf(user.getId()));
                cookie.setMaxAge(60*60);
                response.addCookie(idCookie);
//                session.setAttribute("user",user);
//                session.setMaxInactiveInterval(60*60);

                request.getRequestDispatcher("/set").forward(request, response);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
