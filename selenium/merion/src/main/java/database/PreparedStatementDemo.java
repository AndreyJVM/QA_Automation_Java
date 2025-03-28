package database;

import java.sql.*;
import java.util.Scanner;

public class PreparedStatementDemo {
    public static final String CONNECTION_STRING = "jdbc:postgresql://51.250.26.13/pg-x-clients-be";
    public static final String USERNAME = "merionpg";
    public static final String PASSWORD = "UZObS42{8>}>";

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);

        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        String insertSQL = "insert into company(\"name\") values(?);";
        String selectSQL = "SELECT * FROM company ORDER BY id DESC LIMIT ?";

        PreparedStatement statement = connection.prepareStatement(insertSQL);
        statement.setString(1, name);
        statement.executeUpdate();

        int limit = scanner.nextInt();
        PreparedStatement statementSelect = connection.prepareStatement(selectSQL);
        statementSelect.setInt(1, limit);

        ResultSet resultSet = statementSelect.executeQuery();

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
