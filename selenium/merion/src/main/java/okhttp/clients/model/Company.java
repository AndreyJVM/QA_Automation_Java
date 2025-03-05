package okhttp.clients.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Company(int id, String name, boolean isActive) {
}
