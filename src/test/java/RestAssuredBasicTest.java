import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


/*
given()
content type, set cookies, add auth, add param, set header info etc

when()
get, post, put, delete

then()
validate status code, extract response, extract headers cookies and response body...
 */

public class RestAssuredBasicTest {
//    @Test
//    public void testGetRequest() {
//        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts/1");
//
//        // Validate status code
//        Assert.assertEquals(response.getStatusCode(), 200);
//
//        // Validate response body
//        System.out.println(response.getBody().asString());
//        Assert.assertTrue(response.getBody().asString().contains("userId"));
//    }
//
//    @Test
//    public void getUser() {
//        given()
//                .when()
//                .get("https://reqres.in/api/users?page=2")
//                .then()
//                .statusCode(200)
//                .body("page", equalTo(2))
//                .log().all();
//    }

    @Test
    public void testGetRequest() {
        given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)  // Validate status code
                .body("userId", equalTo(1))  // Validate a JSON field
                .log().all();  // Print response details
    }

    @Test
    public void testGetMultipleUsers() {
        given()
                .baseUri("https://reqres.in")
                .queryParam("page", 2)
                .when()
                .get("/api/users")
                .then()
                .statusCode(200)  // Validate status code
                .body("page", equalTo(2))  // Validate query parameter
                .log().all();  // Log response
    }

    @Test
    public void testCreateUser() {
        HashMap<String,Object> requestBody = new HashMap<>();
        requestBody.put("name", "John Doe");
        requestBody.put("job", "Software Engineer");

        given()
                //.baseUri("https://reqres.in")
                //.header("Content-Type", "application/json")
                .contentType("application/json")
                .body(requestBody)  // Attach request body
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)  // Validate status code
                .log().all();  // Print response

    }
    @Test(dependsOnMethods = {"testCreateUser"})
    public void testUpdateUser() {
        HashMap<String, Object> updatedData = new HashMap<>();
        updatedData.put("name", "John Updated");
        updatedData.put("job", "Senior Engineer");

        given()
                .baseUri("https://reqres.in")
                .header("Content-Type", "application/json")
                .body(updatedData)
                .when()
                .put("/api/users/2")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(dependsOnMethods = {"testUpdateUser"})
    public void testDeleteUser() {
        given()
                .baseUri("https://reqres.in")
                .when()
                .delete("/api/users/2")
                .then()
                .statusCode(204)  // No content status code
                .log().all();
    }
    @Test
    public void testCaptureResponseData() {
        Response response =
                given()
                        .baseUri("https://reqres.in")
                        .when()
                        .get("/api/users/2");

        int id = response.jsonPath().getInt("data.id");
        System.out.println("Captured ID: " + id);

        // Use this ID in further tests
    }

}
