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
    private static final MediaType JSON = MediaType.get("application/json");
    private static final String LOGIN = "/auth/login";
    private static final String COMPANY = "company";
    private final String URL;
    private final OkHttpClient client;
    private final ObjectMapper mapper;

    public XClientsWebClient(String url) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new MyCustomLogger());
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        mapper = new ObjectMapper();
        client = new OkHttpClient.Builder().addNetworkInterceptor(interceptor).build();

        this.URL = url;
    }

    public String getToken(String login, String pass) throws IOException {
        return auth(login, pass).userToken();
    }

    public AuthResponse auth(String login, String pass) throws IOException {
        AuthRequest authRequest = new AuthRequest(login, pass);
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

        HttpUrl url = HttpUrl.parse(URL).newBuilder().addPathSegment(COMPANY).build();

        Request request = new Request.Builder()
                .post(requestBody)
                .header("x-client-token", token)
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String jsonResponse = response.body().string();
        CreateNewCompanyResponse r = mapper.readValue(jsonResponse, CreateNewCompanyResponse.class);

        return r.id();
    }

    public List<Company> getAll() throws IOException {
        return this.getAll(null);
    }

    public List<Company> getAll(Boolean isActive) throws IOException {
        HttpUrl.Builder url = HttpUrl.parse(URL).newBuilder();
        if (isActive != null) {
            url.addQueryParameter("active", isActive.toString());
        }
        url.addPathSegment(COMPANY);

        HttpUrl build = url.build();
        Request getAllCompanies = new Request.Builder()
                .url(build)
                .build();
        Response response = client.newCall(getAllCompanies).execute();

        // return mapper.readValue(response.body().string(), new TypeReference<>() {});

        CollectionType listOfCompanies = mapper.getTypeFactory().constructCollectionType(List.class, Company.class);
        return mapper.readValue(response.body().string(), listOfCompanies);
    }


    public Company getById(int id) throws IOException {
        HttpUrl.Builder b = HttpUrl.parse(URL).newBuilder();
        b.addPathSegment(COMPANY).addPathSegment(String.valueOf(id));
        HttpUrl url = b.build();

        Request getCompany = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(getCompany).execute();

        return mapper.readValue(response.body().string(), Company.class);
    }

    public Company deleteById(int id, String token) throws IOException {
        HttpUrl.Builder b = HttpUrl.parse(URL).newBuilder();
        b.addPathSegment(COMPANY).addPathSegment("delete").addPathSegment(String.valueOf(id));
        HttpUrl url = b.build();

        Request deleteComapny = new Request.Builder()
                .url(url)
                .header("x-client-token", token)
                .build();
        Response response = client.newCall(deleteComapny).execute();

        return mapper.readValue(response.body().string(), Company.class);
    }

    public Company setActive(int id, boolean isActive, String token) throws IOException {
        HttpUrl.Builder b = HttpUrl.parse(URL).newBuilder();
        b.addPathSegment(COMPANY).addPathSegment("status").addPathSegment(String.valueOf(id));
        HttpUrl url = b.build();

        String json = "{\"isActive\": " + isActive + "}";
        RequestBody requestBody = RequestBody.create(json, JSON);
        Request setActive = new Request.Builder()
                .url(url)
                .patch(requestBody)
                .header("x-client-token", token)
                .build();

        Response response = client.newCall(setActive).execute();
        return mapper.readValue(response.body().string(), Company.class);
    }

    public Company updateCompany(int id, String name, String description, String token) throws IOException {
        HttpUrl.Builder b = HttpUrl.parse(URL).newBuilder();
        b.addPathSegment(COMPANY).addPathSegment(String.valueOf(id));
        HttpUrl url = b.build();

        CreateNewCompanyRequest createNewCompanyRequest = new CreateNewCompanyRequest(name, description);
        String jsonRequest = mapper.writeValueAsString(createNewCompanyRequest);
        RequestBody requestBody = RequestBody.create(jsonRequest, JSON);

        Request request = new Request.Builder()
                .patch(requestBody)
                .header("x-client-token", token)
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return mapper.readValue(response.body().string(), Company.class);
    }
}
