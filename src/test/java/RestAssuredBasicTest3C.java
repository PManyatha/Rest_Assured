import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredBasicTest3C {

    // Headers

    @Test
    public void testHeaders() {

        given()

                .when()
                .get("https://www.google.com/")

                .then()
                .header("Content-Type","text/html; charset=ISO-8859-1")
                .and()
                .header("Content-Encoding", "gzip")
                .and()
                .header("Server", "gws");

    }

    @Test
    public void testHeadersInfo() {
        Response res = given()
                .when()
                .get("https://www.google.com/");

        //get single Header info
        String info = res.getHeader("Content-Type");
        System.out.println("Content-Type: "+info);

        //get multiple Headers
        Headers headers = res.getHeaders();
        for(Header header : headers ) {
            System.out.println(header.getName()+": "+header.getValue());
        }


    }



    }

