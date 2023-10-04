package org.example.tests.integrationTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.example.endpoints.APIConstants;
import org.example.tests.base.BaseTest;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class IntergrationTest extends BaseTest {

    String token;

    @Description("Verify booking is created")
    @Test
    public void createBooking(ITestContext iTestContext) throws JsonProcessingException {

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URI);
        response = RestAssured.given().spec(requestSpecification).when().
                body(payloadManager.createPayload()).post();

        validatableResponse = response.then().log().all();
        JsonPath jsonPath_Create = JsonPath.from(response.asString());
//      String bookingID= jsonPath.getString("bookingid");
        System.out.println("Booking Id is : " + jsonPath_Create.getString("bookingid"));
        iTestContext.setAttribute("bookingid", jsonPath_Create.getString("bookingid"));
        String bookingId = (String) iTestContext.getAttribute("bookingid");
        assertThat(bookingId).isNotNull();

    }

    @Description("Verify booking is updated")
    @Test(dependsOnMethods = "createBooking")
    public void createAndUpdateBooking(ITestContext iTestContext) throws JsonProcessingException {
        System.out.println(" --Running Update Booking --");
        token = getToken();
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URI + "/" + (String) iTestContext.getAttribute("bookingid"));

        response = RestAssured.given().spec(requestSpecification)
                //.cookie("token", token) OR
                .auth().preemptive().basic("admin", "password123")
                .when().body(payloadManager.updatePayload()).put();

        assertThat(response.statusCode()).isEqualTo(200);

    }

    @Test(enabled = true, dependsOnMethods = "createBooking")
    public void deleteCreatedBooking(ITestContext iTestContext) {

//        assertThat(response.asString()).isBlank();

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URI + "/" + (String) iTestContext.getAttribute("bookingid")).cookie("token", token);
        ValidatableResponse validatableResponse = RestAssured.given().spec(requestSpecification).auth().basic("admin", "password123")
                .when().delete().then().log().all();
        validatableResponse.statusCode(201);


    }

}
