package okhttp.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp.clients.model.AuthRequest;
import okhttp.clients.model.AuthResponse;
import okhttp.logger.MyCustomLogger;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;

public class AuthService {
    private static final MediaType JSON = MediaType.get("application/json");
    private static final String LOGIN = "/auth/login";
    private final String URL;
    private final OkHttpClient client;
    private final ObjectMapper mapper;

    public AuthService(String url) {
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
}
