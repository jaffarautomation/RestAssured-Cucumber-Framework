package resources;

import java.util.ArrayList;
import java.util.List;

import POJO.Location;
import POJO.SerializationJavaClass;

//This is test data class which we will instantiate in step defination pclass pakage.
//Basically this method take care for request payload.
public class TestDataBuild {

	public SerializationJavaClass addPlacePayload(String name, String language,String Address) {

		// Below Class contain the Code to ADD Place API and It is a POJO Class
		SerializationJavaClass r = new SerializationJavaClass();
		r.setAccuracy(50);
		r.setAddress(Address);
		r.setLanguage(language);
		r.setPhone_number("8087319458");
		r.setWebsite("googale");
		r.setName(name);
		List<String> t = new ArrayList<String>();
		t.add("Shoes");
		t.add("wa1lqk");

		r.setTypes(t);
		Location p = new Location();
		p.setLat(42332);
		p.setLng(64);
		r.setLocation(p);

		return r;

	}
	
	public String deletePlacePayload(String PlaceID)
	{
		
		return "{\"place_id\":\""+PlaceID+"\"}";
	}
}
