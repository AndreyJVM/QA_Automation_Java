package api.okhttp3.business;

import api.extentions.ClientProvider;
import api.extentions.Token;
import api.extentions.TokenProvider;
import okhttp.clients.XClientsWebClient;
import okhttp.clients.model.Company;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static org.junit.Assert.*;

@ExtendWith({ClientProvider.class, TokenProvider.class})
public class XClientsBusinessTest {

    private int companyID;

    @Test
    public void shouldCreateCompany(XClientsWebClient xClient, @Token(login = "leonardo", password = "leads") String token) throws IOException {

        int sizeBefore = xClient.getAll().size();

        companyID = xClient.create("DeleteMe", " ", token);

        int sizeAfter = xClient.getAll().size();

        assertEquals(sizeBefore + 1, sizeAfter);
    }

    @Test
    public void shouldSetDefaultValues(XClientsWebClient xClient, @Token(login = "leonardo", password = "leads") String token) throws IOException {
        String companyName = "DeleteMe";

        companyID = xClient.create(companyName, "", token);

        Company company = xClient.getById(companyID);

        assertEquals(companyID, company.id());
        assertTrue(company.isActive());
        assertTrue(company.description().isBlank());
        assertEquals(companyName, company.name());
    }

    @Test
    public void shouldSaveNameAndDescriptionValues(XClientsWebClient xClient, @Token(login = "leonardo", password = "leads") String token) throws IOException {
        String companyName = "DeleteMe";
        String descriptionName = "please delete";

        companyID = xClient.create(companyName, descriptionName, token);

        Company company = xClient.getById(companyID);

        assertEquals(companyID, company.id());
        assertTrue(company.isActive());
        assertEquals(descriptionName, company.description());
        assertEquals(companyName, company.name());
    }

    @Test
    public void shouldDeleteCompany(XClientsWebClient xClient, @Token(login = "leonardo", password = "leads") String token) throws IOException {
       int id = xClient.create("Delete_A", "Delete_B", token);
        Company deletedInfo = xClient.deleteById(123, token);

        assertEquals(123, deletedInfo.id());
    }

    @AfterEach
    public void down(XClientsWebClient xClient, @Token(login = "leonardo", password = "leads") String token) throws IOException {
        xClient.deleteById(companyID, token);
    }
}
