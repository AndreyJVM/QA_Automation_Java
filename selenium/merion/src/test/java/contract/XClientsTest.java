package contract;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class XClientsTest {

    public static final String URL = "https://x-clients-be.onrender.com/company";

    @Test
    public void shouldReturnArrayOnGetCompanyList () throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(URL).build();

        Response response = client.newCall(request).execute();

        assertEquals(200, response.code());
    }
}
