package kz.edu.astanait.servlets;

import kz.edu.astanait.classes.Product;
import kz.edu.astanait.controllers.ProductController;
import kz.edu.astanait.controllers.UserProductController;
import kz.edu.astanait.filter.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

@WebServlet(name = "Setter", urlPatterns = "/set")
public class Setter extends HttpServlet {
    private ProductController productController = new ProductController();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Criteria sportProducts = new CriteriaSport();
        Criteria clothProducts = new CriteriaClothes();
        Criteria gadjProducts = new CriteriaGadgets();
        HttpSession sessions = request.getSession();
        ArrayList<Product> l = new ArrayList<>();

        LinkedList<Product> products = productController.getAll();

        sessions.setAttribute("cart", l);

        sessions.setAttribute("msg", "");
        sessions.setAttribute("total", 0);

        sessions.setAttribute("sport", sportProducts.getList(products));

        sessions.setAttribute("cloth", clothProducts.getList(products));

        sessions.setAttribute("gadj", gadjProducts.getList(products));

        response.sendRedirect(getServletContext().getContextPath() + "/jsp/homepage.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
