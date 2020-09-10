package resources;

//It is special class in JAVA which has collection of constant or methods
public enum APIResources {

	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"), 
	deletePlaceAPI("maps/api/place/delete/json");
   private String resources;
	APIResources(String resources) {

	this.resources=resources;
	}
	
	public String getResources()
	{
		return resources;
	}

}
