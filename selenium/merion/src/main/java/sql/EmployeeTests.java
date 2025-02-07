package sql;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EmployeeTests {
    public static final String CONNECTION_STRING = "jdbc:postgresql://dpg-cn1542en7f5s73fdrigg-a.frankfurt-postgres.render.com/x_clients_xxet";
    public static final String USERNAME = "x_clients_user";
    public static final String PASSWORD = "x7ngHjC1h08a85bELNifgKmqZa8KIR40";
    private static final String LOGIN = "/auth/login";
    private static final String COMPANY = "company";
    private static final String EMPLOYEE = "employee";
    private static final String X_CLIENT_TOKEN = "x-client-token";
    private static final String URL = "https://x-clients-be.onrender.com";
    private static EmployeeTable table;
    private static Connection connection;

    @BeforeAll
    public static void setAll() throws SQLException {
        RestAssured.baseURI = URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
        table = new EmployeeTable(connection);
    }

    @AfterAll
    public static void tearDown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    @DisplayName("Можно создать сотрудника в компании")
    public void iCanCreateAnEmployee() throws SQLException {
        int newId = createNewCompany();
        String json = " { \"firstName\": \"Иван\", \"lastName\": \"Иванов\", \"companyId\": " + newId + ", \"phone\": \"+7923432423\"}";

        int empId = given()
                .body(json)
                .header(X_CLIENT_TOKEN, getToken())
                .contentType(ContentType.JSON)
                .post(EMPLOYEE)
                .then()
                .statusCode(201)
                .extract().path("id");

        Employee e = table.getById(empId);
        assertEquals(empId, e.id());
        assertEquals(newId, e.companyId());
        assertEquals("Иван", e.firstName());
        assertEquals("Иванов", e.lastName());
    }

    @Test
    @DisplayName("Сотрудник отображается в списке сотрудников компании")
    public void iCanFindEmployeeInfo() throws SQLException {
        int newId = createNewCompany();

        int firstEmpId = table.add("Иван", "Иванов", "+78", newId);
        int secondEmpId = table.add("Иван", "Иванов", "+78", newId);

        given()
                .queryParam("company", newId)
                .get(EMPLOYEE)
                .then()
                .statusCode(200)
                .body("size()", is(2))
                .body("[0].id", is(firstEmpId))
                .body("[1].id", is(secondEmpId));
    }

    @Test
    @DisplayName("Сотруднику можно изменить статус активности")
    public void iCanDeactiveteEmp() throws SQLException {
        int companyId = createNewCompany();
        int empId = table.add("Иван", "Иванов", "+78", companyId);

        String json = "{\"isActive\": false}";
        given()
                .body(json)
                .header(X_CLIENT_TOKEN, getToken())
                .contentType(ContentType.JSON)
                .patch(EMPLOYEE + "/" + empId)
                .then()
                .statusCode(200);

        Employee e = table.getById(empId);
        assertFalse(e.isActive());
    }

    @Test
    @DisplayName("Сотруднику можно изменить email")
    public void iCanChangeEmail() throws SQLException {
        int companyId = createNewCompany();
        int empId = table.add("Иван", "Иванов", "+78", companyId);

        String json = "{\"email\": \"mail@mail.ru\"}";
        given().body(json).header(X_CLIENT_TOKEN, getToken()).contentType(ContentType.JSON).patch(EMPLOYEE + "/" + empId).then().statusCode(200);

        Employee e = table.getById(empId);
        assertEquals("mail@mail.ru", e.email());
    }

    @Test
    @DisplayName("[BUG]. Сотруднику можно изменить email и телефон")
    public void iCanChangeEmailAndPhone() throws SQLException {
        int companyId = createNewCompany();
        int empId = table.add("Иван", "Иванов", "+78", companyId);

        String json = "{\"email\": \"mail@mail.ru\", \"phone\":\"+7765432456432\"}";
        given().body(json).header(X_CLIENT_TOKEN, getToken()).contentType(ContentType.JSON).patch(EMPLOYEE + "/" + empId).then().statusCode(200);

        Employee e = table.getById(empId);
        assertEquals("mail@mail.ru", e.email());
        assertEquals("+7765432456432", e.phone());
    }

    @Test
    @DisplayName("Нельзя создать сотрудника для несуществующей компании")
    public void iCantCreateEmployeeForNullCompany() throws SQLException {
        String json = "{\"firstName\": \"Иван\", \"lastName\": \"Иванов\", \"companyId\": " + Integer.MAX_VALUE + ", \"phone\": \"+7923432423\"}";
        given().body(json).header(X_CLIENT_TOKEN, getToken())
                .contentType(ContentType.JSON)
                .post(EMPLOYEE).then()
                .statusCode(500)
                .body("statusCode", is(500))
                .body("message", is("Internal server error"));

        assertEquals(0, table.countByCompanyId(Integer.MAX_VALUE));
    }

    private String getToken() {
        String json = """
                {
                  "username": "leonardo",
                  "password": "leads"
                }
                """;

        return given()
                .body(json)
                .contentType(ContentType.JSON)
                .post(LOGIN)
                .then()
                .extract()
                .path("userToken");
    }

    private int createNewCompany() {
        String json = """
                {
                  "name": "Contract Test Company",
                  "description": "string"
                }
                """;

        return given().body(json).contentType(ContentType.JSON).header(X_CLIENT_TOKEN, getToken()).post(COMPANY).then().extract().path("id");
    }
}
