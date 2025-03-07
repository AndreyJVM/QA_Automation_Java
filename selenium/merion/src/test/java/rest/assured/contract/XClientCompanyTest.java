package rest.assured.contract;


import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

// Swagger UI -> https://x-clients-be.onrender.com/docs
public class XClientCompanyTest {

    public static final String URI = "https://x-clients-be.onrender.com";
    public static final String COMPANY_ENDPOINT = "/company";
    public static final String LOGIN_ENDPOINT = "/auth/login";
    public static final String X_CLIENT_TOKEN = "x-client-token";

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = URI;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void shouldReturnArrayOnGetCompanyList() {
        given()
                .get(COMPANY_ENDPOINT)
                .then()
                .statusCode(200);
    }

}
