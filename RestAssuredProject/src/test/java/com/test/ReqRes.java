package com.test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import java.util.*;

public class ReqRes {

	public static Response response;
	
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
	
	@Test()
	public void getMethod1() {
		baseURI = "https://reqres.in/api";
		Response response = given()
				.when()
				.get("/users?page=2")
		.then()
		.statusCode(200)
		.extract().response();
		String asPrettyString = response.asPrettyString();
		try {
		JSONParser jp = new JSONParser();
		Object parse = jp.parse(asPrettyString);
		JSONObject obj1 = (JSONObject)parse;
		Object object = obj1.get("data");
		JSONArray jsarray1 = (JSONArray)object;
		for (int i = 0; i < jsarray1.size(); i++) {
			JSONObject jsobj = (JSONObject)jsarray1.get(i);
			
			String str = (String)jsobj.get("first_name");
			System.out.println(str);
		}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test(priority=0)
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
	
//	@Test(priority=1)
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
		.log().all()
		.when()
		.put("/users/2")
		.then()
		.statusCode(200)
		.log().all();
	}
	
//	@Test(priority=2)
	public void deleteMethod() {
		baseURI="https://reqres.in/api";
		when()
		.put("/users/2")
		.then()
		.statusCode(200)
		.log().body()
		.extract().response();
//		String asString = response.asString();
//		System.out.println(asString);
//		Assert.assertEquals(asString.contains(""), arg1);
	}

}
