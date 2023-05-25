import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoggingDemo {

    @Test(priority=1)
    void Logging(){

      given()
              .when().get("https://www.google.com/")
              .then()
              .log().headers();
              //.log().cookies();



      ;


     }

}
