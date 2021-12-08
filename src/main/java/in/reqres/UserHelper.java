package in.reqres;

import com.github.javafaker.Faker;
import com.google.gson.JsonObject;
import groovy.json.JsonException;


public class UserHelper {
    private Faker faker = new Faker();
    public String userName;

    public String ceateRandomUser() throws JsonException{
        userName = faker.name().firstName();
        JsonObject payload = new JsonObject();
        payload.addProperty("name", userName);
        payload.addProperty("job", faker.job().position());
        return payload.toString();


    }
}
