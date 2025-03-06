package okhttp3.employee;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp.logger.MyCustomLogger;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Iterator;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTests {
    private static final MediaType JSON = MediaType.get("application/json");
    private static final String LOGIN = "/auth/login";
    private static final String COMPANY = "company";
    private static final String EMPLOYEE = "employee";
    private static final String X_CLIENT_TOKEN = "x-client-token";
    private final String URL = "https://x-clients-be.onrender.com";
    private ObjectMapper mapper;
    private OkHttpClient client;

    @BeforeEach
    public void setUp() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new MyCustomLogger()).setLevel(BODY);

        mapper = new ObjectMapper();
        client = new OkHttpClient.Builder().addNetworkInterceptor(interceptor).build();
    }

    @Test
    @DisplayName("Можно создать сотрудника в компании")
    public void iCanCreateAnEmployee() throws IOException {
        // Создать компанию
        int newId = createNewCompany();

        // Создать сотрудника
        HttpUrl url = HttpUrl.parse(URL).newBuilder()
                .addPathSegment(EMPLOYEE)
                .build();

        String json = " { \"firstName\": \"Иван\", \"lastName\": \"Иванов\", \"companyId\": " + newId + ", \"phone\": \"+7923432423\"}";
        RequestBody reqBody = RequestBody.create(json, JSON);
        Request createRequest = new Request.Builder()
                .url(url)
                .header(X_CLIENT_TOKEN, getToken())
                .post(reqBody).build();

        Response response = client.newCall(createRequest).execute();
        JsonNode jsonNode = mapper.readTree(response.body().string());

        assertEquals(201, response.code());
        assertTrue(jsonNode.get("id").asInt() > 0);
    }

    @Test
    @DisplayName("Сотрудник отображается в списке сотрудников компании")
    public void iCanFindEmployeeInfo() throws IOException {
        int newId = createNewCompany();

        int firstEmpId = createNewEmployee(newId);
        int secondEmpId = createNewEmployee(newId);

        HttpUrl url = HttpUrl.parse(URL).newBuilder()
                .addPathSegment(EMPLOYEE)
                .addQueryParameter("company", String.valueOf(newId))
                .build();

        Request getListRequest = new Request.Builder()
                .url(url)
                .get().build();

        Response response = client.newCall(getListRequest).execute();
        JsonNode jsonNode = mapper.readTree(response.body().string()); // [ {}, {} ]
        Iterator<JsonNode> elements = jsonNode.elements();// {}, {}
        JsonNode emp1 = elements.next();
        JsonNode emp2 = elements.next();

        assertFalse(elements.hasNext());
        assertEquals(firstEmpId, emp1.get("id").asInt());
        assertEquals(secondEmpId, emp2.get("id").asInt());
        assertEquals(200, response.code());
    }

    @Test
    @DisplayName("Сотруднику можно изменить статус активности")
    public void iCanDeactiveteEmp() throws IOException {
        int companyId = createNewCompany();
        int empId = createNewEmployee(companyId);

        HttpUrl url = HttpUrl.parse(URL).newBuilder()
                .addPathSegment(EMPLOYEE)
                .addPathSegment(String.valueOf(empId))
                .build();

        String json = "{\"isActive\": false}";
        RequestBody reqBody = RequestBody.create(json, JSON);

        Request patchRequest = new Request.Builder()
                .url(url)
                .addHeader(X_CLIENT_TOKEN, getToken())
                .patch(reqBody).build();

        Response response = client.newCall(patchRequest).execute();
        assertEquals(200, response.code());

        JsonNode empInfo = getEmpInfo(empId);
        assertFalse(empInfo.get("isActive").asBoolean());
    }

    @Test
    @DisplayName("Сотруднику можно изменить email")
    public void iCanChangeEmail() throws IOException {
        int companyId = createNewCompany();
        int empId = createNewEmployee(companyId);

        HttpUrl url = HttpUrl.parse(URL).newBuilder()
                .addPathSegment(EMPLOYEE)
                .addPathSegment(String.valueOf(empId))
                .build();

        String json = "{\"email\": \"mail@mail.ru\"}";
        RequestBody reqBody = RequestBody.create(json, JSON);

        Request patchRequest = new Request.Builder()
                .url(url)
                .addHeader(X_CLIENT_TOKEN, getToken())
                .patch(reqBody).build();

        Response response = client.newCall(patchRequest).execute();
        assertEquals(200, response.code());

        JsonNode empInfo = getEmpInfo(empId);
        assertEquals("mail@mail.ru", empInfo.get("email").asText());
    }

    @Test
    @DisplayName("[BUG]. Сотруднику можно изменить email и телефон")
    public void iCanChangeEmailAndPhone() throws IOException {
        int companyId = createNewCompany();
        int empId = createNewEmployee(companyId);

        HttpUrl url = HttpUrl.parse(URL).newBuilder()
                .addPathSegment(EMPLOYEE)
                .addPathSegment(String.valueOf(empId))
                .build();

        String json = "{\"email\": \"mail@mail.ru\", \"phone\":\"+7765432456432\"}";
        RequestBody reqBody = RequestBody.create(json, JSON);

        Request patchRequest = new Request.Builder()
                .url(url)
                .addHeader(X_CLIENT_TOKEN, getToken())
                .patch(reqBody).build();

        Response response = client.newCall(patchRequest).execute();
        assertEquals(200, response.code());

        JsonNode empInfo = getEmpInfo(empId);
        assertEquals("mail@mail.ru", empInfo.get("email").asText());
        assertEquals("+7765432456432", empInfo.get("phone").asText());
    }

    @Test
    @DisplayName("Нельзя создать сотрудника для несуществующей компании")
    public void iCantCreateEmployeeForNullCompany() throws IOException {
        // Создать сотрудника
        HttpUrl url = HttpUrl.parse(URL).newBuilder()
                .addPathSegment(EMPLOYEE)
                .build();

        String json = " { \"firstName\": \"Иван\", \"lastName\": \"Иванов\", \"companyId\": " + Integer.MAX_VALUE + ", \"phone\": \"+7923432423\"}";
        RequestBody reqBody = RequestBody.create(json, JSON);
        Request createRequest = new Request.Builder()
                .url(url)
                .header(X_CLIENT_TOKEN, getToken())
                .post(reqBody).build();

        Response response = client.newCall(createRequest).execute();
        JsonNode jsonNode = mapper.readTree(response.body().string());

        assertEquals(500, response.code());
        assertEquals(500, jsonNode.get("statusCode").asInt());
        assertEquals("Internal server error", jsonNode.get("message").asText());

        url = HttpUrl.parse(URL).newBuilder()
                .addPathSegment(EMPLOYEE)
                .addQueryParameter("company", String.valueOf(Integer.MAX_VALUE))
                .build();

        Request getListRequest = new Request.Builder()
                .url(url)
                .get().build();

        response = client.newCall(getListRequest).execute();
        jsonNode = mapper.readTree(response.body().string());
        assertFalse(jsonNode.elements().hasNext());
    }

    private String getToken() throws IOException {
        String json = """
                {
                  "username": "leonardo",
                  "password": "leads"
                }
                """;

        RequestBody authBody = RequestBody.create(json, JSON);
        Request request = new Request.Builder().post(authBody).url(URL + LOGIN).build();
        Response response = client.newCall(request).execute();

        String body = response.body().string();

        JsonNode jsonNode = mapper.readTree(body);
        return jsonNode.get("userToken").asText();
    }

    private int createNewCompany() throws IOException {

        // Создать сотрудника
        HttpUrl url = HttpUrl.parse(URL).newBuilder()
                .addPathSegment(COMPANY)
                .build();

        String json = """
                {
                  "name": "Contract Test Company",
                  "description": "string"
                }
                """;

        RequestBody reqBody = RequestBody.create(json, JSON);
        Request createRequest = new Request.Builder()
                .url(url)
                .header(X_CLIENT_TOKEN, getToken())
                .post(reqBody).build();

        Response response = client.newCall(createRequest).execute();

        JsonNode jsonNode = mapper.readTree(response.body().string());

        // Получить id компании
        return jsonNode.get("id").asInt();
    }

    private int createNewEmployee(int companyId) throws IOException {
        // Создать сотрудника
        HttpUrl url = HttpUrl.parse(URL).newBuilder()
                .addPathSegment(EMPLOYEE)
                .build();

        String json = " { \"firstName\": \"Иван\", \"lastName\": \"Иванов\", \"companyId\": " + companyId + ", \"phone\": \"+7923432423\"}";
        RequestBody reqBody = RequestBody.create(json, JSON);
        Request createRequest = new Request.Builder()
                .url(url)
                .header(X_CLIENT_TOKEN, getToken())
                .post(reqBody).build();

        Response response = client.newCall(createRequest).execute();
        JsonNode jsonNode = mapper.readTree(response.body().string());
        return jsonNode.get("id").asInt();
    }

    private JsonNode getEmpInfo(int empId) throws IOException {
        HttpUrl url = HttpUrl.parse(URL).newBuilder()
                .addPathSegment(EMPLOYEE)
                .addPathSegment(String.valueOf(empId))
                .build();

        Request getRequest = new Request.Builder()
                .url(url)
                .get().build();

        Response response = client.newCall(getRequest).execute();
        return mapper.readTree(response.body().string());
    }
}
