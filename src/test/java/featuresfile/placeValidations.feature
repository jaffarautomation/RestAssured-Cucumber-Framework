Feature: Validating Place API's

@AddPlace
Scenario Outline:  Verify if Place is successfully added using AddPlaceApi
Given Add Place Payload "<name>" "<language>" "<address>"
When User calls "AddPlaceAPI" API with "POST" Https request
Then the API call is success with status code 200
And "status" in reponse body is "OK"
And "scope" in reponse body is "APP"
#And verify place_id created maps to "<name>" using "getPlaceAPI"

Examples: 
      
       |name|language |address|
       |Jeff|Urdu|Plot456, mumbai|
 #      |anu|telgu|indora|

 @DeletePlace
 Scenario: Verify if delete Place functionality is working
 
Given DeletePlace Payload
When User calls "deletePlaceAPI" API with "POST" Https request
Then the API call is success with status code 200
And "status" in reponse body is "OK" 