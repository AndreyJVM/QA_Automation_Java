package okhttp.logger;

import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;

public class MyCustomLogger implements HttpLoggingInterceptor.Logger {
    @Override
    public void log(@NotNull String s) {
        System.out.println(s);
    }
}
