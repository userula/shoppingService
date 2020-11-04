package kz.edu.astanait.servlets;

import com.google.gson.Gson;
import kz.edu.astanait.classes.Product;
import kz.edu.astanait.controllers.ProductController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Search", urlPatterns = "/search")
public class Search extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("productName").trim();
        //String name = "tech";
        ProductController bc = new ProductController();
        Product b = bc.getByName(name);

        List<Product> list = new ArrayList();
        list.add(b);

        String json = new Gson().toJson(list);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
