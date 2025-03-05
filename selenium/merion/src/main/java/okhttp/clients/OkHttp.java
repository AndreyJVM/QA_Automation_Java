package okhttp.clients;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OkHttp {

    public static final String URL = "https://sky-todo-list.herokuapp.com/";

    public static void main(String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request getAllTasks = new Request
                .Builder()
                .url(URL)
                .build();

        Response execute = client.newCall(null)
                .execute();

        System.out.print(execute);
    }
}
