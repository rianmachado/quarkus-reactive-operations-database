package rian.example.reative.database;

import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

@QuarkusTest
public class RestTest {

	@Test
	public void getAllProductTest() {

		RestAssured.when().get("/products").then().statusCode(200).contentType("application/json")
				.body(equalTo("eu"));
	}

}