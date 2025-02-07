package sql;

public record Employee(
        int id,
        String firstName,
        String lastName,
        boolean isActive,
        int companyId,
        String email,
        String phone
) {
}
