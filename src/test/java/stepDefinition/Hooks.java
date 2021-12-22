package stepDefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks 
{
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		//write a code that will give you place id
		//execute this code only when place id is null
		stepDefinition m = new stepDefinition();
		if(stepDefinition.place_id == null)//static belong to class name, so you can't use m
		{
			m.add_Place_Payload_with("tempPlace","English","temp place street");
			m.user_calls_something_with_post_http_request("AddPlaceAPI","POST");
			m.verify_place_id_created_maps_to_using("tempPlace","getPlaceAPI");
		}
		
	}

}
