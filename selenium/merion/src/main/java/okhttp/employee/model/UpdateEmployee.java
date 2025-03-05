package okhttp.employee.model;

public record UpdateEmployee(String lastName,
                             String email,
                             String url,
                             String phone,
                             boolean isActive) {
}
