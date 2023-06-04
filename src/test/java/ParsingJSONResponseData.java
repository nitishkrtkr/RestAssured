import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ParsingJSONResponseData {

    @Test(priority = 1)
    void testJSONResponse(){

        //Approach 1
     /*   given().contentType(ContentType.JSON)
                .when().get(" http://localhost:3000/students")
                .then()
                .statusCode(200)
                .header("content-Type","application/json; charset=utf-8")
                .body("[0].name",equalTo("nitish"))
                .body("[2].phoneNo",equalTo("987654321"));  */


        //Approach 2

        Response res=
        given().contentType(ContentType.JSON)

                .when().get("http://localhost:3000/posts");

//        Assert.assertEquals(res.statusCode(),200);
//        Assert.assertEquals(res.contentType(),"application/json; charset=utf-8");
//        String phNo = res.jsonPath().get("[2].phoneNo").toString();
//        Assert.assertEquals(phNo,"987654321");
//        System.out.println(phNo);

        //Using JSON Object Class

        JSONObject js=new JSONObject(res.asPrettyString());//converting response to JSOn Object Type

        //here we are converting response so it is asString() not data;

      for(int i=0;i<js.getJSONArray("posts").length();i++){

          String phone = js.getJSONArray("posts").getJSONObject(i).get("author").toString();
          System.out.println(phone);
//
//
//
      }

    }
}
