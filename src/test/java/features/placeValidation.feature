Feature: Validating Place APIs
@AddPlace
 Scenario Outline: Verify if Place is being successully added using AddPlaceAPI
Given Add Place Payload with "<name>" ,"<language>","<address>"
When User calls "AddPlaceAPI" with "POST" HTTP request
Then The API call is successful with status code 200
And "status" in responde body is "OK"
And "scope" in responde body is "APP"
And verify place_id created maps to "<name>" using "getPlaceAPI"


Examples:
|         name    |  language          | address        |
|      House      |       English       |   A place      | 
|      House number 2      |      French      |   Another place      | 


@DeletePlace
Scenario: Verify if Delete Place functionality is working
Given DeletePlace Payload
When User calls "deletePlaceAPI" with "POST" HTTP request
Then The API call is successful with status code 200
And "status" in responde body is "OK"
