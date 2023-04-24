package delivery;

import com.google.gson.Gson;
import dto.OrderRealDto;
import dto.courierCreation;
import helpers.SetupFunctions;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeliveryTest {
    public static String token;

    @BeforeAll
    public static void setup() {
        System.out.println("---> test start");

        SetupFunctions setupFunctions = new SetupFunctions();


//        String baseURL = setupFunctions.getBaseUrl();
//        String username = setupFunctions.getUsername();
//        String password = setupFunctions.getPassword();

        System.out.println("token" + setupFunctions.getToken());

        token = setupFunctions.getToken();
        RestAssured.baseURI = setupFunctions.getBaseUrl();

    }

    @Test
    public void createOrderTest() {
        OrderRealDto orderRealDto = new OrderRealDto("testname", "1234567", "no");
        // 1
        Gson gson = new Gson();

        // 2
        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(gson.toJson(orderRealDto))
                .log()
                .all()
                .post("/orders")
                .then()
                .log()
                .all()
                .extract()
                .response();

        System.out.println();

    }

    @Test
    public void createOrderWithoutComment() {
        OrderRealDto orderRealDto = new OrderRealDto("testname", "1234567", "");

        Gson gson = new Gson();


        String comment = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(gson.toJson(orderRealDto))
                .log()
                .all()
                .post("/orders")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .path("comment");
        Assertions.assertEquals("", comment);


    }

    @Test

    public void createOrderWithoutToken() {
        OrderRealDto orderRealDto = new OrderRealDto("secondTest", "26589744", "Without onions");

        Gson gson = new Gson();

        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer")
                .body(gson.toJson(orderRealDto))
                .log()
                .all()
                .post("/orders")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }


    @Test
    public void getOrderById() {
        //1 create order
        int id = orderCreationPrecondition();

        int receiveId = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log()
                .all()
                .get("/orders" + "/" + id)
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .path("id");
        Assertions.assertEquals(receiveId, id);
    }

    @Test
    public void getNonExistedOrderById() {
        //1 create order
        int id = 2807;


        String response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log()
                .all()
                .get("/orders" + "/" + id)
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response()
                .asString();

        Assertions.assertEquals("", response);

    }

    @Test
    public void getOrders() {

        int id = orderCreationPrecondition();

        OrderRealDto[] orderRealDtoArray = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log()
                .all()
                .get("/orders")
                .then()
                .log()
                .all()
                .extract()
                .as(OrderRealDto[].class);


//        orderRealDtoArray.length
        for (int i = 0; i < orderRealDtoArray.length; i++) {

            System.out.println(orderRealDtoArray[i].getId());
            deleteOrderById(orderRealDtoArray[i].getId());
        }
// ex 3
        OrderRealDto[] orderRealDtoArrayAfterDeletion = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log()
                .all()
                .get("/orders")
                .then()
                .log()
                .all()
                .extract()
                .as(OrderRealDto[].class);


        Assertions.assertEquals(0, orderRealDtoArrayAfterDeletion.length);
        System.out.println();

    }
    public void deleteOrderById(long id) {

        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log()
                .all()
                .delete("/orders/" + id)
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void deleteOrderByIdTest() {
        int orderId = orderCreationPrecondition();
        deleteOrderById(orderId);
    }

    @Test

    public void courierOrderAvailabilityForbiddenForStudent() {
        Response response = executeGetMethodStudent("orders/available");
        Assertions.assertEquals(response.statusCode(), HttpStatus.SC_FORBIDDEN);
        System.out.println();
//        TODO - check response code
    }


    @Test
    public void courierOrderAssignForbiddenForStudent() {
        int orderId = orderCreationPrecondition();
//        String string = String.format("/orders/%s/assign", orderId);
        Response response = executePutMethodByStudent(String.format("/orders/%s/assign", orderId));
//        Assertions.assertEquals(response.statusCode(), );
    }

    @Test
    public void getOrdersStatus403ById() {
        int orderId = orderCreationPrecondition();
        Response response = executePutMethodByStudentWithRequestBody(String.format("orders/%s/status", orderId));
        Assertions.assertEquals(response.statusCode(), HttpStatus.SC_FORBIDDEN);
    }

    public int orderCreationPrecondition() {
        OrderRealDto orderRealDto = new OrderRealDto("testname", "1234567", "no");
        Gson gson = new Gson();

        int id = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(gson.toJson(orderRealDto))
                .log()
                .all()
                .post("/orders")
                .then()
                .log()
                .all()
                .extract()
                .path("id");

        return id;
    }


    public void deleteOrderByIdNumber(long id) {

        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log()
                .all()
                .delete("/orders/" + id)
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    public Response executeGetMethodStudent(String path) {
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log()
                .all()
                .get(path)
                .then()
                .log()
                .all()
                .extract()
                .response();
        return response;
    }

    public Response executePutMethodByStudent(String path) {
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log()
                .all()
                .put(path)
                .then()
                .log()
                .all()
                .extract()
                .response();
        return response;
    }


    @Test
    public void createCourierSuccessfulTest() {
        Response response = createCourier();
    }


    public Response createCourier() {

        courierCreation courierBody = new courierCreation(generateRandomLogin(), generateRandomPassword(), generateRandomName());
        Gson gson = new Gson();

        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(gson.toJson(courierBody))
                .log()
                .all()
                .post("/users/courier")
                .then()
                .log()
                .all()
                .extract()
                .response();

        return response;
    }
    public String generateRandomLogin() {
        return RandomStringUtils.random(5, true, true);
    }

    public String generateRandomPassword() {
        return RandomStringUtils.random(10, true, true);
    }

    public String generateRandomName() {
        return RandomStringUtils.random(9, true, false);
    }


    public Response executePutMethodByStudentWithRequestBody(String path) {
        String bodyWithStatus = "{\"status\":\"OPEN\"}";

        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(bodyWithStatus)
                .log()
                .all()
                .put(path)
                .then()
                .log()
                .all()
                .extract()
                .response();

        return response;

    }
}

