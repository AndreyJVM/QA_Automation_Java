package stepik;

import org.testng.annotations.Test;
import stepik.properties.Book;
import stepik.properties.BookStoreTestCase;
import stepik.properties.BookValidateResponse;

public class DeleteBookTest extends BookStoreTestCase {

    @Test
    public void deleteBook() {
        BookValidateResponse response = testClient.postBook(Book.defaultOf())
                .checkStatusCode(201);

        testClient.deleteBook(response.getId())
                .checkStatusCode(200);


        testClient.getBook(response.getId())
                .checkStatusCode(404);

    }

    @Test
    public void deleteNotExistedBook() {
        testClient.deleteBook(999999999)
                .checkStatusCode(404);
    }
}
