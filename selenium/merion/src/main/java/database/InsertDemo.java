package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertDemo {

        public static final String CONNECTION_STRING = "jdbc:postgresql://51.250.26.13/pg-x-clients-be";
        public static final String USERNAME = "merionpg";
        public static final String PASSWORD = "UZObS42{8>}>";

        public static void main(String[] args) throws SQLException {
            Connection connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);

            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();

            String insert = "insert into company(\"name\") values('" + name +"');";
            connection.createStatement().executeUpdate(insert);

            int limit = scanner.nextInt();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM company ORDER BY id DESC LIMIT " + limit);

            while (resultSet.next()) {
                System.out.println(resultSet.getString("id"));
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("description"));
                System.out.println(resultSet.getString("is_active"));
            }

            connection.close();

            // SQL Injection --> abc'); delete from company; --

            connection.close();
        }
    }