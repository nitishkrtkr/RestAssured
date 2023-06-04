
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.equalTo;

public class practice {

      int id;
    //String createdAt;

    @Test(priority = 1)
    void testUser(){

        // We can log response specification using log() method provided by ValidatableResponse interface.
        // In fact ValidatableResponse extends ValidatableResponseOptions<ValidatableResponse,Response> interface.
        // log() method returns  ValidatableResponseLogSpec interface reference that allows you to log different parts of the Response.
        //headers, cookies, body, status
        //all() – Logs everything in the response, including e.g. headers, cookies, body. Pretty-prints the body if content-type is either either XML, JSON or HTML.
        //all(boolean shouldPrettyPrint) – Logs everything in the response, including e.g. headers, cookies, body with the option to pretty-print the body if the content-type is either XML, JSON or HTML.
        //everything() or everything(boolean shouldPrettyPrint) – Same as all() and all(boolean shouldPrettyPrint).
        //headers() – Logs only the headers.
        //cookies() – Logs only the cookies.
        //ifValidationFails() – Logs everything if a test validation fails.
        //ifValidationFails(LogDetail logDetail) – Logs with the supplied log detail only if the validation fails.
        //ifValidationFails(LogDetail logDetail,boolean shouldPrettyPrint) – Logs all parameters only if the validations fail.
        given().when().get("https://reqres.in/api/users/2")
                .then().assertThat().statusCode(200);
    }

    @Test(priority = 2)
    void createUser(){

        HashMap<String, String> hm=new HashMap<>();
        hm.put("name","Nitish Kumar Thakur");
        hm.put("College_name","NSEC Kolkata");


        id = given().contentType("application/json").body(hm)
                .when().post("https://reqres.in/api/users").jsonPath().getInt("id");
//                .then().log().body();

        System.out.println(id);
    }

     @Test(priority = 3 , dependsOnMethods = {"createUser"})
    void updateUser(){

        HashMap<String, String> map=new HashMap();
        map.put("name","Sahil");
        map.put("College_name","Engineering");

        given().contentType("application/json").body(map)
                .when().put("https://reqres.in/api/users/"+id)

                .then().log().body();

    }

     @Test(priority = 4)
    void deleteUser(){

       given()
               .when().delete("https://reqres.in/api/users/"+id)
               .then();



    }

    @Test(priority = 5)
    void pathQueryPara(){

        given().pathParam("path1","api").pathParam("path2","users")
                .queryParam("page","2")

                .when().get("https://reqres.in/{path1}/{path2}")

                .then().statusCode(200).log().all();


    }

    @Test(priority = 6)
    void AllCookies(){

       Response res= given()
                .when().get("https://www.google.com/");

      Map<String, String> map= res.getCookies();

      for(String key : map.keySet()){

          System.out.println(key+"-------"+res.getCookie(key));

      }
    }

 @Test(priority = 7)
  void Headers(){

     Response res = given().when().get("https://www.google.com/");

        Headers hd = res.getHeaders();

        for(Header h: hd){

            System.out.println(h.getName());
            System.out.println(res.getHeader(h.getName()));
        }
    }

    @Test(priority = 8)
    void postUsingJSONObject(){

        JSONObject data=new JSONObject();
        data.put("name", "Nitish");
        String Students[]={"HAHA","WOWWO"};
        data.put("value",Students);

        given().contentType("application/json").body(data.toString())

                .when().post("https://reqres.in/api/users").then().log().all();
    }

    @Test(priority = 9)
    void UsingExternalJSONFile() throws FileNotFoundException {
//
        File f=new File("C:\\Postman\\students.json");
        FileReader ff=new FileReader(f);
        JSONTokener jt=new JSONTokener(ff);
        JSONObject js=new JSONObject(jt);

        given().contentType("applications/json").body(js.toString())
               .when().put(" http://localhost:3000/students")
                .then().statusCode(200)
                .body("students[0].name",equalTo("nitish"))
                .body("students[2].name",equalTo("jharkhand")).log().all();

    }

}
