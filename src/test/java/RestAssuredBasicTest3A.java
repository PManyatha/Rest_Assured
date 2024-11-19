import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;


public class RestAssuredBasicTest3A {
    @Test
    void testPathAndQueryParams(){
        given()
                .pathParam("path","users")  //Path parameters
                .queryParam("page",2) //Query parameters
                .queryParam("id",2)   //Query parameters

                .when()
                .get("http://reqres.in/api/{path}")

                .then()
                .statusCode(200)
                .log().all();


    }
}
