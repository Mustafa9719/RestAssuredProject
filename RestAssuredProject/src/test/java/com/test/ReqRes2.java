package com.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

public class ReqRes2 {
	// @Test
	public void getMethodNormal() {
		Response response;
		response = RestAssured.get("https://reqres.in/api/users?page=2");
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200, "Verifying status code");
	}

//	@Test
	public void getMethod() {
		given().get("https://reqres.in/api/users?page=2")
		.then().statusCode(200).log().all();
	}
	
	@Test(priority=0)
	public void postMethod() {
//		Map<String, Object> mp = new HashMap<String, Object>();
//		mp.put("City", "Tester");
//		mp.put("Job", "Tester");
//		mp.put("Name", "Vinith");
		JSONObject json = new JSONObject();
		json.put("City", "Tester");
		json.put("Job", "Tester");
		json.put("Name", "Vinith");
		baseURI = "https://reqres.in/api";
		given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(json)
		.when()
		.post("/users")
		.then()
		.statusCode(201)
		.log().all();
	}
	@Test(priority=1)
	public void putMethod() {
		JSONObject json = new JSONObject();
		json.put("City", "Chennai");
		baseURI = "https://reqres.in/api";
		given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(json)
		.when()
		.put("/users/2")
		.then()
		.statusCode(200)
		.log().all();
	}
	
	@Test(priority=2)
	public void deleteMethod() {
		baseURI="https://reqres.in/api";
		when()
		.put("/users/2")
		.then()
		.statusCode(200)
		.log().all();
	}
}


