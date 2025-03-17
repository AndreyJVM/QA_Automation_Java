package stepik;

import org.testng.annotations.Test;
import stepik.data.provider.MyDataProvider;
import stepik.properties.Book;
import stepik.properties.BookStoreTestCase;

public class CreateBookNegativeTest extends BookStoreTestCase {

    @Test(dataProvider = "createBooksNegative", dataProviderClass = MyDataProvider.class)
    public void testCreateBookNegative(Book book) {
        testClient.postBook(book)
                .checkStatusCode(400);
    }
}