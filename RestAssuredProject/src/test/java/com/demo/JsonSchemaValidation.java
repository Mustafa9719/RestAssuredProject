package com.demo;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.testng.annotations.Test;

public class JsonSchemaValidation {
	@Test
	public void getMethod() {
		baseURI="https://reqres.in/api";
		given()
		.when()
		.get("/users?page=2")
		.then()
		.assertThat().body(matchesJsonSchemaInClasspath("JSONschema2.json"))
		.log().all();
	}
}
