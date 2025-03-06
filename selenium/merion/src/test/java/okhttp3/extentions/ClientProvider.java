package okhttp3.extentions;

import okhttp.clients.XClientsWebClient;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class ClientProvider implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(XClientsWebClient.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        XClientsWebClient client = new XClientsWebClient("https://x-clients-be.onrender.com");
        extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).put("x_client", client);
        return client;
    }
}
