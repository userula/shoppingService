package kz.edu.astanait.controllers;

import kz.edu.astanait.JDBC.DB;
import kz.edu.astanait.classes.Product;
import kz.edu.astanait.classes.User;
import kz.edu.astanait.interfaces.IController;

import javax.servlet.http.Cookie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserProductController implements IController {
    private static DB db = new DB();

    public void add(int product_id, int user_id) {
        System.out.println(user_id);
        System.out.println(product_id);
        String sql = "INSERT INTO user_product(user_id, product_id) VALUES(?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = db.getConnection().prepareStatement(sql);
            stmt.setInt(1,user_id);
            stmt.setInt(2,product_id);
            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void add(Object entity) {

    }

    @Override
    public void update(Object entity) {

    }

    @Override
    public void delete(Object id) {
        int user_id = (int) id;
        try {
            PreparedStatement stmt = db.getConnection().prepareStatement("DELETE FROM user_product WHERE user_id = ?");

            stmt.setInt(1, user_id);

            stmt.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int product_id, int user_id) {
        try {
            PreparedStatement stmt = db.getConnection().prepareStatement("DELETE FROM user_product WHERE product_id = ? AND user_id = ?");

            stmt.setInt(1, product_id);
            stmt.setInt(2, user_id);

            stmt.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List getAll() {
        ArrayList<User> userList = new ArrayList();
        try {
            Statement statement = db.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user_product;");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            User u;
            while (resultSet.next()) {
                u = new User(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4),
                        resultSet.getString(5));
                userList.add(u);
            }
            resultSet.close();
            statement.close();

            return userList;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Product> getById(int id)
    {
        ArrayList<Product> list = new ArrayList();
        try {
            Statement statement = db.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM product JOIN user_product ub ON product.id = ub.product_id AND ub.user_id = " + id);
            ResultSetMetaData metaData = rs.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            Product pr1;
            while (rs.next()) {
                pr1 = new Product.Builder().setId(rs.getInt("id")).setName(rs.getString("name"))
                        .setPrice(rs.getDouble("price")).setCategory(rs.getString("category"))
                        .setPhoto(rs.getString("photo"))
                        .setCategory("sport").build();

                list.add(pr1);
            }
            rs.close();
            statement.close();

            return list;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}
