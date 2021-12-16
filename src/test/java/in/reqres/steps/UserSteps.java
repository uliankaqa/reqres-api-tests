package in.reqres.steps;

import in.reqres.Constants;
import in.reqres.UserHelper;
import in.reqres.UserObject;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserSteps extends UserHelper {
    private RequestSpecification request;
    private Response response;
    private String payload;
    private String baseUrlUsers;

    @Before
    public void initRequest() {
        request = RestAssured.given().header("Content-Type", "application/json");
        baseUrlUsers = Constants.baseUrl + Constants.endpointUser;
    }

    @After
    public void cleanUser(){
        if(user.getUserId() != null)
            sendDELETERequest();
    }

    @Given("I have a random user data")
    public void haveRandomUser() {
        payload = createRandomUser();
    }

    @Given("I have created user")
    public void haveCreatedUser() {
        if (!(user.isUserCreatedOnServer())) {
            payload = createRandomUser();
            response = request.body(payload).when().post(baseUrlUsers);
            user.setUserId(response.then().extract().path(UserObject.USER_JSON_ID));
        }
    }

    @When("I send POST request")
    public void sendPOSTRequest() {
        response = request.body(payload).when().post(baseUrlUsers);
    }

    @When("I send PUT request with updated {}")
    public void sendPUTRequest(String updatedProperty) {
        updateUser(updatedProperty);
        payload = getJSONBodyFromUserObject();
        response = request.body(payload).when().put(baseUrlUsers + "/" + user.getUserId());
    }

    @When("I send PATCH request with updated {}")
    public void sendPATHRequest(String updatedProperty) {
        updateUser(updatedProperty);
        payload = getJSONBodyFromUserObject();
        response = request.body(payload).when().patch(baseUrlUsers + "/" + user.getUserId());
    }

    @When("I send DELETE request")
    public void sendDELETERequest() {
        response = request.when().delete(baseUrlUsers + user.getUserId());
        user.setUserId(null);
    }

    @Then("I see status code {}")
    public void seeStatusCode(Integer code) {
        response.then().statusCode(code);
    }

    @Then("I see user data in response body")
    public void iSeeUserData() {
        String responseNameValue = response.then().extract().path(UserObject.USER_JSON_NAME);
        user.setUserId(response.then().extract().path(UserObject.USER_JSON_ID));
        assertEquals(responseNameValue, user.getUserName());
    }

    @Then("I see updated {} in response")
    public void seeUpdatedDataInResponse(String updatedProperty) {
        String responsePropertyValue = response.then().extract().path(updatedProperty);
        String currentPropertyValue = getPropertyValue(updatedProperty);
        assertEquals(responsePropertyValue, currentPropertyValue);
    }

}
