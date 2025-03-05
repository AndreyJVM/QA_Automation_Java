package okhttp.clients;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class GetCompaniesXClientsRequest {

    public static final String URL = "https://x-clients-be.onrender.com";
    public static final String COMPANY = "/company";
    public static final String LOGIN = "/auth/login";

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request getAllCompanies = new Request.Builder().url(URL + COMPANY).build();

        Response response = client.newCall(getAllCompanies).execute();

        System.out.println(response.body().string());
    }
}
