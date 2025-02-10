package vorobev.aqa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateAndGetUser() {
        // Create user
        User user = new User();
        user.setName("John Doe");

        ResponseEntity<User> createResponse = restTemplate.postForEntity("/users", user, User.class);
        assertEquals(HttpStatus.OK, createResponse.getStatusCode());

        // Get user by ID
        Long userId = createResponse.getBody().getId();
        ResponseEntity<User> getResponse = restTemplate.getForEntity("/users/" + userId, User.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals("John Doe", getResponse.getBody().getName());
    }
}