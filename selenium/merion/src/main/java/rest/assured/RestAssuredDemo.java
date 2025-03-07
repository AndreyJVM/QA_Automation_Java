package rest.assured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.net.URI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredDemo {

    public static final String URI = "https://x-clients-be.onrender.com";

    public static void main(String[] args) {

        RestAssured.baseURI = URI;
        RestAssured.basePath = "/company";

        given().baseUri(URI)
                .when().get("")
                .then().statusCode(200)
                .contentType(ContentType.JSON);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .when().post(URI)
                .then().log().all()
                .statusCode(201)
                .header("Connection", "keep-alive")
                .body("title", equalTo("456"));

    }
}
