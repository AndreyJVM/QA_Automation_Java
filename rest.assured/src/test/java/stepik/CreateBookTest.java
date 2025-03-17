package stepik;

import org.testng.annotations.Test;
import stepik.data.provider.MyDataProvider;
import stepik.properties.Book;
import stepik.properties.BookStoreTestCase;
import stepik.properties.BookValidateResponse;

public class CreateBookTest extends BookStoreTestCase {

    @Test(dataProvider = "dataProviderCreateBook", dataProviderClass = MyDataProvider.class)
    public void createBookTest(Book book) {

        BookValidateResponse response = testClient.postBook(book)
                        .checkStatusCode(201)
                        .checkIdNotNull()
                        .checkLastUpdated()
                        .checkBook(book);

        testClient.getBook(response.getId())
                .checkStatusCode(200)
                .checkId(response.getId())
                .checkLastUpdated()
                .checkBook(book);

    }
}