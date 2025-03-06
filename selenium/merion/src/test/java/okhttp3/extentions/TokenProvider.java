package okhttp3.extentions;

import okhttp.clients.XClientsWebClient;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.io.IOException;

public class TokenProvider implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().isAnnotationPresent(Token.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        String login = parameterContext.getParameter().getAnnotation(Token.class).login();
        String password = parameterContext.getParameter().getAnnotation(Token.class).password();
        try {
            Object client = extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).get("x_client");
            return ((XClientsWebClient)client).getToken(login, password);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
