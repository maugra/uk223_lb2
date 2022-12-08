package ch.zli.m223.lb2;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.security.jwt.Claim;
import io.quarkus.test.security.jwt.JwtSecurity;
import org.junit.jupiter.api.BeforeEach;

import javax.inject.Inject;

@TestSecurity(user = "maurus@example.com", roles = {"Admin"})
@JwtSecurity(claims = {
		@Claim(key = "upn", value = "maurus@examle.com")
})
@QuarkusTest
public class BookingResourceTest {

	@Inject
	TestDataService testDataService;

	@BeforeEach
	void resetDB() {
		testDataService.resetDB();
	}

}
