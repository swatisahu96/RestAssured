package org.example.tests.crud;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.example.endpoints.APIConstants;
import org.example.tests.base.BaseTest;
import org.testng.annotations.Test;
import io.qameta.allure.Description;

@Owner("Swati")
//@Description
@Severity(SeverityLevel.CRITICAL)
@Test(groups = {"P0","Sanity", "Regression"})
public class createBooking extends BaseTest {

    @Test
    public void testCreateBooking() throws JsonProcessingException {


        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URI);

        response = RestAssured.given().spec(requestSpecification).when().
                body(payloadManager.createPayload()).post();

        validatableResponse =response.then().log().all();

        System.out.println("Response---->"+response);
        jsonPath = JsonPath.from(response.asString());
        System.out.println("Booking Id :" + jsonPath.getString("bookingid"));
        //Returns json path
        System.out.println("jsonPath ---->" + jsonPath);
    }


}
