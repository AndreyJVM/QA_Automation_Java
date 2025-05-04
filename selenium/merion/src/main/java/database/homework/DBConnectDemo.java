package database.homework;

import sql.EmployeeTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnectDemo {

    public static final String CONNECTION_STRING = "jdbc:postgresql://51.250.26.13/pg-x-clients-be";
    public static final String USERNAME = "merionpg";
    public static final String PASSWORD = "UZObS42{8>}>";

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);

        EmployeeTable table = new EmployeeTable(connection);

        ResultSet list = table.getAll();
        while (list.next()) {
            printInfo(list);
        }

        int empId = table.add("Merion", "Academy", "+733322231", 123);

        ResultSet emp = table.getById(empId);
        emp.next();
        printInfo(emp);

        table.deleteById(empId);

        connection.close();
    }

    private static void printInfo(ResultSet list) throws SQLException {
        System.out.println(list.getInt("id") + " " + list.getString("first_name") + " " + list.getString("last_name"));
    }
}
