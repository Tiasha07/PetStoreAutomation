package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPointsWithRoutes;
import api.payload.User;
import io.restassured.response.Response;

public class UserTestsWithRoutes {

	Faker faker;
	User userPayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setUp()
	{
		faker= new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("**************Creating User*****************");
		Response response = UserEndPointsWithRoutes.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(),200);	
		logger.info("**************User is created*****************");
	}
	
	@Test(priority=2)
	public void testGetUser()
	{
		logger.info("**************Reading User*****************");
		Response response = UserEndPointsWithRoutes.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.statusCode(),200);	
		logger.info("**************User is displayed*****************");
	}
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		// Update data using payload
		logger.info("**************Updating User*****************");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPointsWithRoutes.updateUser(userPayload,this.userPayload.getUsername());
		response.then().log().all();
		response.then().log().body().statusCode(200); // chai assertion
		Assert.assertEquals(response.statusCode(),200);	// testng assertion
		
		// Checking data after updation
		Response responseAfterUpdate = UserEndPointsWithRoutes.readUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().all();
		Assert.assertEquals(responseAfterUpdate.statusCode(),200);	
		logger.info("**************User is updated*****************");
	}
	
	
	@Test(priority=4)
	public void testdelUser()
	{
		logger.info("**************Deleting User*****************");
		Response response = UserEndPointsWithRoutes.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.statusCode(),200);	
		logger.info("**************User is deleted*****************");
	}
	
}
