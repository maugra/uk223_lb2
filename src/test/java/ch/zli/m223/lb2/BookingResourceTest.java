package ch.zli.m223.lb2;

import ch.zli.m223.lb2.model.Booking;
import ch.zli.m223.lb2.model.Duration;
import ch.zli.m223.lb2.service.ApplicationUserService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.security.jwt.Claim;
import io.quarkus.test.security.jwt.JwtSecurity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Set;

import static io.restassured.RestAssured.given;

@TestSecurity(user = "maurus@example.com", roles = {"Admin"})
@JwtSecurity(claims = {
		@Claim(key = "upn", value = "maurus@examle.com")
})
@QuarkusTest
public class BookingResourceTest {

	@Inject
	TestDataService testDataService;
	@Inject
	ApplicationUserService applicationUserService;

	@BeforeEach
	void resetDB() {
		testDataService.resetDB();
	}

	@Test
	void testCreateBooking(){
		var user = applicationUserService.findByEmail("maurus@example.com").get();
		var booking = new Booking();
		booking.setApplicationUser(user);
		booking.setDate(LocalDate.now().plusDays(1));
		booking.setDuration(Set.of(Duration.MORNING));
		booking.setWithLocker(true);
		given()
				.header("Content-Type", "application/json")
				.body(booking)
				.when().post("/bookings")
				.then()
				.statusCode(200);
	}
}
