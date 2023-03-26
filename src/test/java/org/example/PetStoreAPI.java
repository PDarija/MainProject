package org.example;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;

public class PetStoreAPI {

    @BeforeEach
    public void setup() {

        RestAssured.baseURI = "https://petstore.swagger.io/v2/store/";

    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 7})
    public void getExistingOrderStatusCode200(int id) {

        given().
                log().
                all().
                when().
                get("order/{id}", id).
        then().
                log().
                all().
                statusCode(200);
    }


    @ParameterizedTest
    @ValueSource(ints = {5, 6, 8, 9, 10})
    public void getNonExistingOrderStatusCode404(int id) {

        given().
                log().
                all().
                when().
                get("order/{id}", id).
                then().
                log().
                all().
                statusCode(HttpStatus.SC_NOT_FOUND);
    }


    @Test
    public void checkResponseBodyCompleteTrue() {

        String orderResponse = given().
                log().
                all().
                when().
                get("order/3").
                then().
                log().
                all().
                statusCode(200).
                and().
                extract().
                path("complete");


        Assertions.assertTrue( orderResponse.contains("true"));

    }

    @Test
    public void CheckPetIdBiggerThenZero() {

        long petId = given().
                log().
                all().
                when().
                get("order/3").
                then().
                log().
                all().
                statusCode(200).
                and().
                extract().
                path("petId");


        Assertions.assertTrue( petId > 0);
    }

}
