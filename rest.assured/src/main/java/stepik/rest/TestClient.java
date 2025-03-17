package stepik.rest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import stepik.properties.Book;
import stepik.properties.BookValidateResponse;
import stepik.properties.enam.TestConfig;

import static io.restassured.RestAssured.given;

@AllArgsConstructor
public class TestClient {

    private final String baseURI;
    private final String basePath;

    public TestClient() {
        this(TestConfig.BaseURI.value, TestConfig.BasePath.value);
    }

    public RequestSpecification getRequestSpecification() {
        return given().baseUri(baseURI)
                .basePath(basePath)
                .contentType(ContentType.JSON)
                .log().all();
    }

    public RequestSpecification getRequestSpecification(Object book) {
        return getRequestSpecification()
                .body(book);
    }

    public BookValidateResponse postBook(Book book) {
        Response response = getRequestSpecification(book).when()
                .post("/books");

        response.then().log().all();

        return new BookValidateResponse(response);
    }

    public BookValidateResponse getBook(Integer id) {
        Response response = getRequestSpecification().when()
                .get("/books/{id}", id);

        response.then().log().all();

        return new BookValidateResponse(response);
    }

    public BookValidateResponse putBook(Integer id, Book book) {
        Response response = getRequestSpecification(book)
                .when()
                .put("/books/{id}", id);

        response.then()
                .log().all();

        return new BookValidateResponse(response);
    }

    public BookValidateResponse deleteBook(Integer id) {
        Response response = getRequestSpecification()
                .when()
                .delete("/books/{id}", id);

        response.then()
                .log().all();

        return new BookValidateResponse(response);
    }
}