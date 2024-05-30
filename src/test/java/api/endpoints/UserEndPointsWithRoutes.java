package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//Created to Perform CRUD operations of the User API

public class UserEndPointsWithRoutes {
	
	static ResourceBundle getURL()
	{
		// method created for getting URL's from properties file
		ResourceBundle routes = ResourceBundle.getBundle("routes"); // loads the properties file // by default checks the src/test/resources hence just provide the name of the properties file
		return routes;
	}
	
	public static Response createUser(User Payload)
	{
		String post_URL = getURL().getString("post_url");
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(Payload)
		.when()
			.post(post_URL);
		
		return response;	
	}
	
	public static Response readUser(String userName)
	{
		String get_URL = getURL().getString("get_url");
		Response response = 
		given()
			.pathParam("username", userName)
		.when()
			.get(get_URL);
		
		return response;
	}
	
	public static Response updateUser(User Payload,String userName)
	{
		String update_URL = getURL().getString("update_url");
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(Payload)
		.when()
			.put(update_URL);
		
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		String delete_URL = getURL().getString("delete_url");
		Response response = 
		given()
			.pathParam("username", userName)
		.when()
			.delete(delete_URL);
		
		return response;
	}
	
	

}
