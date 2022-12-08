package ch.zli.m223.lb2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;


@QuarkusTest
public class ApplicationUserResourceTest {
  @Inject
  TestDataService testDataService;
  
  @BeforeEach
  void resetDB(){
    testDataService.generateTestData();
  }
  @Test
    public void testCreateUserEndpoint(){
        given()
            .when().get("/members/")
            .then()
              .statusCode(200)
              .body("size()", is(3));
    }
}
