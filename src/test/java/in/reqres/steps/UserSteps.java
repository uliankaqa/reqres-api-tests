package in.reqres.steps;

import in.reqres.UserHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static in.reqres.Constants.baseUrl;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserSteps extends UserHelper {
    private RequestSpecification request;
    private static Response response;
    private static String payload;

    @Given("I have a random user")
    public void iHaveRandomUser(){
        request = RestAssured.given().header("Content-Type", "application/json");
        payload = ceateRandomUser();
    }

    @When("I send POST request to '{}' endpoint")
    public void iSendPostRequest(String endpoint){
        response = request.body(payload).when().post(baseUrl + endpoint);
    }

    @Then("I see status code {}")
    public void iSeeStatusCode(Integer code){
        response.then().statusCode(code);
    }

    @Then("I see user data in response body")
    public void iSeeUserData(){
       String responseNameValue =  response.then().extract().path("name");
        assertEquals(responseNameValue, userName);
    }
}
