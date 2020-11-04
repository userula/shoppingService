package kz.edu.astanait.servlets;

import kz.edu.astanait.controllers.UserProductController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "Logout", urlPatterns = "/logout")
public class Logout extends HttpServlet {
    private static UserProductController userProductController = new UserProductController();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cc = request.getCookies();
        HttpSession sessions = request.getSession();

        //remove the cookies
        for(int i = 0; i < cc.length; i++)
        {
            if(cc[i].getName().equals("user_id"))
            {
                int idd = Integer.parseInt(cc[i].getValue());
                userProductController.delete(idd);
            }
            cc[i].setMaxAge(0);
            cc[i].setValue("");
            response.addCookie(cc[i]);
        }

        //remove the sessions
        sessions.removeAttribute("cart");
        sessions.removeAttribute("msg");
        sessions.removeAttribute("gadj");
        sessions.removeAttribute("sport");
        sessions.removeAttribute("cloth");
        sessions.removeAttribute("total");
        response.sendRedirect(getServletContext().getContextPath());
    }
}
