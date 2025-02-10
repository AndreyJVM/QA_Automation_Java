package vorobev.aqa;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Epic("User Management")
@Feature("CRUD Operations")
public class UserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Description("This test verifies the creation of a user and its retrieval by ID.")
    @Story("Create and Retrieve User")
    public void testCreateAndGetUser() {
        createUser("John Doe");
        Long userId = getUserId("John Doe");
        retrieveUser(userId, "John Doe");
    }

    @Step("Create a user with name {name}")
    private void createUser(String name) {
        User user = new User();
        user.setName(name);
        ResponseEntity<User> createResponse = restTemplate.postForEntity("/users", user, User.class);
        assertEquals(HttpStatus.OK, createResponse.getStatusCode());
    }

    @Step("Get user ID by name {name}")
    private Long getUserId(String name) {
        ResponseEntity<User[]> response = restTemplate.getForEntity("/users", User[].class);
        User[] users = response.getBody();
        assertNotNull(users);
        for (User user : users) {
            if (name.equals(user.getName())) {
                return user.getId();
            }
        }
        fail("User not found: " + name);
        return null;
    }

    @Step("Retrieve user by ID {userId} and verify name {expectedName}")
    private void retrieveUser(Long userId, String expectedName) {
        ResponseEntity<User> getResponse = restTemplate.getForEntity("/users/" + userId, User.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals(expectedName, getResponse.getBody().getName());
    }

    @Test
    @Description("This test verifies the handling of a non-existing user.")
    @Story("Error Handling")
    public void testGetUserNotFound() {
        ResponseEntity<String> response = restTemplate.getForEntity("/users/999", String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().contains("User not found"));
    }

    @Test
    @Description("This test verifies the update of a user's information.")
    @Story("Update User")
    public void testUpdateUser() {
        createUser("Alice");
        Long userId = getUserId("Alice");

        // Update the user's name
        User updatedUser = new User();
        updatedUser.setName("Alice Smith");
        restTemplate.put("/users/" + userId, updatedUser);

        // Verify the update
        retrieveUser(userId, "Alice Smith");
    }

    @Test
    @Description("This test verifies the deletion of a user.")
    @Story("Delete User")
    public void testDeleteUser() {
        createUser("Bob");
        Long userId = getUserId("Bob");

        // Delete the user
        restTemplate.delete("/users/" + userId);

        // Verify the user is deleted
        ResponseEntity<String> getResponse = restTemplate.getForEntity("/users/" + userId, String.class);
        assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());
        assertTrue(getResponse.getBody().contains("User not found"));
    }

    @Test
    @Description("This test verifies the retrieval of all users.")
    @Story("Retrieve All Users")
    public void testGetAllUsers() {
        // Create two users
        createUser("User One");
        createUser("User Two");

        // Get all users
        ResponseEntity<User[]> response = restTemplate.getForEntity("/users", User[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().length >= 2); // At least two users should be returned
    }
}