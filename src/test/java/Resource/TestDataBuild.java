package Resource;

import java.util.ArrayList;
import java.util.List;

import POJO.location;
import POJO.locationBody;

public class TestDataBuild 
{
	public locationBody addPlacePayload(String name, String language, String address)
	{
		//serialize the body
		locationBody body = new locationBody();
		location location = new location();
		//set location
		location.setLat(-38.383494);
		location.setLng(33.427362);
		body.setLocation(location);
		body.setAccuracy(50);
		body.setName(name);
		body.setPhone_number("(+91) 983 893 3937");
		body.setAddress(address);
		//set array list
		List<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		body.setTypes( types);		    
		body.setWebsite("http://google.com");
		body.setLanguage(language);
		return body;
	}
	public String deletePlacePayload(String placeID)
	{
		return "{\r\n"
				+ "		    \"place_id\":\"" + placeID + "\"\r\n"
				+ "		}";

	}
	
}
