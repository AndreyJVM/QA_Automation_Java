package api.okhttp3.contract;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp.logger.MyCustomLogger;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// https://x-clients-be.onrender.com/docs
public class XClientsTest {

    public static final String URL = "https://x-clients-be.onrender.com/company";
    public static final String URL_LOGIN = "https://x-clients-be.onrender.com/auth/login";
    private OkHttpClient client;
    private final MediaType JSON = MediaType.get("application/json");
    ObjectMapper mapper;

    @BeforeEach
    public void setUp() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new MyCustomLogger());
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mapper = new ObjectMapper();
        client = new OkHttpClient.Builder().addNetworkInterceptor(interceptor).build();
    }

    @Test
    public void shouldReturnArrayOnGetCompanyList() throws IOException {
        Request request = new Request.Builder().url(URL).build();

        Response response = client.newCall(request).execute();

        String body = response.body().string();
        assertEquals(200, response.code());
        assertTrue(body.startsWith("["));
        assertTrue(body.endsWith("]"));
    }

    @Test
    public void shouldReturn401WithoutToken() throws IOException {

        String json = """
                {
                  "name": "ping",
                  "description": "pong"
                }
                """;

        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder().url(URL).post(body).build();

        Response response = client.newCall(request).execute();

        assertEquals(401, response.code());
        assertEquals("{\"statusCode\":401,\"message\":\"Unauthorized\"}", response.body().string());
    }

    @Test
    public void shouldReturn401WithoutValidToken() throws IOException {

        String json = """
                {
                  "name": "ping",
                  "description": "pong"
                }
                """;

        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(URL)
                .header("x-client-token", "NON_VALID_TOKEN")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        assertEquals(401, response.code());
        assertEquals("{\"statusCode\":401,\"message\":\"Unauthorized\"}", response.body().string());
    }

    @Test
    public void shouldReturn201CompanyCreated() throws IOException {

        String json = """
                {
                  "name": "ping",
                  "description": "pong"
                }
                """;


        RequestBody reqBody = RequestBody.create(json, JSON);
        Request createRequest = new Request.Builder()
                .url(URL)
                .header("x-client-token", getToken())
                .post(reqBody)
                .build();

        Response response = client.newCall(createRequest).execute();

        JsonNode jsonNode = mapper.readTree(response.body().string());

        int newID = createDummyCompany();

        assertEquals(201, response.code());
        assertTrue(newID > 0);
    }

    @Test
    public void shouldDeleteCompany() throws IOException {
        int id = createDummyCompany();
        Request request = new Request.Builder()
                .header("x-client-token", getToken())
                .url(URL + "/delete/" + id)
                .build();

        Response response = client.newCall(request).execute();
        String body = response.body().string();
        JsonNode node = mapper.readTree(body);

        assertEquals(200, response.code());
        assertEquals(id, node.get("id").asInt());
    }

    @Test
    public void shouldReturn401OnDeleteCompany() throws IOException{
        int id = createDummyCompany();
        Request request = new Request.Builder()
                .url(URL + "/delete/" + id)
                .build();

        Response response = client.newCall(request).execute();

        assertEquals(401, response.code());
        assertEquals("{\"statusCode\":401,\"message\":\"Unauthorized\"}", response.body().string());
    }

    @Test
    @Tag("defect")
    public void shouldGet404DeleteNonExistedCompany() throws IOException {
        int id = createDummyCompany();
        Request request = new Request.Builder()
                .header("x-client-token", getToken())
                .url(URL + "/delete/" + id)
                .build();

        client.newCall(request).execute();

        Response response = client.newCall(request).execute();

        assertTrue(response.body().string().isEmpty());
        assertEquals(404, response.code());
    }

    private String getToken() throws IOException {

        String json = """
                {
                  "username": "leonardo",
                  "password": "leads"
                }
                """;

        RequestBody authBody = RequestBody.create(json, JSON);
        Request request = new Request.Builder().post(authBody).url(URL_LOGIN).build();
        Response response = client.newCall(request).execute();

        String body = response.body().string();


        JsonNode jsonNode = mapper.readTree(body);
        return jsonNode.get("userToken").asText();
    }

    private int createDummyCompany() throws IOException {
        String json = """
                {
                  "name": "Well be deleted",
                  "description": "pong"
                }
                """;

        RequestBody reqBody = RequestBody.create(json, JSON);
        Request createRequest = new Request.Builder()
                .url(URL)
                .header("x-client-token", getToken())
                .post(reqBody)
                .build();

        Response response = client.newCall(createRequest).execute();
        JsonNode jsonNode = mapper.readTree(response.body().string());

        return jsonNode.get("id").asInt();
    }
}