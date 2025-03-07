package rest.assured;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class XClientsDemo {

    public static void main(String[] args) {
        RestAssured.baseURI = "https://x-clients-be.onrender.com";
        RestAssured.basePath = "/company";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        given().get().then().statusCode(200);
    }
}
