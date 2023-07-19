package jm.task.core.jdbc.util;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    public static Connection connection(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/itmentor","root","root");
            return connection;
        } catch (SQLException e) {
            System.out.println("Connection error");
            throw new RuntimeException(e);
        }
    }
}
