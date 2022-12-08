package ch.zli.m223.lb2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.zli.m223.lb2.model.ApplicationUser;
import ch.zli.m223.lb2.model.Role;

import javax.inject.Inject;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ApplicationUserResourceTest {
  @Inject
  TestDataService testDataService;

  @BeforeEach
  void resetDB() {
    testDataService.generateTestData();
  }

  @Test
  void testGetUserEndpoint() {
    given()
        .when().get("/members")
        .then()
        .statusCode(200)
        .body("size()", is(3));
  }

  @Test
  void testCreateUserEndpoint(){
    var user = new ApplicationUser();
    user.setFirstname("Max");
    user.setLastname("Mustermann");
    user.setEmail("max@example.com");
    user.setNickname("Maxl");
    user.setPassword("Passw0rd");
    user.setRole(Role.MEMBER);
    given().body(user)
        .when().post("/members")
        .then()
        .statusCode(200);
  }
}
