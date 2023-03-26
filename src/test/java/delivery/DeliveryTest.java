package delivery;

import com.google.gson.Gson;
import dto.OrderRealDto;
import dto.OrderTestDto;
import helpers.SetupFunctions;
import io.restassured.RestAssured;
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

        System.out.println("token" + setupFunctions.getToken() );

        token = setupFunctions.getToken();
        RestAssured.baseURI = setupFunctions.getBaseUrl();

    }

    @Test
    public void createOrderTest(){
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
    public void createOrderWithoutComment(){
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
        Assertions.assertEquals("",comment);



    }

    @Test

    public void createOrderWithoutToken(){
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
    public void getOrderById(){
        //1 create order
        int id = orderCreationPrecondition();

        int receiveId = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log()
                .all()
                .get("/orders" + "/"+ id)
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .path("id");
        Assertions.assertEquals(receiveId, id);

    }


    @Test
    public void getNonExistedOrderById(){
        //1 create order
        int id = 2807;


        String response= given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log()
                .all()
                .get("/orders" + "/"+ id)
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        Assertions.assertEquals("", response);

    }

    @Test
    public void getOrders(){

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
        for ( int i = 0; i < orderRealDtoArray.length; i ++){

//            System.out.println(orderRealDtoArray[i].getId());
            deleteOrderById( orderRealDtoArray[i].getId() );
        }
        System.out.println();

    }

    @Test
    public void deleteOrderByIdTest(){
        deleteOrderById(2807);
    }

    public int orderCreationPrecondition(){
        OrderRealDto orderRealDto = new OrderRealDto("testname", "1234567", "no");
        Gson gson = new Gson();

        int id = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body( gson.toJson( orderRealDto ) )
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

    public void deleteOrderById(long id){

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
               .statusCode(200);

    }







}
