package rest.assured;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.notNullValue;

// swagger UI -> https://x-clients-be.onrender.com/docs
public class XClientsDemo {

    private static XClientsWebClientRestAssured xClientsWebClientRestAssured;

    public static void main(String[] args) {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        xClientsWebClientRestAssured = new XClientsWebClientRestAssured("https://x-clients-be.onrender.com");

        String token = xClientsWebClientRestAssured.getToken("leonardo", "leads");
        Response response = xClientsWebClientRestAssured.create("Demo Delete this company", "fake company", token);
        response.then().body("id", notNullValue());
    }
}