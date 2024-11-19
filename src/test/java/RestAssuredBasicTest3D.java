import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredBasicTest3D {
//log() inside then() method
    @Test
    public void testLogging() {
        given()

                .when()
                .get("https://reqres.in/api/users?page=3")

                .then()
                //.log().body();
                //.log().cookies();
                //.log().headers();
                .log().all();

    }
}
