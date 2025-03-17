package stepik.properties;

import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;

import static org.testng.AssertJUnit.assertEquals;

public class BookValidateResponse {
    private BookResponse model;
    private Response response;

    @SneakyThrows
    public BookValidateResponse(Response response) {
        this.response = response;
        if (response.asString().length() > 0) {
            model = response.as(BookResponse.class);
        }
    }

    public BookValidateResponse checkStatusCode(int statusCode) {
        response.then().statusCode(statusCode);

        return this;
    }

    public BookValidateResponse checkIdNotNull() {
        response.then().body("id", Matchers.notNullValue());
        return this;
    }

    public BookValidateResponse checkLastUpdated() {
        response.then().body("lastUpdated", Matchers.notNullValue());
        return this;
    }

    public BookValidateResponse checkBook(Book executed) {
        assertEquals(new Book(model), executed);
        return this;
    }

    public BookValidateResponse checkId(Integer id) {
        response.then().body("id", Matchers.notNullValue());
        return this;
    }

    public Integer getId() {
        return response.jsonPath().getInt("id");
    }

    public BookValidateResponse checkErrorResponse(BookResponse expected) {
        response.then().body("timestamp", Matchers.notNullValue());
        assertEquals(model, expected);

        return this;
    }
}