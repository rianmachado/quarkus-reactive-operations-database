package rian.example.reative.database;

import static org.hamcrest.CoreMatchers.containsString;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

@QuarkusTest
public class AppArsenalEndpointTest  {

	@Test
	public void getAllAppArsenalTest() {
		RestAssured.
			when()
			.get("/api/v1/apparsenal").then().statusCode(200)
			.contentType("application/json")
			.body(containsString("Corote"));
	}

	@Test
	public void updateProductTest() {
		String myJson = "{\"otherInfo\":\"Pinga certa: " + LocalDateTime.now() + "\"}";
		RestAssured.given().body(myJson).contentType("application/json").when().put("/api/v1/apparsenal/5").then()
				.statusCode(200);
	}
}