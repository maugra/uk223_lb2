package ch.zli.m223.lb2;

import ch.zli.m223.lb2.model.ApplicationUser;
import ch.zli.m223.lb2.model.Role;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.security.jwt.Claim;
import io.quarkus.test.security.jwt.JwtSecurity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@TestSecurity(user = "flurin@example.com", roles = { "Admin" })
@JwtSecurity(claims = {
        @Claim(key = "upn", value = "flurin@examle.com")
})
@QuarkusTest
public class ApplicationUserResourceTest {
  @Inject
  TestDataService testDataService;

  @BeforeEach
  void resetDB(){
    testDataService.resetDB();
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
    given().header("Content-Type", "application/json").body(user)
        .when().post("/members")
        .then()
        .statusCode(200);
  }
  @Test
  void testCreateUserWithWeakPassword(){
    var user = new ApplicationUser();
    user.setFirstname("Max");
    user.setLastname("Mustermann");
    user.setEmail("max@example.com");
    user.setNickname("Maxl");
    user.setPassword("1234");
    user.setRole(Role.MEMBER);
    given().header("Content-Type", "application/json").body(user)
            .when().post("/members")
            .then()
            .statusCode(400);
  }
  @Test
  void testCreateUserWithExistingEmail(){
    var user = new ApplicationUser();
    user.setFirstname("Max");
    user.setLastname("Mustermann");
    user.setEmail("robinsky@example.com");
    user.setNickname("Maxl");
    user.setPassword("Passw0rd");
    user.setRole(Role.MEMBER);
    given().header("Content-Type", "application/json").body(user)
            .when().post("/members")
            .then()
            .statusCode(409);
  }
}
