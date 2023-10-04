package org.example.tests.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.endpoints.APIConstants;
import org.example.module.PayloadManager;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    public RequestSpecification requestSpecification;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;

    @BeforeMethod
    public void setUpConfig() {

        // Creating requestSpecification object so that it can be extended
        requestSpecification = (RequestSpecification) new RequestSpecBuilder().
                setBaseUri(APIConstants.BASE_URI)
                .addHeader("Content-Type", "application/json").
                build().log().all();
    }

    public String getToken() {

        requestSpecification = RestAssured.given().baseUri(APIConstants.BASE_URI)
                .basePath(APIConstants.CREATE_TOKEN_URI).contentType(ContentType.JSON);


        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        response = requestSpecification.when().body(payload).post();
        validatableResponse = response.then();

        JsonPath jsonPath1 = JsonPath.from(response.asString());
        String token = jsonPath1.getString("token");

        return token;

    }
}
