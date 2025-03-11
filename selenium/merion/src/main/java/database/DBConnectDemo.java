package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnectDemo {

    public static final String CONNECTION_STRING = "jdbc:postgresql://dpg-cn1542en7f5s73fdrigg-a.frankfurt-postgres.render.com/x_clients_xxet";
    public static final String USERNAME = "x_clients_user";
    public static final String PASSWORD = "x7ngHjC1h08a85bELNifgKmqZa8KIR40";

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);

        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM company");

        resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM company");
        resultSet.next();
        System.out.println(resultSet.getInt(1));


        String insert = "insert into company(\"name\") values('Рога и копыта');";

        connection.createStatement().executeUpdate(insert);

        resultSet = connection.createStatement().executeQuery("select * from company order by id desc limit 10");

        while (resultSet.next()) {
            System.out.println(resultSet.getString("id"));
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getString("description"));
            System.out.println(resultSet.getString("is_active"));
        }

        connection.close();
    }
}
