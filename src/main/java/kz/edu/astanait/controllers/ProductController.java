package kz.edu.astanait.controllers;

import kz.edu.astanait.JDBC.DB;
import kz.edu.astanait.classes.Product;
import kz.edu.astanait.interfaces.IController;

import javax.ws.rs.BadRequestException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class ProductController implements IController {
    private static DB db = new DB();
    @Override
    public void add(Object entity) {
        Product product = (Product) entity;
        String sql = "INSERT INTO product(name, price, category, photo)"+
                "VALUES(?,?,?,?)";

        try {
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);

            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getCategory());
            stmt.setString(4, product.getPhoto());

            stmt.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void update(Object entity) {
        Product product = (Product) entity;
        String sql = "update product set name = ?, price = ?, category = ?, photo = ?" +
                " where id = ?";
        PreparedStatement stmt = null;
        try {

            stmt = db.getConnection().prepareStatement(sql);

            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getCategory());
            stmt.setString(4, product.getPhoto());
            stmt.setInt(5, product.getId());

            stmt.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void delete(Object entity) {
        int id = (int) entity;
        String sql = "delete from books where book_id = ?";
        PreparedStatement stmt = null;
        try {

            stmt = db.getConnection().prepareStatement(sql);

            stmt.setInt(1,id);

            stmt.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public LinkedList<Product> getAll() {
        LinkedList<Product> bookList = new LinkedList<>();
        try {
            Statement statement = db.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM product");
            Product b;
            while (rs.next()) {
                b = new Product.Builder().setId(rs.getInt("id")).setName(rs.getString("name"))
                        .setPrice(rs.getDouble("price")).setCategory(rs.getString("category"))
                        .setPhoto(rs.getString("photo"))
                        .build();
                bookList.add(b);
            }
            rs.close();
            statement.close();

            return bookList;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public Object getById(int id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        try {
            //Statement stmt = db.getConnection().createStatement();
            PreparedStatement pstmt = db.getConnection().prepareStatement(sql);
            //ResultSet rs = stmt.executeQuery(sql);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Product.Builder().setId(rs.getInt("id")).setName(rs.getString("name"))
                        .setPrice(rs.getDouble("price")).setCategory(rs.getString("category"))
                        .setPhoto(rs.getString("photo"))
                        .setCategory("sport").build();
            }
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getMessage());
        }
        return null;
    }

    public Product getByName(String name) {
        String sql = "SELECT * FROM product WHERE name LIKE ?";
        try {
            //Statement stmt = db.getConnection().createStatement();
            PreparedStatement pstmt = db.getConnection().prepareStatement(sql);
            //ResultSet rs = stmt.executeQuery(sql);
            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Product.Builder().setId(rs.getInt("id")).setName(rs.getString("name"))
                        .setPrice(rs.getDouble("price")).setCategory(rs.getString("category"))
                        .setPhoto(rs.getString("photo"))
                        .setCategory("sport").build();

            }
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getMessage());
        }
        return null;
    }

    public int getTotalPrice(ArrayList<Product> l)
    {
        int sum = 0;
        for (Product p : l)
        {
            sum += p.getPrice();
        }
        return sum;
    }
}
