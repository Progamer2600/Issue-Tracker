package me.echols.issuetracker.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnect {
    Connection conn = null;
    public static Connection connectdb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306",
                    "root", "root");
            System.out.println("Database Connected");
            return conn;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
