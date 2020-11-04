package kz.edu.astanait.controllers;

import kz.edu.astanait.JDBC.DB;
import kz.edu.astanait.classes.User;
import kz.edu.astanait.interfaces.IController;

import javax.ws.rs.BadRequestException;
import java.sql.*;
import java.util.ArrayList;

public class UserController implements IController {
    private static DB db = new DB();
    @Override
    public void add(Object entity) {
        User user = (User) entity;
        String sql = "INSERT INTO users(fname, lname, email, password)"+
                "VALUES(?,?,?,?)";

        try {
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);

            stmt.setString(1,user.getFname());
            stmt.setString(2,user.getLname());
            stmt.setString(3,user.getEmail());
            stmt.setString(4,user.getPassword());

            stmt.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public User checkLogin(String email, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

        PreparedStatement stmt = db.getConnection().prepareStatement(sql);

        stmt.setString(1,email);
        stmt.setString(2,password);

        ResultSet rset = stmt.executeQuery();

        User user = null;

        if(rset.next()){
            user = new User(rset.getInt("id"),rset.getString("fname"),rset.getString("lname"),rset.getString("email"),rset.getString("password"));
        }
        return user;
    }

    public boolean checkEmail(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?";

            PreparedStatement stmt = db.getConnection().prepareStatement(sql);

            stmt.setString(1,email);
            ResultSet rset = stmt.executeQuery();
            if(rset.next()){

                return true;
            }
        return false;
    }

    @Override
    public void update(Object u) {
        User user = (User) u;
        String sql = "update users set fname = ?, lname = ?, email = ?, password = ? where id = ?";
        PreparedStatement stmt = null;
        try {

            stmt = db.getConnection().prepareStatement(sql);

            stmt.setString(1, user.getFname());
            stmt.setString(2, user.getLname());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setInt(5, user.getId());

            stmt.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Object user) {
        String sql = "DELETE FROM users WHERE id = ?";
        int id = (int) user;
        try {
            //Statement stmt = db.getConnection().createStatement();
            PreparedStatement pstmt = db.getConnection().prepareStatement(sql);
            //ResultSet rs = stmt.executeQuery(sql);
            pstmt.setInt(1, id);
            pstmt.execute();


        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getMessage());
        }
    }

    @Override
    public ArrayList<User> getAll() {
        ArrayList<User> userList = new ArrayList();
        try {
            Statement statement = db.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
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
    public User getById(int id) {
        String sql = "SELECT * FROM users WHERE user_id = ?";

        try {
            //Statement stmt = db.getConnection().createStatement();
            PreparedStatement pstmt = db.getConnection().prepareStatement(sql);
            //ResultSet rs = stmt.executeQuery(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("email"),
                        rs.getString("password") );

            }
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getMessage());
        }
        return null;
    }



}
