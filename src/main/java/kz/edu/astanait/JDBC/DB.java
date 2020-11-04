package kz.edu.astanait.JDBC;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
public class DB {
    public Connection getConnection() {

      //  Context initContext = null;
        Connection connection = null;
//        try
//        {
//            initContext = new InitialContext();
//            Context envContext  = (Context)initContext.lookup("java:/comp/env");
//            DataSource ds = (DataSource)envContext.lookup("jdbc/assignment4-oracle");
//            connection = ds.getConnection();
//        }
//        catch (NamingException | SQLException e)
//        {
//            e.printStackTrace();
//        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //load driver
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "asd", "asd");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

}
