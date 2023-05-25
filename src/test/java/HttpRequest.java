import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import io.restassured.matcher.RestAssuredMatchers.*;
import java.util.HashMap;


    public class HttpRequest {
        int id;

        @Test(priority=1)
     void testUser(){


        given().when().get("https://reqres.in/api/users?page=2")

                .then().assertThat().statusCode(200).log().all();
     }

       @Test(priority=2)
     void createUser(){

         HashMap<String,String> hm=new HashMap<>();
         hm.put("name","Nitish");
         hm.put("style","dashing");

         id=given().contentType("application/json").body(hm)
                 .when().post("https://reqres.in/api/users").jsonPath().getInt("id");
//
//                 .then().statusCode(201).log().all();
     }

        @Test(priority=3,dependsOnMethods = {"createUser"})
        void updateUser(){

            HashMap<String,String> hm=new HashMap<>();
            //hm.put("name","Nitish");
            hm.put("style","Invinsible");

            given().contentType("application/json").body(hm)
                    .when().put("https://reqres.in/api/users/"+id)
                    .then().statusCode(200).log().all();
        }

        @Test(priority=4)
        void deleteUser(){

        when().delete("https://reqres.in/api/users/"+id)
                .then().statusCode(204).log().all();


        }

}
