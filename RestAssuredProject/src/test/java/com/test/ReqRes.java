package com.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import java.util.*;

public class ReqRes {

	@Test(enabled=false)
	public void sample1() {
		Response response = get("https://reqres.in/api/users?page=2");
		System.out.println(response.getStatusCode());
		System.out.println(response.getStatusLine());
		Headers headers = response.getHeaders();
		System.out.println(headers);
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200, "Verifying status code is 200");
	}

	@Test(enabled=false)
	public void getMethod() {
		baseURI = "https://reqres.in/api";
		given()
		.get("/users?page=2")
		.then()
		.statusCode(200)
		.body("data[4].id", equalTo(11))
		.body("data[0].first_name", equalTo("Michael"))
		.body("data.first_name", hasItems("Byron", "Byron"))
		.log().all();
	}
	
	@Test(priority=0)
	public void postMethod() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Name", "Mohamed Mustafa");
		map.put("Job", "Software Tester");
		JSONObject request = new JSONObject(map);
		baseURI = "https://reqres.in/api";
		given()
		.header("Content-type","application/json")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(request.toJSONString())
		.when()
		.post("/users")
		.then()
		.statusCode(201)
		.log().all();
	}
	
	@Test(priority=1)
	public void putMethod() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Name", "Mohamed Mustafa");
		map.put("Job", "Automation Tester");
		JSONObject request = new JSONObject(map);
		baseURI = "https://reqres.in/api";
		given()
		.header("Content-type","application/json")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(request.toJSONString())
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
