package in.reqres;

import com.github.javafaker.Faker;
import com.google.gson.JsonObject;
import groovy.json.JsonException;


public class UserHelper {
    //faker helper for creating random users data
    private Faker faker = new Faker();

    //Users object, include all users data
    public UserObject user = new UserObject();

    /*
    Method create random users and return  JSON String for POST request body
     */
    public String createRandomUser() throws JsonException {
        user.setUserName(getNewRandomName());
        user.setUserJob(getNewRandomJob());

        return getJSONBodyFromUserObject();
    }

    /*
    Method create and return  JSON String for POST request body
     */
    public String getJSONBodyFromUserObject() {
        JsonObject payload = new JsonObject();
        if (user.getUserName() != null)
            payload.addProperty(UserObject.USER_JSON_NAME, user.getUserName());
        if (user.getUserJob() != null)
            payload.addProperty(UserObject.USER_JSON_JOB, user.getUserJob());
        return payload.toString();
    }

    /*
    Methods for  updates random Job and Name properties
     */
    public String getNewRandomJob() {
        return faker.job().position();
    }

    public String getNewRandomName() {
        return faker.name().firstName();
    }


    protected void updateUser(String updatedProperty) {
        if (updatedProperty.equals(UserObject.USER_JSON_NAME)) user.setUserName(getNewRandomName());
        if (updatedProperty.equals(UserObject.USER_JSON_JOB)) user.setUserJob(getNewRandomJob());
    }

    /*
    Method get from JSON name property the value of object property
     */
    protected String getPropertyValue(String jsonName) {
        if (jsonName.equals(UserObject.USER_JSON_NAME)) return user.getUserName();
        if (jsonName.equals(UserObject.USER_JSON_JOB)) return user.getUserJob();
        if (jsonName.equals(UserObject.USER_JSON_ID)) return user.getUserId();
        return "";
    }
}
