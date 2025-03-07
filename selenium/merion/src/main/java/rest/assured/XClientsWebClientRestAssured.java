package rest.assured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import okhttp.clients.model.Company;

import java.util.List;

import static io.restassured.RestAssured.given;

public class XClientsWebClientRestAssured {

    public XClientsWebClientRestAssured(String url) {
        RestAssured.baseURI = url;
        RestAssured.basePath = "/company";
    }

    public String getToken(String login, String pass) {
        String authJson = "{ \"username\": \"" + login + "\", \"password\": \"" + pass + "\"}";
        return given().contentType(ContentType.JSON)
                .body(authJson)
                .basePath("/auth/login")
                .post()
                .then()
                .extract()
                .path("userToken");
    }

    public int create(String name, String description, String token) {
        String json = "{\"name\": \"" + name + "\", \"description\": \"" + description + "\"}";
        return given()
                .contentType(ContentType.JSON)
                .body(json)
                .header("x-client-token", token)
                .post()
                .jsonPath().getInt("id");
    }

    public Company getById(int companyId) {
        return given().pathParams("id", companyId)
                .get("/{id}")
                .then()
                .extract()
                .as(Company.class);
    }

    public List<Company> getAll() {
        return given().get()
                .jsonPath().getList("", Company.class);
    }

    public Company deleteById(int companyId, String token) {
        ValidatableResponse resp = given()
                .pathParams("id", companyId)
                .header("x-client-token", token)
                .get("/delete/{id}")
                .then();

        return  resp.extract().as(Company.class);
    }
}