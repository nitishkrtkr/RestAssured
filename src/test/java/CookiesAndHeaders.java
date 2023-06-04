import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CookiesAndHeaders {

   // @Test(priority=1)
   void CookiesDemo(){

        Response res = given()

               .when().get("https://www.google.com/");

              Map<String,String> map =res.getCookies();

              for(String s : map.keySet()){

                  System.out.println(s+"-----"+res.getCookie(s));
              }

   }

      @Test(priority=2)
     void HeadersDemo(){

       Response res= given()

                .when().get("https://www.google.com/");

               Headers head=res.getHeaders();
       for(Header h: head){
           System.out.println(h.getName());
           System.out.println(res.getHeader(h.getName()));

       }



   }
}
