package api.assured.business;

import api.extentions.ClientProviderRestAssured;
import api.extentions.Token;
import api.extentions.TokenProvider;
import okhttp.clients.model.Company;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import rest.assured.XClientsWebClientRestAssured;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({ClientProviderRestAssured.class, TokenProvider.class})
public class XClientsBusinessTest {
    private int companyId;

    @AfterEach
    public void tearDown(XClientsWebClientRestAssured client, @Token(login = "leonardo", password = "leads") String token) {
        client.deleteById(companyId, token);
    }

    @Test
    public void shouldCreateCompany(XClientsWebClientRestAssured xClient, @Token(login = "leonardo", password = "leads") String token) {
        // посмотреть, сколько было ДО
        int sizeBefore = xClient.getAll().size();

        // изменить
        companyId = xClient.create("DeleteMe", "", token);

        // посмотреть, сколько стало ПОСЛЕ
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
