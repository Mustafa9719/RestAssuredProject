package com.demo;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.util.Map;
import java.util.TreeMap;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MyAPILocalHost3000 {
	public static Response response;
	
//	@Test (priority=0)
	public void getMethod() {
		baseURI="http://localhost:3000/";
		given()
		.when()
		.get()
		.then().statusCode(200)
		.log().all();
	}
	
	@Test(priority=1)
	public void postMethod() {
		
		Map<String, Object> json = new TreeMap<String, Object>();
		json.put("id", 6);
		json.put("name","Mustafa");
		json.put("number",754674);
		baseURI="http://localhost:3000/";
		given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(json)
		.post("users")
		.then()
		.statusCode(201);		
	}
	
//	@Test(priority=2)
	public void putMethod() {
		
		JSONObject json = new JSONObject();
		json.put("name","ArulMurugan");
		json.put("number","75467854");
		baseURI="http://localhost:3000/";
		given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(json.toJSONString())
		.put("users/5")
		.then()
		.statusCode(200)
		.log().body()
		.extract().response().asPrettyString();		
	}
	
	@Test(priority=3)
	public void deleteMethod() {
		baseURI="http://localhost:3000/";
		given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.when()
		.delete("users/5")
		.then()
		.statusCode(200);
	}

}
