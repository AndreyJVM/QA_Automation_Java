package stepik.properties;

import stepik.rest.TestClient;

public class BookStoreTestCase {
    protected static TestClient testClient;

    static {
        testClient = new TestClient();
    }
}
