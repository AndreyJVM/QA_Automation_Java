package api.assured.contract;

import com.atlassian.oai.validator.restassured.OpenApiValidationFilter;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import okhttp.clients.model.Company;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

// Swagger UI -> https://x-clients-be.onrender.com/docs
public class XClientCompanyTest {

    private static final String URI = "https://x-clients-be.onrender.com";
    private static final String SWAGGER_SCHEMA = "https://x-clients-be.onrender.com/docs-json";
    private static final String COMPANY_ENDPOINT = "/company";
    private static final String DELETE_ENDPOINT = "/company/delete/{id}";
    private static final String LOGIN_ENDPOINT = "/auth/login";
    private static final String X_CLIENT_TOKEN = "x-client-token";

    private static ResponseSpecification RESPONSE_401;

    private static RequestSpecification CREATE_REQUEST;
    private static RequestSpecification DELETE_REQUEST;

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = URI;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RESPONSE_401 = new ResponseSpecBuilder()
                .expectStatusCode(401)
                .expectBody("statusCode", equalTo(401))
                .expectBody("message", equalTo("Unauthorized"))
                .build();

        String json = """
                {
                  "name": "ping",
                  "description": "pong"
                }
                """;

        CREATE_REQUEST = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBody(json)
                .addHeader(X_CLIENT_TOKEN, getToken())
                .build();

        DELETE_REQUEST = new RequestSpecBuilder()
                .addHeader(X_CLIENT_TOKEN, getToken())
                .setBasePath(DELETE_ENDPOINT)
                .build();

        OpenApiValidationFilter filter = new OpenApiValidationFilter(SWAGGER_SCHEMA);
        RestAssured.filters(filter);
    }

    @Test
    public void shouldReturnArrayOnGetCompanyList() {
        List<Company> list = given()
                .get(COMPANY_ENDPOINT)
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("", Company.class);

        Assertions.assertFalse(list.isEmpty());
    }

    @Test
    public void shouldReturn401WithoutValidToken() {

        given()
                .spec(CREATE_REQUEST)
                .contentType(ContentType.JSON)
                .header(X_CLIENT_TOKEN, "NON_VALID_TOKEN")
                .post(COMPANY_ENDPOINT)
                .then()
                .spec(RESPONSE_401);
    }

    @Test
    public void shouldReturn201WithValidToken() {

        given()
                .spec(CREATE_REQUEST)
                .post(COMPANY_ENDPOINT)
                .then()
                .statusCode(201)
                .body("id", greaterThanOrEqualTo(0));
    }

    @Test
    public void shouldDeleteCompany() {
        int id = createDummyCompany();

        given()
                .header(X_CLIENT_TOKEN, getToken())
                .basePath(COMPANY_ENDPOINT)
                .get("/delete/{id}", id)
                .then()
                .statusCode(200)
                .body("id", equalTo(id));
    }

    @Test
    public void shouldReturn401OnDeleteCompany() {
        int id = createDummyCompany();

        given()
                .spec(DELETE_REQUEST)
                .header(X_CLIENT_TOKEN, "")
                .get("", id)
                .then()
                .spec(RESPONSE_401);
    }

    @Test
    @Tag("defect")
    public void shouldGet404DeleteNonExistedCompany() {
        int id = createDummyCompany();

        given()
                .spec(DELETE_REQUEST)
                .get("", id)
                .andReturn();

        given()
                .spec(DELETE_REQUEST)
                .get("", id)
                .then()
                .statusCode(404);
    }

    private static String getToken() {
        String jsonAuth = """
                {
                  "username": "leonardo",
                  "password": "leads"
                }
                """;

        return given()
                .body(jsonAuth)
                .contentType(ContentType.JSON)
                .post(LOGIN_ENDPOINT)
                .then()
                .extract()
                .path("userToken");
    }

    private int createDummyCompany() {

        String json = """
                {
                  "name": "ping",
                  "description": "pong"
                }
                """;

        return given().
                body(json)
                .contentType(ContentType.JSON)
                .header(X_CLIENT_TOKEN, getToken())
                .post(COMPANY_ENDPOINT)
                .then()
                .extract().path("id");
    }
}