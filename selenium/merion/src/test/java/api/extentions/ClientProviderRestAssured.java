package api.extentions;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import rest.assured.XClientsWebClientRestAssured;

public class ClientProviderRestAssured implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(XClientsWebClientRestAssured.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        XClientsWebClientRestAssured client = new XClientsWebClientRestAssured("https://x-clients-be.onrender.com");
        extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).put("x_client", client);
        return client;
    }
}
