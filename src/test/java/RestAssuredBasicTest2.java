import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static javax.swing.UIManager.get;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredBasicTest2 {

    //Using HashMAp

    @Test
    public void TestPostUsingHashMap(){
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "Raja");
       map.put ("location", "India");
       map.put("phone", "7896541230");

       String[] courseArr={"c","C++"};
       map.put("courses", Arrays.toString(courseArr));

       given()
               .contentType("application/json")
               .body(map)
               .when()
  //             .get("http://localhost:3000/0")
               .post("http://localhost:3000/0")
                .then()
               .statusCode(201)
               .body("name", equalTo("Raja"))
              .body("location", equalTo("India"))
               .body(courseArr[0], equalTo("c"))
              .header("Content-Type", "application/json")
               .log().all();

    }

    @Test
    void testDelete(){
        given()
                .when()
                .delete("http://localhost:3000/Students/6")

                .then()
                .statusCode(204);

    }
    //Using org.JSON

    @Test
    public void TestPostUsingJsonLib(){
        JSONObject data = new JSONObject();
        data.put("name", "Raja");
        data.put ("location", "India");
        data.put("phone", "7896541230");

        String[] courseArr={"c","C++"};
        data.put("courses", Arrays.toString(courseArr));

        given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("http://localhost:3000/0")
                .then()
                .statusCode(201)
                .body("name", equalTo("Raja"))
                .body("location", equalTo("India"))
                .body(courseArr[0], equalTo("c"))
                .header("Content-Type", "application/json")
                .log().all();

    }

    @Test
    void testDeleteORG(){
        given()
                .when()
                .delete("http://localhost:3000/Students/6")

                .then()
                .statusCode(204);

    }

    //Using POJO class

    @Test
    public void TestPostUsingPOJO(){
        Pojo_PostRequest data = new Pojo_PostRequest();
        data.setName("Raja");
        data.setLocation("India");
        data.setPhone("1233");
        String[] courseArr={"c","C++"};
        data.setCourses(courseArr);

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("http://localhost:3000/0")
                .then()
                .statusCode(201)
                .body("name", equalTo("Raja"))
                .body("location", equalTo("India"))
                .body(courseArr[0], equalTo("c"))
                .header("Content-Type", "application/json")
                .log().all();

    }

    @Test
    void testDeletePOJO(){
        given()
                .when()
                .delete("http://localhost:3000/Students/6")

                .then()
                .statusCode(204);

    }

    //Using External JSON file
    @Test
    public void TestPostUsingExternalFile() throws FileNotFoundException {
        File f = new File(".\\body.json");
        FileReader fr = new FileReader(f);
        JSONTokener jt = new JSONTokener(fr);

        JSONObject data = new JSONObject(jt);

        given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("http://localhost:3000/0")
                .then()
                .statusCode(201)
                .body("name", equalTo("Raja"))
                .body("location", equalTo("India"))
                .header("Content-Type", "application/json")
                .log().all();

    }

    @Test
    void testDeleteExternalFile(){
        given()
                .when()
                .delete("http://localhost:3000/Students/6")

                .then()
                .statusCode(204);

    }

}
