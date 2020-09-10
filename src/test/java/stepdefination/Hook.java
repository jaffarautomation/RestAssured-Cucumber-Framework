package stepdefination;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hook {

	@Before("@DeletePlace")
	public void beforeSceanrio() throws IOException {
		
		PlaceAPI t = new PlaceAPI();
		
		if (t.responseP==null)
		{
		t.add_place_payload("nagpur", "gyu", "wadi");
		t.user_calls_api_with_https_request("AddPlaceAPI", "POST");
		}

	}

}
