package steps;


import endpoints.EndPoints;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.annotations.BeforeMethod;
import pojo.Billing;
import pojo.Customer;
import pojo.Shipping;
import utilities.ConfProperties;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetMethod {
    private static RequestSpecification spec;
    private static OAuth2Filter auth;
    public static Response response;
    public static ConfProperties properties;


    @BeforeClass
    public static void SetUP() {
        String accessToken = "a9arkha0qdkdz1rppvalxqenbjvwbbb9ec3zx1aa";//getAccessToken(username, password);
        auth = new OAuth2Filter(accessToken);
hhhhh

    }

    @BeforeMethod
    public void configureRestAssured() {
        RestAssured.baseURI = "http://begetwin.beget.tech/wp-json/wc/v3";
        RestAssured.requestSpecification = given()
                .header("X-Response-Control", "minified")
                .header("Language", "en");
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @BeforeClass
    public static void initSpec() {
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("http://begetwin.beget.tech/wp-json/wc/v3")
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build().log().all();
    }

    @Test
    public void ferstStep() {
        given()
                .filter(auth)
                .spec(spec)
                .when()
                .get(EndPoints.customers)
                .then()
                .statusCode(200);
    }

    @Test
    public void basicSearch() {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> productInfo = new HashMap<>();
        productInfo.put("name", "Premium Quality");
        productInfo.put("type", "simple");
        productInfo.put("regular_price", "21.99");
        productInfo.put("description", "Pellentesque habitant morbi tristique senectus et netus");

        // Make request and retrieve result


    }

    @Test
    //This API helps you to view all the customers.
    public void basicSearch2() {
        given()
                .filter(auth)
                .spec(spec)
                .when()
                .get(EndPoints.customers).then().statusCode(200).body("findAll { it.billing}.first_name", contains("Mak", "", "ivan", "John", "eeee"));

    }


    @Test
    //This API lets you retrieve and view a specific customer by ID.
    public void givenUrl_whenSuccessOnGetsResponseAndJsonHasRequiredKV_thenCorrect() {
        given().filter(auth).spec(spec).when().get(EndPoints.customerId4).then().statusCode(200).assertThat()
                .body("billing.first_name", equalTo("ivan"));
    }

    @Test
    //This API helps you to create a new customer.
    public void createUser() {
        Billing billing = new Billing("Boris", "Pak", "USSr", "street 7", "", "London", "Ca", "5000", "UK", "yy@mail.ru", "233333");
        Shipping shipping = new Shipping("Boris", "Boris", "Boris", "Boris", "Boris", "B" +
                "Boris", "Boris", "Boris", "UK");
        Customer customer = new Customer("yy@mail.ru", "Pak", "Pak", "BorisPak", billing, shipping);
        given().filter(auth).spec(spec).body(customer).when().post(EndPoints.customers).then().statusCode(201);


    }

    @Test
    public void updateCustomer() {
        Billing billing = new Billing("Boris", "Pak", "USSr", "street 7", "", "London", "Ca", "5000", "UK", "yy@mail.ru", "233333");
        Shipping shipping = new Shipping("Boris", "Boris", "USSr", "street", "", "New City",
                "CA", "680000", "US");
        Customer customer = new Customer("yy@mail.ru", "Boris", "Pak", "BorisPak", billing, shipping);
        given().filter(auth).spec(spec).body(customer).when().put(EndPoints.customerUpdate).then().statusCode(200);


    }

    @Test //This API helps you delete a customer.
    public void deleteCustomer() {
        given().filter(auth).spec(spec).when().delete(EndPoints.customerId8DELETE).then().statusCode(200);
    }


}








