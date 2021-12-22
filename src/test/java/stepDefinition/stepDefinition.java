package stepDefinition;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.response.Response;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import POJO.location;
import POJO.locationBody;
import Resource.APIResources;
import Resource.TestDataBuild;
import Resource.Utils;
public class stepDefinition extends Utils 
{
	RequestSpecification request;
	ResponseSpecification commonResponse;
	Response response;
	static String place_id; //will not be reset t null
	JsonPath js;
	TestDataBuild data = new TestDataBuild();
	@Given("Add Place Payload with {string} ,{string},{string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException  
	{		    
		
		
		request =  given().log().all().spec(requestSpecification())
				.body(data.addPlacePayload(name,language,address));
	}

	@When("^User calls \"([^\"]*)\" with \\\"([^\\\"]*)\\\" HTTP request$")
	public void user_calls_something_with_post_http_request(String strArg1,String strArg2)  {
		APIResources apiResource = APIResources.valueOf(strArg1);
		commonResponse = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		if(strArg2.equalsIgnoreCase("POST"))
		{
			response = request.when().post(apiResource.getResource())
					.then().spec(commonResponse)
					.extract().response();
		}
		else if(strArg2.equalsIgnoreCase("GET"))
		{
			response = request.when().get(apiResource.getResource())
					.then().spec(commonResponse)
					.extract().response();
		}
	}

	@Then("^The API call is successful with status code 200$")
	public void the_api_call_is_successful_with_status_code_200()
	{
		Assert.assertEquals(response.getStatusCode(),200);
	}

	@And("^\"([^\"]*)\" in responde body is \"([^\"]*)\"$")
	public void something_in_responde_body_is_something(String key, String value) 
	{
		
		Assert.assertEquals(getJsonPath(response, key),value);
	}
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String string, String string2) throws IOException 
	{
		place_id = getJsonPath(response, "place_id");
		request =  given().log().all().spec(requestSpecification()).queryParam("place_id",place_id);
		user_calls_something_with_post_http_request(string2,"GET");
		Assert.assertEquals(getJsonPath(response, "name"),string);
	}
	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException 
	{
		request = given().log().all().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
		//don't need to write other methods as the past methods have already been written
	}

}
