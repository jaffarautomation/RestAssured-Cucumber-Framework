package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification r1;

	// This is is generic for request specification
	public RequestSpecification requestSpecification() throws IOException {

		if (r1 == null)

		{
			PrintStream log = new PrintStream(new FileOutputStream("Logging.txt"));
			r1 = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("Key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
			return r1;
		}

		return r1;
	}

	public static String getGlobalValue(String key) throws IOException {

		Properties prop = new Properties();
		FileInputStream r = new FileInputStream(
				"C:\\Users\\ADMIN\\eclipse-workspace1\\RestAssured-UdemyFramework\\src\\test\\java\\resources\\globalConfigFile.properties");
		prop.load(r);
		return (String) prop.get(key);

	}

	// THis method will accept Resposne and JSON key and Will Return the JSON Value
	// in String format
	public static String getJSONpath(Response response, String key) {
		String resp = response.asString();
		JsonPath r = new JsonPath(resp);

		return r.get(key);
	}

}
