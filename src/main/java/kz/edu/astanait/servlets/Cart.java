package kz.edu.astanait.servlets;

import kz.edu.astanait.classes.Product;
import kz.edu.astanait.controllers.ProductController;
import kz.edu.astanait.controllers.UserController;
import kz.edu.astanait.controllers.UserProductController;
import kz.edu.astanait.proxy.ProductAccess;
import kz.edu.astanait.proxy.ProxyProductAccess;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Cart", urlPatterns = "/cart")
public class Cart extends HttpServlet {
    private static UserProductController userProductController = new UserProductController();
    private static ProductController productController = new ProductController();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Cookie[] cc = request.getCookies();
        ArrayList<Product> l = new ArrayList<>();
        int user_id = 0;
        for (Cookie i : cc)
        {
            if(i.getName().equals("user_id"))
            {

                user_id = Integer.parseInt(i.getValue());
            }
        }
        String msg = "";
        int total = 0;

        if(action.equalsIgnoreCase("buy"))
        {
            int id = Integer.parseInt(request.getParameter("id"));

            l = userProductController.getById(user_id);
            ProductAccess pa = new ProxyProductAccess(l);

            if(pa.productCounter().equals(""))
            {
                userProductController.add(id, user_id);
                l = userProductController.getById(user_id);
                total = productController.getTotalPrice(l);
            }
            else
            {
                msg = pa.productCounter();
                session.setAttribute("msg", msg);
                response.sendRedirect("jsp/Cart.jsp");
                return ;
            }

        }
        else if(action.equalsIgnoreCase("remove"))
        {
            int id = Integer.parseInt(request.getParameter("id"));
            userProductController.delete(id, user_id);

            l = userProductController.getById(user_id);
            total = productController.getTotalPrice(l);

            session.setAttribute("cart", l);
            response.sendRedirect(getServletContext().getContextPath() + "/jsp/Cart.jsp");
        }

        session.setAttribute("total", total);
        session.setAttribute("msg", msg);
        session.setAttribute("cart", l);

    }
}
