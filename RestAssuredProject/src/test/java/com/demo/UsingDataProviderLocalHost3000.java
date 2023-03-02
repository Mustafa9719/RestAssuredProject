package com.demo;

import static io.restassured.RestAssured.*;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class UsingDataProviderLocalHost3000 {

	@DataProvider(name = "dataforpost")
	public Object[][] dataforpost() {
		return new Object[][] {
			{"Mustafa",3546},
			{"Vinith",56454},
			{"Gokul",48789}
		};
	} 
	

//	@Test(dataProvider="dataforpost")
	public void postMethod(String name, int number) {
		
		JSONObject json = new JSONObject();
		json.put("name",name);
		json.put("number",number);
		baseURI="http://localhost:3000/";
		given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(json.toJSONString())
		.post("users")
		.then()
		.statusCode(201);		
	}
	@DataProvider(name = "DeleteData")
	public Object[] datafordelete(){
		return new Object[] {
				7,8,9
		};
	}
	
	@Test(dataProvider="DeleteData")
	public void deleteMethod(int id) {
		baseURI="http://localhost:3000/";
		given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.when()
		.delete("users/"+id)
		.then()
		.statusCode(200);
	}

}
