package sql;

import api.extentions.ClientProviderRestAssured;
import api.extentions.Token;
import api.extentions.TokenProvider;
import okhttp.clients.model.Company;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import rest.assured.XClientsWebClientRestAssured;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({ClientProviderRestAssured.class, TokenProvider.class})
public class XClientsBusinessTest {

    private int companyId;

    public static final String CONNECTION_STRING = "jdbc:postgresql://51.250.26.13/pg-x-clients-be";
    public static final String USERNAME = "merionpg";
    public static final String PASSWORD = "UZObS42{8>}>";

    private static Connection connection;

    @BeforeAll
    public static void createConnection() throws SQLException {
        connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        if(connection != null) {
            connection.close();
        }
    }

    @AfterEach
    public void tearDown(XClientsWebClientRestAssured client, @Token(login = "leonardo", password = "leads") String token) {
        client.deleteById(companyId, token);
    }

    @Test
    public void shouldCreateCompany(XClientsWebClientRestAssured xClient, @Token(login = "leonardo", password = "leads") String token) {
        int sizeBefore = xClient.getAll().size();

        companyId = xClient.create("DeleteMe", "", token);

        int sizeAfter = xClient.getAll().size();
        assertEquals(sizeBefore + 1, sizeAfter);
    }

    @Test
    public void shouldSetDefaultValues(XClientsWebClientRestAssured xClient, @Token(login = "leonardo", password = "leads") String token) {
        String companyName = "DeleteMe";
        companyId = xClient.create(companyName, "", token);

        Company company = xClient.getById(companyId);

        assertEquals(companyId, company.id());
        assertTrue(company.isActive());
        assertTrue(company.description().isBlank());
        assertEquals(companyName, company.name());
    }

    @Test
    public void shouldSaveNameAndDescValues(XClientsWebClientRestAssured xClient, @Token(login = "leonardo", password = "leads") String token) {
        String companyName = "DeleteMe";
        String desc = "please";

        companyId = xClient.create(companyName, desc, token);

        Company company = xClient.getById(companyId);

        assertEquals(companyId, company.id());
        assertTrue(company.isActive());
        assertEquals(desc, company.description());
        assertEquals(companyName, company.name());
    }

    @Test
    public void shouldDeleteCompany(
            XClientsWebClientRestAssured client,
            @Token(login = "leonardo", password = "leads") String token
    ) {
        int id = client.create("A", "B", token);
        Company deletedInfo = client.deleteById(id, token);
        assertEquals(id, deletedInfo.id());
    }
}