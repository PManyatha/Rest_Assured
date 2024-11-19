import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.lang.Math.log;

public class RestAssuredBasicTest3B {
    //cookies and HeadersRestAssuredBasicTest3A


    @Test
    public void testCookies() {
        given()

                .when()
                .get("https://www.google.com/")

                .then()
               // .cookie("AEC","")
                .log().all();

    }

    @Test
    public void testCookiesInfoCapture() {
       Response res = given()

                .when()
                .get("https://www.google.com/");

       //get single cookie
//             String cookie_value = res.getCookie("AEC");
//             System.out.println("The value of cookie is: ======>" + cookie_value);

        //get all cookies info
        Map<String,String> cookie_values = res.getCookies();
        System.out.println(cookie_values.keySet());

       for(String k : cookie_values.keySet()){
            String value = res.getCookie(k);
            System.out.println("Key === "+k + ": value === " + value);

        }



    }
}
