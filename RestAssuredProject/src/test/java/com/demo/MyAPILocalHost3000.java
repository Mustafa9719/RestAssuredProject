package com.demo;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MyAPILocalHost3000 {

	
	@Test(priority=2)
	public void getMethod() {
		baseURI="http://localhost:3000/";
		Response response = given()
		.when()
		.get("profile")
		.then().statusCode(200)
		.log().all()
		.extract().response();
	}
	
//	@Test(priority=0)
	public void postMethod() {
		
		JSONObject json = new JSONObject();
		json.put("id", 8);
		json.put("first_name","Mohamed");
		json.put("last_name","Mustafa");
		baseURI="http://localhost:3000/";
		given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(json.toJSONString())
		.post("profile")
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
	
//	@Test
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
