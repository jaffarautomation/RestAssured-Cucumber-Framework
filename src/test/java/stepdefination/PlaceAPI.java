package stepdefination;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import POJO.Location;
import POJO.SerializationJavaClass;

public class PlaceAPI extends Utils {

	RequestSpecification req;
	ResponseSpecification t1;
	 static Response responseP;
	TestDataBuild r = new TestDataBuild();
	static String Place_ID ;

	// Achiveing Data Driven approach from feature file with help of Example
	// keyword.
	// This approach will pass JSON key value from feature file
	// If there are 4 rows this test case will run for 4 times.
	@Given("Add Place Payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {

		// RestAssured.baseURI="https://rahulshettyacademy.com";
		req = given().spec(requestSpecification()).log().all().body(r.addPlacePayload(name, language, address));

	}

	// In This method we are just concerned to hit GET, POST or PUT request.
	// In other method we will do response valiation

	@When("User calls {string} API with {string} Https request")
	public void user_calls_api_with_https_request(String resources, String method) {

		// Below Code will call the Enum class constructor and then constuctor will call
		// the method which is getting placed from feature file
		// If resources i.e method is not present in Enum class then it will throw an
		// error.

		APIResources resourcesAPI = APIResources.valueOf(resources);
		System.out.println(resourcesAPI);

		t1 = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (method.equalsIgnoreCase("POST"))
			// Now we are attaching the RS object to when
			responseP = req.when().post(resourcesAPI.getResources());
		else if (method.equalsIgnoreCase("GET"))
			responseP = req.when().get(resourcesAPI.getResources());
		System.out.println( responseP.getBody().prettyPrint());
		
	}

	// Validating Status code
	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {

		System.out.println("Actual statusCode is " + responseP.getStatusCode());

		// Here we are doing Junit Assert on Response Code
		assertEquals(responseP.getStatusCode(), 200);

	}

	// Validating status is OK, and Scope is APP and fecthing Place_ID.
	// Here we are sending actual JSON Key and Value from feature file
	@Then("{string} in reponse body is {string}")
	public void in_reponse_body_is(String key, String ExpectedValue) {

		/*
		 * // Extracting the JSON object with help of JsonPath Class String r =
		 * responseP.asString(); JsonPath t = new JsonPath(r);
		 * 
		 * // Now Storing the value of Keys which is Status in String "Actualvalue"
		 * String Actualvalue = t.get(key).toString();
		 * System.out.println("Actual Value for " + key + " " + Actualvalue);
		 */

		// Calling the getJSONpath method from Utils class.
		System.out.println(getJSONpath(responseP, key));
		// Now Using Junit Assert to validate the Actual and Expected Value
		assertEquals(getJSONpath(responseP, key), ExpectedValue);
		 

	}

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String ExpectednameValue, String resources) throws IOException {

		 Place_ID = getJSONpath(responseP, "place_id");
		System.out.println(Place_ID);
		req = given().spec(requestSpecification()).queryParam("place_id", Place_ID);
		user_calls_api_with_https_request(resources, "GET");
		System.out.println(responseP.getStatusCode());
		String name = getJSONpath(responseP, "address");
		System.out.println(name);
//		assertEquals(name, ExpectednameValue);

	}

	
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
		 Place_ID = getJSONpath(responseP, "place_id");
			System.out.println(Place_ID);
			
	   req=given().spec(requestSpecification()).body(r.deletePlacePayload(Place_ID));
	  
	
	}



}
