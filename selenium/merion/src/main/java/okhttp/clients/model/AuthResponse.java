package okhttp.clients.model;

public record AuthResponse(String userToken, String role, String displayName, String login) {
}
