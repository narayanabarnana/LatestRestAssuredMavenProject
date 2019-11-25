package basicsOfGoogleRestAPI;

import org.testng.annotations.Test;

import files.ReusableMethod;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetRequestGoogleDirectionsAPI {
	
	
	@Test
	public void googleDirections() {
		
		
		
		//https://maps.googleapis.com
		///maps/api/directions/json?origin=Disneyland&destination=Universal+Studios+Hollywood&key=YOUR_API_KEY
		RestAssured.baseURI="https://maps.googleapis.com";
		Response res=given().
		queryParam("origin", "Disneylant").
		queryParam("destination", "Universal+Studios+Hollywood").
		queryParam("key","AIzaSyCWg2j-SAAUKIy8rTVK_dPlw7z14kRL7Io").
		when().
		get("/maps/api/directions/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).
		extract().response();
		
		String response=res.asString();
		System.out.println(response);
		
		//JSON Path Expression Tester: https://jsonpath.curiousconcept.com/
		
		
		
		
		JsonPath js=ReusableMethod.rawToJson(res);
		
		int count=js.get("geocoded_waypoints.size()");
		System.out.println("size of the geocoded_waypoints is " + count);
		
		 for(int i=0;i<count;i++)
		   {
			 //System.out.println(js.get("geocoded_waypoints["+i+"]"));
			   //System.out.println();
			   //System.out.println(js.get("results["+i+"].name"));
		   }
		
		
		String status=js.get("geocoded_waypoints[0].geocoder_status");
		System.out.println("Status is " + status);
		
		String Placeid=js.get("geocoded_waypoints[0].place_id");
		System.out.println("Place id is " + Placeid);
		
		String type=js.get("geocoded_waypoints[0].types[0]");
		System.out.println("Type 1 is " + type);
		
		String northeastlongitude=js.get("routes[0].bounds.northeast.lat").toString();
		System.out.println("North East Longitude is " + northeastlongitude);
		
		String copyright=js.get("routes[0].copyrights");
		System.out.println("Copy Right is " + copyright);
		
		String distancetext=js.get("routes[0].legs[0].distance.text");
		System.out.println("Distance Text is " + distancetext);
		
		String end_address=js.get("routes[0].legs[0].end_address");
		System.out.println("End Address is " + end_address);
		
		String PolylinePoint=js.get("routes[0].legs[0].steps[2].polyline.points");
		System.out.println("Polyline point is " + PolylinePoint);
		
		String travel_mode=js.get("routes[0].legs[0].steps[2].travel_mode");
		System.out.println("Travel Mode is " + travel_mode);
		
	
		
	}

}
