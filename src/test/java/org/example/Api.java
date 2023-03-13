package org.example;

import dto.OrderDto;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static io.restassured.RestAssured.given;

public class Api {

    @BeforeEach
    public void setup() {
        System.out.println("---> test start");
        RestAssured.baseURI = "http://51.250.6.164";
        RestAssured.port = 8080;
    }

    @Test
    public void simplePositiveTest() {

        given().
                log().
                all().
                when().
                get("/test-orders/5").
//                  get("http://51.250.6.164:8080/test-orders/5")    .
        then().
                log().
                all().
                statusCode(200);
    }


    @Test
    public void simpleNegativeTest() {

        given().
                when().
                get("http://51.250.6.164:8080/test-orders/15").
                then().
                statusCode(400);
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5, 9, 10})
    public void simpleParamPositiveTest(int id) {

        given().
                log().
                all().
                when().
                get("/test-orders/{id}", id).
                then().
                log().
                all().
                statusCode(HttpStatus.SC_OK);

    }

    @ParameterizedTest
    @ValueSource(ints = {0, 11, -1})
    public void simpleParamNegativeTest(int id) {

        given().
                log().
                all().
                when().
                get("/test-orders/{id}", id).
                then().
                log().
                all().
                statusCode(HttpStatus.SC_BAD_REQUEST);
    }


    @Test
    public void simplePositiveTestAndExtractBodyStatusOpen() {

        String responseString = given().
                log().
                all().
                when().
                get("/test-orders/5").
                then().
                log().
                all().
                statusCode(200).
                and().
                extract().
                asString();

        Assertions.assertTrue(responseString.contains("OPEN"));
    }


    @Test
    public void simplePositiveTestAndExtractParameterFromBody() {

        int responseId = given().
                log().
                all().
                when().
                get("/test-orders/5").
                then().
                log().
                all().
                statusCode(200).
                and().
                extract().
                path("id");

        Assertions.assertTrue(responseId > 0);
    }


//    String order = "{\"customerName\":\"name\",\"customerPhone\":\"123456\",\"comment\":\"comment\"}";

    @Test
    public void createOrderAndCheckStatusCode() {
        OrderDto orderDto = new OrderDto("testname", "21546494", "no");
//        int length = 10;
//        boolean useLetters = true;
//        boolean useNumbers = false;
//        String randomName = RandomStringUtils.random(10, true, false);
//        String randomPhoneNumber = RandomStringUtils.random(10, false, true);
//        String randomComment = RandomStringUtils.random(5, true, true);
        OrderDto orderDtoRandom = new OrderDto();
//        orderDtoRandom.setCustomerName(RandomStringUtils.random(10, true, false));
        orderDtoRandom.setCustomerName (generateRandomName());
        orderDtoRandom.setCustomerPhone(RandomStringUtils.random(10, false, true));
        orderDtoRandom.setComment(RandomStringUtils.random(5, true, true));

        given()
                .header("Content-type", "application/json")
                .body(orderDtoRandom)
                .log()
                .all()
                .post("/test-orders")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    String secondOrder = "{\"customerName\":\"name\",\"customerPhone\":\"123456\",\"comment\":\"comment\"}";

    //3. Добавьте отдельный негативный тест, проверяющий код ответа 415 для метода POST (исключите header request).
    @Test
    public void createOrderAndCheckNegativeStatusCode() {

        given()

                .body(secondOrder)
                .log()
                .all()
                .post("/test-orders")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
    }

    //4. Добавьте отдельный позитивный парметризованный тест, проверяющий тело ответа для метода GET. В теле нужно проверить

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5, 9, 10})
    public void simplePositiveTestAndExtractBodyAsString(int id) {

        String responseString = given().
                log().
                all().
                when().
                get("/test-orders/{id}", id).
                then().
                log().
                all().
                statusCode(200).
                and().
                extract().
                path("status");


        Assertions.assertTrue(responseString.contains("OPEN"));
    }

    public String generateRandomName() {
        return RandomStringUtils.random(10, true, false);
    }


}




