package okhttp.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import okhttp.employee.model.CreateEmployee;
import okhttp.employee.model.CreateNewCompanyResponse;
import okhttp.employee.model.Employee;
import okhttp.employee.model.UpdateEmployee;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.util.List;

public class EmployeeService {
    private static final MediaType JSON = MediaType.get("application/json");
    private static final String COMPANY = "company";
    private static final String EMPLOYEE = "employee";
    public final CollectionType listOfEmpsType;
    private final String URL;
    private final OkHttpClient client;
    private final ObjectMapper mapper;
    private final String TOKEN_VALUE;
    private static final String TOKEN_KEY = "x-client-token";

    public EmployeeService(String url, String login, String pass) throws IOException {
        this.URL = url;

        mapper = new ObjectMapper();
        listOfEmpsType = mapper.getTypeFactory().constructCollectionType(List.class, Employee.class);

        client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        TOKEN_VALUE = new AuthService(url).getToken(login, pass);
    }

    // PATCH https://x-clients-be.onrender.com/employee/1212
    public Employee update(int id, UpdateEmployee update) throws IOException {
        String jsonRequest = mapper.writeValueAsString(update);
        RequestBody requestBody = RequestBody.create(jsonRequest, JSON);

        HttpUrl url = HttpUrl.parse(URL)
                .newBuilder()
                .addPathSegment(EMPLOYEE)
                .addPathSegment("" + id)
                .build();

        Request request = new Request.Builder()
                .patch(requestBody)
                .header(TOKEN_KEY, TOKEN_VALUE)
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return mapper.readValue(response.body().string(), Employee.class);
    }

    // GET https://x-clients-be.onrender.com/employee?company=2340
    public List<Employee> getByCompanyId(int companyId) throws IOException {
        HttpUrl url = HttpUrl.parse(URL)
                .newBuilder()
                .addPathSegment(EMPLOYEE)
                .addQueryParameter(COMPANY, Integer.toString(companyId))
                .build();

        Request getByIdReq = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(getByIdReq).execute();
        return mapper.readValue(response.body().string(), listOfEmpsType);
    }

    // GET https://x-clients-be.onrender.com/employee/1876
    public Employee getById(int id) throws IOException {
        HttpUrl url = HttpUrl.parse(URL)
                .newBuilder()
                .addPathSegment(EMPLOYEE)
                .addPathSegment("" + id)
                .build();

        Request getByIdReq = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(getByIdReq).execute();
        return mapper.readValue(response.body().string(), Employee.class);
    }

    // POST https://x-clients-be.onrender.com/employee
    public int create(CreateEmployee employee) throws IOException {
        String jsonRequest = mapper.writeValueAsString(employee);
        RequestBody requestBody = RequestBody.create(jsonRequest, JSON);

        HttpUrl url = HttpUrl.parse(URL).newBuilder().addPathSegment(EMPLOYEE).build();

        Request request = new Request.Builder()
                .post(requestBody)
                .header(TOKEN_KEY, TOKEN_VALUE)
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String jsonResponse = response.body().string();
        CreateNewCompanyResponse r = mapper.readValue(jsonResponse, CreateNewCompanyResponse.class);

        return r.id();
    }
}
