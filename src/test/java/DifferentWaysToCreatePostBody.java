

/*Different ways to create post request body
1-post request body using HashMap;
2-post request using org.JSON
3-post request using POJO class
4-post request using external json file data
 */

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DifferentWaysToCreatePostBody {

    //@Test(priority = 1)
    void testPostUsingHashMap(){

        HashMap data=new HashMap();

        data.put("name","Nitish");
        String arr[]={"Engineering","Technician"};
        data.put("job",arr);

        given().contentType("application/json").body(data)
                .when().post("https://reqres.in/api/users")
                .then().statusCode(201);

    }

    //@Test(priority = 2)
    void testPostUsingJsonObject(){

        JSONObject data=new JSONObject();

        data.put("name","Nitish");
        String arr[]={"Engineering","Technician"};
        data.put("job",arr);

        given().contentType("application/json").body(data.toString())
                .when().post("https://reqres.in/api/users")
                .then().statusCode(201).log().all();

    }

    @Test(priority = 3)
    void testPostUsingPOJOClass(){

        POJO data=new POJO();


        data.setName("Nitish_Kumar_Thakur");
        String arr[]={"Engineering","Technician"};
        data.setJob("arr");

        given().contentType("application/json").body(data.toString())
                .when().post("https://reqres.in/api/users")
                .then().statusCode(400)
                .body("name",equalTo("Nitish_Kumar_Thakur"))
                .body("arr[0]",equalTo("Engineering"))
                .body("arr[1]",equalTo("Technician"));
    }

    @Test(priority = 4)
    void testPostUsingExternalJsonFile() throws FileNotFoundException {

        File f=new File("D:\\nit.json");
        FileReader file=new FileReader(f);
        JSONTokener jt=new JSONTokener(file);
        JSONObject data=new JSONObject(jt);

        given().contentType("application/json").body(data.toString())
                .when().post("https://reqres.in/api/users")
                .then().statusCode(201)
                .body("name",equalTo("Nitish_Kumar_Thakur"))
                .body("job",equalTo("Engineering")).log().all();
    }
}
