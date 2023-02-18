package com.demo;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class MyAPILocalHost3000 {
	
//	@Test(priority=2)
	public void getMethod() {
		baseURI="http://localhost:3000/";
		given()
		.when()
		.get("users")
		.then().statusCode(200)
		.log().all();
	}
	
//	@Test(priority=0)
	public void postMethod() {
		
		JSONObject json = new JSONObject();
		json.put("id", "4");
		json.put("name","Arul");
		json.put("number","75467854");
		baseURI="http://localhost:3000/";
		given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(json.toJSONString())
		.post("users")
		.then()
		.statusCode(201);		
		
	}
	
//	@Test(priority=1)
	public void putMethod() {
		
		JSONObject json = new JSONObject();
		json.put("id", "4");
		json.put("name","ArulMurugan");
		json.put("number","75467854");
		baseURI="http://localhost:3000/";
		given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(json.toJSONString())
		.put("users")
		.then()
		.statusCode(200);		
		
	}
	
	@Test
	public void deleteMethod() {
		baseURI="http://localhost:3000/";
		given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.when()
		.delete("users/4")
		.then()
		.statusCode(200);
	}

}
