package rest.assured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class XClientsWebClientRestAssured {

    public XClientsWebClientRestAssured(String url) {
        RestAssured.baseURI = url;
        RestAssured.basePath = "/company";
    }

    public String getToken(String login, String password) {
        String authJson = "{ \"username\": \"" + login + "\",\"password\": \"" + password + "\"}";

        return given().contentType(ContentType.JSON)
                .body(authJson)
                .basePath("/auth/login")
                .post()
                .then()
                .extract()
                .path("userToken");
    }

    public Response create(String name, String description, String token) {
        String json = "{ \"name\": \"" + name + "\",\"description\": \"" + description + "\"}";
        return given()
                .contentType(ContentType.JSON)
                .body(json)
                .header("x-client-token", token)
                .post();
    }
}