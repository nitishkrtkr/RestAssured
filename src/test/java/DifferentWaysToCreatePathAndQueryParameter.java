import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DifferentWaysToCreatePathAndQueryParameter {

    @Test(priority =1)
    void createPathAndQery(){

        given()
                .pathParam("mypath1","users")
                .pathParam("mypath2","api")
                .queryParam("page","2")
                .when()
                .get("https://reqres.in/{mypath2}/{mypath1}")
                .then().statusCode(200).log().all();



    }
}
