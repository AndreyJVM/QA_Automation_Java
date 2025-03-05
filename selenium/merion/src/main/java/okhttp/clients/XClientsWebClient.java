package okhttp.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import okhttp.clients.model.AuthRequest;
import okhttp.clients.model.AuthResponse;
import okhttp.clients.model.Company;
import okhttp.clients.model.CreateNewCompanyRequest;
import okhttp.clients.model.CreateNewCompanyResponse;
import okhttp.logger.MyCustomLogger;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.util.List;

public class XClientsWebClient {

    public static final String LOGIN = "/auth/login";
    private static final MediaType JSON = MediaType.get("application/json");
    public static final String COMPANY = "/company";
    private String URL;
    private final OkHttpClient client;
    private final ObjectMapper mapper;

    public XClientsWebClient(String url) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new MyCustomLogger());
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        mapper = new ObjectMapper();
        client = new OkHttpClient.Builder().addNetworkInterceptor(interceptor).build();

        this.URL = url;
    }

    public String getToken(String login, String password) throws IOException {
        return auth(login, password).userToken();
    }

    public AuthResponse auth(String login, String password) throws IOException {
        AuthRequest authRequest = new AuthRequest(login, password);
        String jsonRequest = mapper.writeValueAsString(authRequest);
        RequestBody requestBody = RequestBody.create(jsonRequest, JSON);
        Request authReq = new Request.Builder().post(requestBody).url(URL + LOGIN).build();
        Response authResp = client.newCall(authReq).execute();
        String jsonResp = authResp.body().string();
        return mapper.readValue(jsonResp, AuthResponse.class);
    }

    public int create(String name, String description, String token) throws IOException {
        CreateNewCompanyRequest createNewCompanyRequest = new CreateNewCompanyRequest(name, description);
        String jsonRequest = mapper.writeValueAsString(createNewCompanyRequest);
        RequestBody requestBody = RequestBody.create(jsonRequest, JSON);
        Request request = new Request.Builder()
                .post(requestBody)
                .header("x-client-token", token)
                .url(URL + COMPANY)
                .build();
        Response response = client.newCall(request).execute();
        String jsonResponse = response.body().string();
        CreateNewCompanyResponse id = mapper.readValue(jsonResponse, CreateNewCompanyResponse.class);
        return id.id();
    }

    public List<Company> getAll(Boolean isActive) throws IOException {
        HttpUrl.Builder url = HttpUrl.parse(URL).newBuilder();
        if (isActive != null) {
            url.addQueryParameter("active", isActive.toString());
        }
        url.addPathSegment(COMPANY);
        Request getAllCompanies = new Request.Builder()
                .url(URL + COMPANY)
                .build();
        Response response = client.newCall(getAllCompanies).execute();

        CollectionType listOfCompanies = mapper.getTypeFactory().constructCollectionType(List.class, Company.class);
        return mapper.readValue(response.body().string(), listOfCompanies);
    }

}
