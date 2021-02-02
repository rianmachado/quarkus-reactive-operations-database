package rian.example.reative.database;

import static org.hamcrest.CoreMatchers.equalTo;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

@QuarkusTest
public class RestTest {

	//@Test
	public void getAllProductTest() {
		RestAssured.when()
		.get("/products")
		.then()
		.statusCode(200)
		.contentType("application/json")
		.body(equalTo("eu"));
	}

	//@Test
	public void updateProductTest() {
		String myJson = "{\"description\":\"Cachaca pau do indio A\"}";
		RestAssured.given()
		.body(myJson)
		.when()
		.put("/products/12")
		.then()
		.statusCode(200)
		.contentType("application/json").body(equalTo("{\"menssage\":\"Sucesso ....\"}"));
	}
}