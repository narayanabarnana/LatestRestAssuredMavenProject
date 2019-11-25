package restAssuredUsingHTTPReqeustAndResponse;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class GetMessage {
	
	
		public static void main(String[] args)
		{
		
		//Specify the base URL to the RESTful web service
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		
		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step
		
		RequestSpecification httpRequest=RestAssured.given();
		
		
		// Make a request to the server by specifying the method Type and the method URL.
		 // This will return the Response from the server. Store the response in a variable.
		Response response=httpRequest.request(Method.GET, "/Hyderabad");
		
		//Validate Response status 
		int statusCode=response.getStatusCode();		
		System.out.println("The status code is " + statusCode);		
		Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
		
		
		String statusLine=response.getStatusLine();		
		System.out.println("Status line of the response is " + statusLine); //Output is Status line of the response is HTTP/1.1 200 OK
		
		//Validate response Header 
		//validate content type
		String contentType = response.header("Content-Type");
		 System.out.println("Content-Type value: " + contentType);  //output Content-Type value: application/json
		 
		// Reader header of a give name. In this line we will get
		 // Header named Server
		 String serverType =  response.header("Server");
		 System.out.println("Server value: " + serverType); //Output is Server value: nginx
		 
		 
		// Reader header of a give name. In this line we will get
		 // Header named Content-Encoding
		 String acceptLanguage = response.header("Content-Encoding");
		 System.out.println("Content-Encoding: " + acceptLanguage);   //Output is Content-Encoding: gzip	
		 
		 //Get all the headers
		 Headers allHeaders = response.headers();
		// Iterate over all the Headers
		 for(Header header : allHeaders)
		 {
		 System.out.println("Key: " + header.getName() + " || Value: " + header.getValue());
		 }
		
		 
		//Method to print response body
		String responseBody=response.getBody().asString();		
		System.out.println("Response body is " + responseBody);
		
		//Or we can use this also
		String responseBody1=response.body().asString();
		System.out.println(responseBody1);
		
		JsonPath jsonPathEvaluator=response.jsonPath();
		String cityName=jsonPathEvaluator.get("City");
		System.out.println("City Name is " + cityName);
		
		
		
		}
}
